package ju5tas.states;

public class DoubleState {
    public State current = State.TEXT;
    public State previous = State.TEXT;

    @Override
    public String toString() {
        return "[" + current.name() + "][" + previous.name() + "]";
    }
}
