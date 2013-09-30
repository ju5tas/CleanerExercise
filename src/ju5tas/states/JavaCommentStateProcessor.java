package ju5tas.states;

import ju5tas.states.handlers.CustomStateHandler;
import ju5tas.states.handlers.StateHandler;

public class JavaCommentStateProcessor {

    private StateHandler handler;

    public JavaCommentStateProcessor(boolean quoteSupport) {
        CustomStateHandler text = new CustomStateHandler();
        CustomStateHandler multi = new CustomStateHandler();
        CustomStateHandler aster = new CustomStateHandler();
        CustomStateHandler slash = new CustomStateHandler();
        CustomStateHandler single = new CustomStateHandler();

        if (quoteSupport) {
            CustomStateHandler quote = new CustomStateHandler();
            text.addRule(new CustomStateHandler.Rule('"', quote, State.TEXT));
            text.addRule(new CustomStateHandler.Rule('/', slash, State.COMMENT));
            text.addRule(new CustomStateHandler.Rule(null, null, State.TEXT));

            quote.addRule(new CustomStateHandler.Rule('"', text, State.TEXT));
            quote.addRule(new CustomStateHandler.Rule(null, null, State.TEXT));
        } else {
            text.addRule(new CustomStateHandler.Rule('/', slash, State.COMMENT));
            text.addRule(new CustomStateHandler.Rule(null, null, State.TEXT));
        }
        slash.addRule(new CustomStateHandler.Rule('/', single, State.COMMENT));
        slash.addRule(new CustomStateHandler.Rule('*', multi, State.COMMENT));
        slash.addRule(new CustomStateHandler.Rule(null, text, State.TEXT));
        slash.setPrintCustomChar('/', true);

        single.addRule(new CustomStateHandler.Rule('\n', text, State.TEXT));
        single.addRule(new CustomStateHandler.Rule(null, null, State.COMMENT));

        multi.addRule(new CustomStateHandler.Rule('*', aster, State.COMMENT));
        multi.addRule(new CustomStateHandler.Rule(null, null, State.COMMENT));

        aster.addRule(new CustomStateHandler.Rule('/', text, State.TEXT));
        aster.addRule(new CustomStateHandler.Rule(null, multi, State.COMMENT));
        aster.includeLastChar(false);

        handler = text;
    }

    public void processSymbol(char c) {
        handler = handler.execute(c);
    }

}
