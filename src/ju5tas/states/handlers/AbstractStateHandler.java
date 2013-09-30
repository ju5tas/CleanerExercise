package ju5tas.states.handlers;

import ju5tas.states.State;

public abstract class AbstractStateHandler implements StateHandler {

    protected State state = State.TEXT;
    protected StateHandler firstWay = this;
    protected State firstState;
    protected StateHandler elseWay = this;
    protected State elseState;
    protected char firstSymbol;
    private boolean includeChar = true;

    protected void changeState(State st, char c) {
        this.setState(st);
        if (state == State.TEXT && includeChar) System.out.print(c);

    }

    public void setIncludeLastChar(boolean includeChar) {
        this.includeChar = includeChar;
    }

    public void setFirstState(State firstState) {
        this.firstState = firstState;
    }

    public void setElseState(State elseState) {
        this.elseState = elseState;
    }

    public void setFirstWay(StateHandler firstWay) {
        this.firstWay = firstWay;
    }

    public void setElseWay(StateHandler elseWay) {
        this.elseWay = elseWay;
    }

    public void setFirstSymbol(char firstSymbol) {
        this.firstSymbol = firstSymbol;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

}
