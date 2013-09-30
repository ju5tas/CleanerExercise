package ju5tas.states;

import ju5tas.states.handlers.CustomStateHandler;
import ju5tas.states.handlers.StateHandler;

public class SqlCommentStateProcessor {

    private StateHandler handler;

    public SqlCommentStateProcessor() {
        CustomStateHandler text = new CustomStateHandler();
        CustomStateHandler minus = new CustomStateHandler();
        CustomStateHandler comment = new CustomStateHandler();

        text.addRule(new CustomStateHandler.Rule('-', minus, State.COMMENT));
        text.addRule(new CustomStateHandler.Rule(null, null, State.TEXT));

        minus.addRule(new CustomStateHandler.Rule('-', comment, State.COMMENT));
        minus.addRule(new CustomStateHandler.Rule(null, text, State.TEXT));
        minus.setPrintCustomChar('-', true);

        comment.addRule(new CustomStateHandler.Rule('\n', text, State.TEXT));
        comment.addRule(new CustomStateHandler.Rule(null, null, State.COMMENT));

        handler = text;
    }

    public void processSymbol(char c) {
        handler = handler.execute(c);
    }

}
