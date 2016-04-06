package com.perfonomy.yafsm

import spock.lang.Specification

class StateSpec extends Specification {
    def 'It does not accept duplicate transitions' () {
        given: 'Equal Transitions'
            def firstEvent = new Event('dummy')
            def secondEvent = new Event('dummy')
            def firstState = new State('DummyState', [] as Set<Transition>)
            def secondState = new State('DummyState', [] as Set<Transition>)
            def firstTransition = new Transition(firstEvent, firstState)
            def secondTransition = new Transition(secondEvent, secondState)

        when: 'Both transitions are supplied to a state'
            def state = new State('SomeState', [
                    firstTransition,
                    secondTransition
            ] as Set<Transition>)

        then: 'There should be only one transition'
            state.numberOfTransitions == 1
    }
}
