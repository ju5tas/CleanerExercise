package ju5tas.cleaner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ju5tas.cleaner.Application //class
 */
public class Application {

    public static final String fileName = "C:\\Projects\\Parser\\src\\ju5tas\\cleaner\\Application.java";
    //= "C:\\test.txt";

    /**
     * Method main
     */
    public static void main(String[] args) {

        String text = null;
        try {
            text = readFile();
        } catch (IOException e) {
            text = "";
        }
        int a = 6 / 3 * 2;
        System.out.println(text + "\n--------");
        CommentCleaner cleaner = new CommentCleaner();
        String s = cleaner.clean(text);
        System.out.println(s);

    }

    /**
     * Read file
     * Comments will be deleted // like this too
     */
    private static String readFile() throws IOException {

        StringBuilder builder = new StringBuilder(); /* This comment //will remove*/

        InputStream inputstream = new FileInputStream(fileName);
        int data = inputstream.read();
        char content;
        while (data != -1) {
            content = (char) data;
            builder.append(content);
            data = /* comment */inputstream.read(); //comment /*123*/ lol
        }

        inputstream.close();

        // And this
        /// and this
        return builder.toString();
        //// and this too
    }
}/*This too*/