package com.perfonomy.yafsm

import spock.lang.Specification

class TransitionSpec extends Specification {
    def 'Transitions are equal if the trigger and next states are equal'() {
        when:
            def t1 = new Transition(new Event('E1'), new State('S1', [] as Set<Transition>))
            def t2 = new Transition(new Event('E1'), new State('S1', [] as Set<Transition>))

        then:
            t1.equals(t2)

        when:
            def t3 = new Transition(new Event('E3'), new State('S1', [] as Set<Transition>))

        then:
            !t1.equals(t3)
        when:
            def t4 = new Transition(new Event('E1'), new State('S2', [] as Set<Transition>))

        then:
            !t1.equals(t4)
    }
}
