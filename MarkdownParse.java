
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.naming.ldap.ExtendedRequest;

public class MarkdownParse {
    public static boolean isEscaped(int currentIndex, String markdown) {
        return currentIndex != 0 && markdown.charAt(currentIndex - 1) == '\\';

    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {

            String beforeThis;
            String thisString = String.valueOf(markdown.charAt(currentIndex));
            if (currentIndex == 0) {
                beforeThis = thisString;
            } else {
                beforeThis = String.valueOf(markdown.charAt(currentIndex - 1));

            }
            if ((beforeThis.equals("\\") && thisString.equals("]"))) {
                currentIndex += 2;
            } else {
                int nextOpenBracket = markdown.indexOf("[", currentIndex);
                int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
                int openParen = markdown.indexOf("(", nextCloseBracket);
                int closeParen = markdown.indexOf(")", openParen);
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
                if (markdown.indexOf("[", currentIndex) == -1) {
                    currentIndex = markdown.length();
                }
            }
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
    // ||markdown.charAt(currentIndex)=='}'||markdown.charAt(currentIndex)=='('||markdown.charAt(currentIndex)==')'){
}