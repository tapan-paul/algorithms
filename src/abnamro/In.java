package abnamro;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @tapan .
 */
public class In {

    // assume Unicode UTF-8 encoding
    private static final String CHARSET_NAME = "UTF-8";

    private static final Pattern WHITESPACE_PATTERN
            = Pattern.compile("\\p{javaWhitespace}+");

    private Scanner scanner;

    /**
     * Initializes an input stream from standard input.
     */
    public In() {
        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
    }

    /**
     * Initializes an input stream from a filename
     *
     * @param  s the filename
     */
    public In(String s) {
        try {
            // first try to read file from local file system
            File file = new File(s);
            if (file.exists()) {
                scanner = new Scanner(file, CHARSET_NAME);
                return;
            }
        }
        catch (IOException ioe) {
            System.err.println("Could not open " + s);
        }
    }

    public boolean isEmpty() {
        return !scanner.hasNext();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Reads and returns the next line in this input stream.
     *
     * @return the next line in this input stream; <tt>null</tt> if no such line
     */
    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        }
        catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }


    public int readInt() {
        return scanner.nextInt();
    }

    public char readChar() {
        scanner.useDelimiter("");
        String ch = scanner.next();
        assert ch.length() == 1 : "Internal (Std)In.readChar() error!"
                + " Please contact the authors.";
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return ch.charAt(0);
    }
}
