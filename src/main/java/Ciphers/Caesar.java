package Ciphers;

import static java.lang.Math.floorMod;

public class Caesar extends CipherAbstractBase {
    private Integer intKey;

    @Override
    public boolean setKey(String key) {
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
    public String encrypt(String plaintext) {
        String return_string = "";
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (!Character.isLetter(c)) {
                return_string += c;
                continue;
            }
            int ascii_value = (int) plaintext.charAt(i);
            int new_ascii;
            if (!Character.isLowerCase(c)) {
                new_ascii = floorMod((ascii_value - 65) + intKey, 26) + 65;
            } else {
                new_ascii = floorMod((ascii_value - 97) + intKey, 26) + 97;
            }
            return_string += (char) new_ascii;
        }
        return return_string;
    }

    @Override
    public String decrypt(String ciphertext) {
        String return_string = "";
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            if (!Character.isLetter(c)) {
                return_string += c;
                continue;
            }
            int ascii_value = (int) c;
            int new_ascii;
            if (!Character.isLowerCase(c)) {
                new_ascii = floorMod((ascii_value - 65) - intKey, 26) + 65;
            } else {
                new_ascii = floorMod((ascii_value - 97) - intKey, 26) + 97;
            }
            return_string += (char) new_ascii;
        }
        return return_string;
    }
}
