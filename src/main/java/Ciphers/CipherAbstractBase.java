package Ciphers;


import java.util.ArrayList;
import java.util.List;

public abstract class CipherAbstractBase implements CipherInterface {
    String CipherKey;

    CipherAbstractBase() {
    }

    @Override
    public boolean setKey(final String key) {  // TODO: 2/23/2019  what is a valid key?
        CipherKey = key;
        return true;
    }

    static protected String stripWindowsNewLines(String inputString) {
        return inputString.replace("\r", "");
    }

    static protected void stringBuilderAppend(StringBuilder builder, Character c) {
        if (c != null) {
            if (c == '\n') {
                builder.append(System.getProperty("line.separator"));
            } else {
                builder.append(c);
            }
        }
    }

    static public List<Character> stringToList(String s) {
        List<Character> returnString = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            returnString.add(s.charAt(i));
        }
        return returnString;
    }
}
