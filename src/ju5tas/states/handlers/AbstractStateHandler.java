package ju5tas.states.handlers;

import ju5tas.states.State;

public abstract class AbstractStateHandler implements StateHandler {

    protected State state = State.TEXT;
    protected StateHandler firstWay = this;
    protected State firstState;
    protected StateHandler secondWay = this;
    protected State secondState;
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

    public void setSecondState(State secondState) {
        this.secondState = secondState;
    }

    public void setFirstWay(StateHandler firstWay) {
        this.firstWay = firstWay;
    }

    public void setSecondWay(StateHandler secondWay) {
        this.secondWay = secondWay;
    }

    public void setFirstSymbol(char firstSymbol) {
        this.firstSymbol = firstSymbol;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

}
