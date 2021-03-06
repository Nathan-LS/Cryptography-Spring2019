package Ciphers;

import static java.lang.Math.floorMod;

public class Caesar extends CipherAbstractTextBase {
    private Integer intKey;

    @Override
    public boolean setKey(final String key) {
        try {
            intKey = Integer.parseInt(key);
        } catch (NumberFormatException ex) {
            System.out.println("You must pass a valid integer as the key when using the Caesar cipher.");
            return false;
        }
        if (intKey >= 27) {
            System.out.println("The maximum key shift is 26 for the Caesar cipher.");
            return false;
        } else if (intKey < 0) {
            System.out.println("You must provide a positive number key to the Caesar cipher.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String encrypt(final String plaintext) {
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (!Character.isLetter(c)) {
                returnString.append(c);
                continue;
            }
            int ascii_value = (int) plaintext.charAt(i);
            int new_ascii;
            if (!Character.isLowerCase(c)) {
                new_ascii = floorMod((ascii_value - 65) + intKey, 26) + 65;
            } else {
                new_ascii = floorMod((ascii_value - 97) + intKey, 26) + 97;
            }
            returnString.append((char) new_ascii);
        }
        return returnString.toString();
    }

    @Override
    public String decrypt(final String cipherText) {
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (!Character.isLetter(c)) {
                returnString.append(c);
                continue;
            }
            int ascii_value = (int) c;
            int new_ascii;
            if (!Character.isLowerCase(c)) {
                new_ascii = floorMod((ascii_value - 65) - intKey, 26) + 65;
            } else {
                new_ascii = floorMod((ascii_value - 97) - intKey, 26) + 97;
            }
            returnString.append((char) new_ascii);
        }
        return returnString.toString();
    }
}
