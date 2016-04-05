package com.perfonomy.yafsm;

public class Transition {
    private Event trigger;
    private State nextState;

    public Transition(Event trigger, State nextState) {
        this.trigger = trigger;
        this.nextState = nextState;
    }

    public State getNextState() {
        return nextState;
    }

    public boolean supports(Event event) {
        return trigger.equals(event);
    }
}
