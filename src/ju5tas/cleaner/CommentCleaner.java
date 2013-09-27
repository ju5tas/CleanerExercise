package ju5tas.cleaner;

import ju5tas.states.StateProcessor;

public class CommentCleaner {

    public String clean(String str) {
        StateProcessor processor = new StateProcessor();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            processor.execute(c);
        }
        return processor.getResult();
    }

}
