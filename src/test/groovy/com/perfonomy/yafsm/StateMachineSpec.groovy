package com.perfonomy.yafsm

import com.perfonomy.yafsm.exceptions.UnMappedEventException
import spock.lang.Specification

class StateMachineSpec extends Specification {
    def "A State Machine can be configured"() {
        def goodsDispatchedState = new State('Goods_Dispatched', [] as Set<Transition>)
        def csrReviewState = new State('CSRReview', [
                new Transition(new Event('CSRApproved'), goodsDispatchedState)
        ] as Set<Transition>)
        def paymentProcessingState = new State('PaymentProcessing', [
                new Transition(new Event('PaymentProcessed'), csrReviewState)
        ] as Set<Transition>)
        def startState = new State('InBasket', [
                new Transition(new Event('OrderPlaced'), paymentProcessingState)
        ] as Set<Transition>)

        def stateMachine = new StateMachine(startState)

        when: "An order is placed"
            def newState = stateMachine.apply(new Event('OrderPlaced'))
        then: 'The next state should be payment processing'
            newState.equals(paymentProcessingState)
            stateMachine.currentState.equals(paymentProcessingState)
        when: 'An unknown event is applied'
            stateMachine.apply(new Event('RandomEvent'))
        then: 'An unmapped event exception should be thrown'
            thrown(UnMappedEventException)
        when: 'When a payment processed is applied'
            newState = stateMachine.apply(new Event('PaymentProcessed'))
        then: 'New state should be CSR Review'
            newState.equals(csrReviewState)
            stateMachine.currentState.equals(csrReviewState)
        when: 'When a CSR approved event is applied'
            newState = stateMachine.apply(new Event('CSRApproved'))
        then: 'New state should be Goods Dispatched'
            newState.equals(goodsDispatchedState)
            stateMachine.currentState.equals(goodsDispatchedState)
        when: 'Any other event is applies'
            stateMachine.apply(new Event('OrderPlaced'))
        then: 'An unmapped event exception should be thrown as no other transitions are possible'
            thrown(UnMappedEventException)
    }
}
