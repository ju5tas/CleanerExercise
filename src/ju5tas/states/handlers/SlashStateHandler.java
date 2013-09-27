package ju5tas.states.handlers;

import ju5tas.states.DoubleState;
import ju5tas.states.State;

public class SlashStateHandler extends AbstractStateHandler {

    public SlashStateHandler() {
        super();
    }

    public SlashStateHandler(DoubleState state) {
        super(state);
    }

    @Override
    public StateHandler execute(char c) {
        switch (c) {
            case '*':
                changeState(state, State.MULTI_COMMENT);
                return new MultiStateHandler(state);
            case '/':
                changeState(state, State.SINGLE_COMMENT);
                return new SingleStateHandler(state);
            default:
                changeState(state, State.TEXT);
                return new TextStateHandler(state);
        }
    }
}
