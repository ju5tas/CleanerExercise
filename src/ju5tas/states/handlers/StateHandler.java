package ju5tas.states.handlers;

import ju5tas.states.DoubleState;

public interface StateHandler {

    DoubleState getState();

    StateHandler execute(char c);

}
