package ju5tas.cleaner;

import ju5tas.states.JavaCommentStateProcessor;
import ju5tas.states.StateProcessor;
import ju5tas.states.XmlStateProcessor;

public class Application {

    public static void main(String[] args) {

        String javaText = "package hello; /* multiline */ import ok; //\n" +
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

        System.out.println("---ORIGINAL JAVA TEXT---\n");
        System.out.println(javaText);
        System.out.println("\n---XML CONFIG---\n");
        cleanJava2(javaText);
        System.out.println("\n\n---CONSTRUCTOR CONFIG---\n");
        cleanJava(javaText, true);
    }

    public static void cleanJava(String str, boolean quoteSupport) {
        StateProcessor processor = new JavaCommentStateProcessor(quoteSupport);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            processor.processSymbol(c);
        }
    }

    public static void cleanJava2(String str) {
        StateProcessor processor = new XmlStateProcessor("conf.xml");
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            processor.processSymbol(c);
        }
    }

}