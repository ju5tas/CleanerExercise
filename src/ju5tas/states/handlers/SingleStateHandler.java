package ju5tas.states.handlers;

import ju5tas.states.DoubleState;
import ju5tas.states.State;

public class SingleStateHandler extends AbstractStateHandler {

    public SingleStateHandler() {
        super();
    }

    public SingleStateHandler(DoubleState state) {
        super(state);
    }

    @Override
    public StateHandler execute(char c) {
        if (c == '\n') {
            state.previous = State.TEXT;
            state.current = State.TEXT;
            return new TextStateHandler(state);
        } else {
            state.previous = state.current;
            return this;
        }
    }
}
