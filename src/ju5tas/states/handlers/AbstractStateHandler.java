package ju5tas.states.handlers;

import ju5tas.states.DoubleState;
import ju5tas.states.State;

public abstract class AbstractStateHandler implements StateHandler {

    public AbstractStateHandler() {
        this(new DoubleState());
    }

    public AbstractStateHandler(DoubleState state) {
        this.state = state;
    }

    protected DoubleState state;

    @Override
    public DoubleState getState() {
        return state;
    }

    protected static void changeState(DoubleState doubleState, State state) {
        doubleState.previous = doubleState.current;
        doubleState.current = state;
    }
}
