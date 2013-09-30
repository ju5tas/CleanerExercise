package ju5tas.states;

import ju5tas.states.handlers.StateHandler;
import ju5tas.states.handlers.ThreeWayStateHandler;
import ju5tas.states.handlers.TwoWayStateHandler;

public class StateProcessor {

    private StateHandler handler;

    public StateProcessor(boolean quotesSupport) {
        StateHandler text;
        TwoWayStateHandler singleComment = new TwoWayStateHandler();
        TwoWayStateHandler multiComment = new TwoWayStateHandler();
        TwoWayStateHandler asterisk = new TwoWayStateHandler();
        ThreeWayStateHandler slash = new ThreeWayStateHandler();

        if (quotesSupport) {
            ThreeWayStateHandler textHandler = new ThreeWayStateHandler();
            text = textHandler;
            TwoWayStateHandler quotes = new TwoWayStateHandler();
            textHandler.setFirstSymbol('"');
            textHandler.setFirstWay(quotes);
            textHandler.setFirstState(State.TEXT);
            textHandler.setSecondSymbol('/');
            textHandler.setSecondWay(slash);
            textHandler.setSecondState(State.COMMENT);
            textHandler.setThirdState(State.TEXT);

            quotes.setFirstSymbol('"');
            quotes.setFirstWay(textHandler);
            quotes.setFirstState(State.TEXT);
            quotes.setSecondState(State.TEXT);
        } else {
            TwoWayStateHandler textHandler = new TwoWayStateHandler();
            text = textHandler;
            textHandler.setFirstSymbol('/');
            textHandler.setFirstWay(slash);
            textHandler.setFirstState(State.COMMENT);
            textHandler.setSecondState(State.TEXT);
        }

        slash.setFirstSymbol('/');
        slash.setFirstWay(singleComment);
        slash.setFirstState(State.COMMENT);
        slash.setSecondSymbol('*');
        slash.setSecondWay(multiComment);
        slash.setSecondState(State.COMMENT);
        slash.setThirdWay(text);
        slash.setThirdState(State.TEXT);
        slash.setCustomChar('/');
        slash.setPrintCustomChar(true);

        singleComment.setFirstSymbol('\n');
        singleComment.setFirstWay(text);
        singleComment.setFirstState(State.TEXT);
        singleComment.setSecondState(State.COMMENT);

        multiComment.setFirstSymbol('*');
        multiComment.setFirstWay(asterisk);
        multiComment.setFirstState(State.COMMENT);
        multiComment.setSecondState(State.COMMENT);

        asterisk.setFirstSymbol('/');
        asterisk.setFirstWay(text);
        asterisk.setFirstState(State.TEXT);
        asterisk.setSecondWay(multiComment);
        asterisk.setSecondState(State.COMMENT);
        asterisk.setIncludeLastChar(false);


        handler = text;
    }

    public void processSymbol(char c) {
        handler = handler.execute(c);
    }

}
