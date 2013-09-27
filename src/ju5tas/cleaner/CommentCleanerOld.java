package ju5tas.cleaner;

import ju5tas.states.State;

public class CommentCleanerOld {

    private State state = State.TEXT;
    private State prevState = State.TEXT;
    private char prevChar = 0;
    private StringBuilder result = new StringBuilder();
    private boolean debug = false;

    private void process(char c) {
        switch (state) {
            case TEXT:
                textState(c);
                break;
            case SLASH:
                slashState(c);
                break;
            case SINGLE_COMMENT:
                singleState(c);
                break;
            case MULTI_COMMENT:
                multiState(c);
                break;
            case ASTERISK:
                starState(c);
                break;
        }
        appendChar(c);
        prevChar = c;
    }

    private void appendChar(char c) {
        if (state == State.TEXT && prevState == State.SLASH) {
            result.append(prevChar);
            result.append(c);
        }
        if (state == State.TEXT && prevState == State.TEXT) {
            result.append(c);
        }
        if (debug) {
            System.out.println((c == '\n' || c == '\r' ? "\\n" : c + " ") + " " + state.name() + " [" + prevState.name() + "]");
        }
    }

    private void starState(char c) {
        if (c == '/') {
            prevState = state;
            state = State.TEXT;
        } else {
            prevState = state;
            state = State.MULTI_COMMENT;
        }
    }

    private void multiState(char c) {
        if (c == '*') {
            prevState = state;
            state = State.ASTERISK;
        } else {
            prevState = state;
        }
    }

    private void singleState(char c) {
        if (c == '\n') {
            prevState = State.TEXT;
            state = State.TEXT;
        } else {
            prevState = state;
        }
    }

    private void slashState(char c) {
        switch (c) {
            case '*':
                prevState = state;
                state = State.MULTI_COMMENT;
                break;
            case '/':
                prevState = state;
                state = State.SINGLE_COMMENT;
                break;
            default:
                prevState = state;
                state = State.TEXT;
        }
    }

    private void textState(char c) {
        if (c == '/') {
            prevState = state;
            state = State.SLASH;
        } else {
            prevState = state;
        }
    }

    public String clean(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            process(c);
        }
        return result.toString();
    }

}
