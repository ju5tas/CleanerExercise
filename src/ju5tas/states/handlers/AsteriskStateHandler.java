package ju5tas.states.handlers;

import ju5tas.states.DoubleState;
import ju5tas.states.State;

public class AsteriskStateHandler extends AbstractStateHandler {

    public AsteriskStateHandler() {
        super();
    }

    public AsteriskStateHandler(DoubleState state) {
        super(state);
    }

    @Override
    public StateHandler execute(char c) {
        if (c == '/') {
            changeState(state, State.TEXT);
            return new TextStateHandler(state);
        } else {
            changeState(state, State.MULTI_COMMENT);
            return new MultiStateHandler(state);
        }
    }
}
