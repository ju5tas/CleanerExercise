package ju5tas.states;

import ju5tas.states.handlers.StateHandler;
import ju5tas.states.handlers.TextStateHandler;

public class StateProcessor {

    private char previousChar = 0;
    private StateHandler handler = new TextStateHandler();
    private StringBuilder result = new StringBuilder();

    public void execute(char c) {
        handler = handler.execute(c);
        appendChar(c);
        previousChar = c;
    }

    public String getResult() {
        return result.toString();
    }

    public void resetState() {
        result.delete(0, result.length());
        handler = new TextStateHandler();
        previousChar = 0;
    }

    private void appendChar(char c) {
        DoubleState state = handler.getState();
        if (state.current == State.TEXT && state.previous == State.SLASH) {
            result.append(previousChar);
            result.append(c);
        }
        if (state.current == State.TEXT && state.previous == State.TEXT) {
            result.append(c);
        }
    }
}
