package ju5tas.states.handlers;

import ju5tas.states.State;

import java.util.ArrayList;
import java.util.List;

public class CustomStateHandler implements StateHandler {

    public static class Rule {
        public Rule(Character symbol, StateHandler handler, State state) {
            this.state = state;
            this.handler = handler;
            this.symbol = symbol;
        }

        public State state;
        public StateHandler handler;
        public Character symbol;
    }

    private String name;
    private boolean printCustomChar = false;
    private boolean includeEndChar = true;
    private char customChar;
    protected State state = State.TEXT;

    @Override
    public StateHandler execute(char c) {
        Rule defaultRule = null;
        for (Rule r : rules) {
            if (r.symbol == null) {
                defaultRule = r;
                continue;
            }
            if (c == r.symbol) {
                changeState(r.state, c);
                return r.handler;
            }
        }
        if (defaultRule == null)
            throw new RuntimeException("Не задано правило по-умолчанию: new CustomStateHandler.Rule(null, handler, state) для обрабочика " + getName());
        if (printCustomChar) System.out.print(customChar);
        changeState(defaultRule.state, c);
        return (defaultRule.handler == null ? this : defaultRule.handler);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    protected void changeState(State st, char c) {
        if (st == null) throw new RuntimeException("State не может быть null");
        this.setState(st);
        boolean needPrint = state == State.TEXT && includeEndChar;
        if (needPrint) System.out.print(c);
    }

    public void includeEndChar(boolean includeChar) {
        this.includeEndChar = includeChar;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void printCustomChar(char customChar, boolean printCustomChar) {
        this.printCustomChar = printCustomChar;
        this.customChar = customChar;
    }

}
