package ju5tas.states;

import ju5tas.states.handlers.StateHandler;
import ju5tas.states.handlers.ThreeWayStateHandler;
import ju5tas.states.handlers.TwoWayStateHandler;

public class StateProcessor {

    private StateHandler handler;

    {
//        TwoWayStateHandler      text            = new TwoWayStateHandler();
        TwoWayStateHandler singleComment = new TwoWayStateHandler();
        TwoWayStateHandler multiComment = new TwoWayStateHandler();
        TwoWayStateHandler asterisk = new TwoWayStateHandler();
        ThreeWayStateHandler slash = new ThreeWayStateHandler();

        ThreeWayStateHandler text = new ThreeWayStateHandler();
        TwoWayStateHandler quotes = new TwoWayStateHandler();

        text.setFirstSymbol('"');
        text.setFirstWay(quotes);
        text.setFirstState(State.TEXT);
        text.setSecondSymbol('/');
        text.setSecondWay(slash);
        text.setSecondState(State.COMMENT);
        text.setThirdState(State.TEXT);

        quotes.setFirstSymbol('"');
        quotes.setFirstWay(text);
        quotes.setFirstState(State.TEXT);
        quotes.setSecondState(State.TEXT);
//        text.setFirstSymbol('/');
//        text.setFirstWay(slash);
//        text.setFirstState(State.COMMENT);
//        text.setSecondState(State.TEXT);

        slash.setFirstSymbol('/');
        slash.setFirstWay(singleComment);
        slash.setFirstState(State.COMMENT);
        slash.setSecondSymbol('*');
        slash.setSecondWay(multiComment);
        slash.setSecondState(State.COMMENT);
        slash.setThirdWay(text);
        slash.setThirdState(State.TEXT);

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
