package ju5tas.states.handlers;

public interface StateHandler {

    StateHandler execute(char c);

    void setName(String name);

    String getName();

}
