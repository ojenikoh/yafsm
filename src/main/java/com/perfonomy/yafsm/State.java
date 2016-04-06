package com.perfonomy.yafsm;

import com.perfonomy.yafsm.exceptions.UnMappedEventException;

import java.util.Optional;
import java.util.Set;

public class State {

    private String name;
    private Set<Transition> transitions;

    public State(String name, Set<Transition> transitions) {
        this.name = name;
        this.transitions = transitions;
    }

    public State on(final Event event) throws UnMappedEventException {
        Optional<Transition> transition = transitions.stream().filter(t -> t.supports(event)).findFirst();

        if (transition.isPresent()) {
            return transition.get().getNextState();
        }

        throw new UnMappedEventException(String.format("No State transition for event: %s", event.getName()));
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfTransitions() {
        return transitions.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (name != null ? !name.equals(state.name) : state.name != null) return false;
        return !(transitions != null ? !transitions.equals(state.transitions) : state.transitions != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (transitions != null ? transitions.hashCode() : 0);
        return result;
    }
}
