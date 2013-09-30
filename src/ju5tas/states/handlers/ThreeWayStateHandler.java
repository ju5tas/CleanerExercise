package ju5tas.states.handlers;


import ju5tas.states.State;

public class ThreeWayStateHandler extends AbstractStateHandler {

    protected StateHandler thirdWay = this;
    protected State thirdState;
    protected char secondSymbol;
    private boolean printCustomChar = false;
    private char customChar;

    public void setCustomChar(char customChar) {
        this.customChar = customChar;
    }

    public void setPrintCustomChar(boolean printCustomChar) {
        this.printCustomChar = printCustomChar;
    }

    public void setThirdWay(StateHandler thirdWay) {
        this.thirdWay = thirdWay;
    }

    public void setSecondSymbol(char secondSymbol) {
        this.secondSymbol = secondSymbol;
    }

    public void setThirdState(State thirdState) {
        this.thirdState = thirdState;
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
            changeState(thirdState, c);
            return thirdWay;
        }
    }

}
