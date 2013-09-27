package ju5tas.states.handlers;

import ju5tas.states.DoubleState;
import ju5tas.states.State;

public class MultiStateHandler extends AbstractStateHandler {

    public MultiStateHandler() {
        super();
    }

    public MultiStateHandler(DoubleState state) {
        super(state);
    }

    @Override
    public StateHandler execute(char c) {
        if (c == '*') {
            changeState(state, State.ASTERISK);
            return new AsteriskStateHandler(state);
        } else {
            state.previous = state.current;
            return this;
        }
    }
}
