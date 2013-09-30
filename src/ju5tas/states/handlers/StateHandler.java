package ju5tas.states.handlers;

import ju5tas.states.State;

public interface StateHandler {

    void setState(State state);

    StateHandler execute(char c);

}
