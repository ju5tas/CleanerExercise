package ju5tas.cleaner;

import ju5tas.states.StateProcessor;

public class Application {

    public static void main(String[] args) {

        String text = "package hello; /* multiline */ ok //\n" +
                "/** javadoc\n" +
                "* //doc \n" +
                "*/\n" +
                "public void text() // simple comment \n" +
                "text /* asd // adsddasd */ \n" +
                "System.out.println(\"/*asd*/text//sadasd\");\n" +
                "int a = 6 / 3 * 2; // a = 4";

        System.out.println("\n----ORIGINAL TEXT-----\n" + text + "\n---QUOTE SUPPORT ON---");
        clean(text, true);
        System.out.println("\n\n---QUOTE SUPPORT OFF--");
        clean(text, false);

    }

    public static void clean(String str, boolean quoteSupport) {
        StateProcessor processor = new StateProcessor(quoteSupport);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            processor.processSymbol(c);
        }
    }

}