package ju5tas.states.handlers;


import ju5tas.states.State;

public class ThreeWayStateHandler extends AbstractStateHandler {

    protected StateHandler secondWay = this;
    protected State secondState;
    protected char secondSymbol;
    private boolean printCustomChar = false;
    private char customChar;

    public void setCustomChar(char customChar) {
        this.customChar = customChar;
    }

    public void setPrintCustomChar(boolean printCustomChar) {
        this.printCustomChar = printCustomChar;
    }

    public void setSecondWay(StateHandler secondWay) {
        this.secondWay = secondWay;
    }

    public void setSecondSymbol(char secondSymbol) {
        this.secondSymbol = secondSymbol;
    }

    public void setSecondState(State secondState) {
        this.secondState = secondState;
    }

    @Override
    public StateHandler execute(char c) {
        if (c == firstSymbol) {
            changeState(firstState, c);
            return firstWay;
        } else if (c == secondSymbol) {
            changeState(secondState, c);
            return secondWay;
        } else {
            if (printCustomChar) System.out.print(customChar);
            changeState(elseState, c);
            return elseWay;
        }
    }

}
