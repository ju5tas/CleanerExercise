package ju5tas.states.handlers;

import ju5tas.states.State;

import java.util.ArrayList;
import java.util.List;

public class CustomStateHandler extends AbstractStateHandler {

    private boolean printCustomChar = false;
    private char customChar;

    public void printCustomChar(char customChar, boolean printCustomChar) {
        this.printCustomChar = printCustomChar;
        this.customChar = customChar;
    }

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
            throw new RuntimeException("Не задан обработчик по-умолчанию: new CustomStateHandler.Rule(null, handler, state)");
        if (printCustomChar) System.out.print(customChar);
        changeState(defaultRule.state, c);
        return (defaultRule.handler == null ? this : defaultRule.handler);
    }

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

    public List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }


}
