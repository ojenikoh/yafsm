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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transition that = (Transition) o;

        if (trigger != null ? !trigger.equals(that.trigger) : that.trigger != null) return false;
        return !(nextState != null ? !nextState.equals(that.nextState) : that.nextState != null);

    }

    @Override
    public int hashCode() {
        int result = trigger != null ? trigger.hashCode() : 0;
        result = 31 * result + (nextState != null ? nextState.hashCode() : 0);
        return result;
    }
}
