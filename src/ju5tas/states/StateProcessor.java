package ju5tas.states;

import ju5tas.states.handlers.StateHandler;
import ju5tas.states.handlers.TextStateHandler;

public class StateProcessor {

    char prevChar = 0;
    StateHandler handler = new TextStateHandler();
    private StringBuilder result = new StringBuilder();

    public void execute(char c) {
        handler = handler.execute(c);
        appendChar(c);
        prevChar = c;
    }

    public String getResult() {
        return result.toString();
    }

    public void resetState() {
        result.delete(0, result.length());
        handler = new TextStateHandler();
        prevChar = 0;
    }

    private void appendChar(char c) {
        DoubleState state = handler.getState();
        if (state.current == State.TEXT && state.previous == State.SLASH) {
            result.append(prevChar);
            result.append(c);
        }
        if (state.current == State.TEXT && state.previous == State.TEXT) {
            result.append(c);
        }
    }
}
