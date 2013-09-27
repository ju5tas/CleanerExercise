package ju5tas.states.handlers;

import ju5tas.states.DoubleState;
import ju5tas.states.State;

public class TextStateHandler extends AbstractStateHandler {

    public TextStateHandler(DoubleState state) {
        super(state);
    }

    public TextStateHandler() {
        super();
    }

    @Override
    public StateHandler execute(char c) {
        if (c == '/') {
            changeState(state, State.SLASH);
            return new SlashStateHandler(state);
        } else {
            state.previous = state.current;
            return this;
        }
    }
}
