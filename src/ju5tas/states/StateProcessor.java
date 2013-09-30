package ju5tas.states;

import ju5tas.states.handlers.StateHandler;

public abstract class StateProcessor {

    private StateHandler handler;

    protected void setHandler(StateHandler handler) {
        this.handler = handler;
    }

    protected abstract void configure();

    public void processSymbol(char c) {
        handler = handler.execute(c);
    }
}
