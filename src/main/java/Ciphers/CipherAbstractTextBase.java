package Ciphers;


public abstract class CipherAbstractTextBase implements CipherInterface<String> {
    String CipherKey;

    @Override
    public boolean setKey(final String key) {
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
}
