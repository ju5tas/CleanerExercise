package ju5tas.cleaner;

import ju5tas.states.JavaCommentStateProcessor;
import ju5tas.states.SqlCommentStateProcessor;

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

        String sqlText = "select *\n" +
                "from SystemUser -- user information where [login] = 'spanov'\n" +
                "where department <> -1 -- -1 - unknown";

        System.out.println("---ORIGINAL JAVA TEXT---\n");
        System.out.println(javaText);
        System.out.println("\n---QUOTE SUPPORT ON---\n");
        cleanJava(javaText, true);
        System.out.println("\n\n---QUOTE SUPPORT OFF--\n");
        cleanJava(javaText, false);

        System.out.println("\n\n---ORIGINAL SQL TEXT---\n");
        System.out.println(sqlText);
        System.out.println("\n\n---SQL CLEANED---\n");
        cleanSql(sqlText);

    }

    public static void cleanJava(String str, boolean quoteSupport) {
        JavaCommentStateProcessor processor = new JavaCommentStateProcessor(quoteSupport);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            processor.processSymbol(c);
        }
    }

    public static void cleanSql(String str) {
        SqlCommentStateProcessor processor = new SqlCommentStateProcessor();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            processor.processSymbol(c);
        }
    }

}