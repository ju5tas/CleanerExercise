package ju5tas.states.handlers;

public class TwoWayStateHandler extends AbstractStateHandler {

    @Override
    public StateHandler execute(char c) {
        if (c == firstSymbol) {
            changeState(firstState, c);
            return firstWay;
        } else {
            changeState(elseState, c);
            return elseWay;
        }
    }

}
