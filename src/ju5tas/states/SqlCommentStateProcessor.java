package ju5tas.states;

import ju5tas.states.handlers.CustomStateHandler;
import ju5tas.states.handlers.StateHandler;

public class SqlCommentStateProcessor extends StateProcessor {

    @Override
    public StateHandler configure() {
        CustomStateHandler text = new CustomStateHandler();
        CustomStateHandler minus = new CustomStateHandler();
        CustomStateHandler comment = new CustomStateHandler();

        text.addRule(new CustomStateHandler.Rule('-', minus, State.COMMENT));
        text.addRule(new CustomStateHandler.Rule(null, null, State.TEXT));

        minus.addRule(new CustomStateHandler.Rule('-', comment, State.COMMENT));
        minus.addRule(new CustomStateHandler.Rule(null, text, State.TEXT));
        minus.printCustomChar('-', true);

        comment.addRule(new CustomStateHandler.Rule('\n', text, State.TEXT));
        comment.addRule(new CustomStateHandler.Rule(null, null, State.COMMENT));

        return text;
    }

}
