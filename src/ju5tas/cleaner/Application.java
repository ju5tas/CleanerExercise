package ju5tas.cleaner;

import ju5tas.states.StateProcessor;

public class Application {

    public static void main(String[] args) {

        String text = "package hello; /* multiline */ import ok; //\n" +
                "/*asd*/class q12345 {//wert / / / / \n" +
                "   String str = \"Hello world!\"; double d =  1/2/ 3; // / /\n" +
                "/** javadoc\n" +
                "* //doc \n" +
                "*/\n" +
                "public void text() { // simple comment \n" +
                "   text(); /* asd // adsddasd */ \n" +
                "   System.out.println(\"/*asd*/text//sadasd\");\n" +
                "   int a = 6 / 3 * 2; \n" +
                "} }// a = 4";

        System.out.println("----ORIGINAL TEXT-----\n");
        System.out.println(text);
        System.out.println("\n---QUOTE SUPPORT ON---\n");
        clean(text, true);
        System.out.println("\n\n---QUOTE SUPPORT OFF--\n");
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