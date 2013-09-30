package ju5tas.states.handlers;


import ju5tas.states.State;

public class ThreeWayStateHandler extends AbstractStateHandler {

    protected StateHandler thirdWay = this;
    protected State thirdState;
    protected char secondSymbol;

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
            //System.out.print("/");
            changeState(thirdState, c);
            return thirdWay;
        }
    }

}
