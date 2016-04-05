package com.perfonomy.yafsm;

import com.perfonomy.yafsm.exceptions.UndefinedTransitionException;

public class StateMachine {

    private State currentState;

    public StateMachine(State startState) {
        currentState = startState;
    }

    public State apply(Event event) throws UndefinedTransitionException {
        currentState = currentState.on(event);
        return currentState;
    }

    public State getCurrentState() {
        return currentState;
    }
}
