package ju5tas.states.handlers;

import ju5tas.states.State;

public abstract class AbstractStateHandler implements StateHandler {

    protected State state = State.TEXT;
    private boolean includeChar = true;

    protected void changeState(State st, char c) {
        if (st == null) throw new RuntimeException("State не может быть null");
        this.setState(st);
        if (state == State.TEXT && includeChar) System.out.print(c);
    }

    public void includeLastChar(boolean includeChar) {
        this.includeChar = includeChar;
    }

    public void setState(State state) {
        this.state = state;
    }

}
