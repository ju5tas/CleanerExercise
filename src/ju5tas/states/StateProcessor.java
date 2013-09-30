package ju5tas.states;

import ju5tas.states.handlers.StateHandler;

public abstract class StateProcessor {

    private StateHandler handler;

    public StateProcessor() {
        handler = configure();
    }

    protected void setHandler(StateHandler handler) {
        this.handler = handler;
    }

    protected abstract StateHandler configure();

    public void processSymbol(char c) {
        handler = handler.execute(c);
    }
}