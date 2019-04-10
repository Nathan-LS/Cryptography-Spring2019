package Ciphers;


public class Vigenre extends CipherAbstractTextBase {
    /* Globals */
    private String stringKey;
    private final Integer size = 26;
    private char[][] board = new char[size][size];

    /* Initialize the board */
    public void initBoard() {
        for (int i = 0; i < size; i++) {
            char ascii_letter = (char)(65 + i);

            for (int j = 0; j < size; j++) {
                board[i][j] = ascii_letter;
                ascii_letter++;

                if (ascii_letter > 'Z') {
                    ascii_letter = 'A';
                }
            }
        }
    }

    @Override
    public boolean setKey(final String key) {
        // Store the key into a string variable
        stringKey = key;

        // Validate the key
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) >= '0' && key.charAt(i) <= '9') {
                System.out.println("You must pass a string consisting of only letters when using Vigenre cipher");
                return false;
            }
        }
        return true;
    }

    public char getEncryptionLetter(char row_letter, char column_letter) {
        // Get the board row
        int board_row = 0;
        for (int row = 0; row < size; row++) {
            if (board[row][0] == row_letter) {
                board_row = row;
                break;
            }
        }
        
        // Get the board column
        int board_col = 0;
        for (int col = 0; col < size; col++) {
            if (board[0][col] == column_letter) {
                board_col = col;
                break;
            }
        }

        // Return the letter
        return board[board_row][board_col];
    }

    public char getDecryptionLetter(char row_letter, char column_letter) {
        // Get the board row
        int board_row = 0;
        for (int row = 0; row < size; row++) {
            if (board[row][0] == row_letter) {
                board_row = row;
                break;
            }
        }

        // Get the board column
        int board_col = 0;
        for (int col = 0; col < size; col++) {
            if (board[board_row][col] == column_letter) {
                board_col = col;
                break;
            }
        }

        return board[0][board_col];
    }

    @Override
    public String encrypt(final String plainText) {
        String pT = plainText.toUpperCase(); // in case input string has lowercase
        initBoard();

        // Final ciphertext result
        String ciphertext = "";

        // The position of what key to use
        int key_pos = 0;

        // Use the plaintext and keyword to get the ciphertext
        for (int i = 0; i < pT.length(); i++) {
            char letter = getEncryptionLetter(pT.charAt(i), stringKey.charAt(key_pos));
            ciphertext += letter ; 
            key_pos = key_pos + 1;
            
            // If we read the last character in the keyword, then position
            // back to the first location
            if (key_pos == stringKey.length()) {
                key_pos = 0;
            }
        }

        // Return the ciphertext
        return ciphertext;
    }

    @Override
    public String decrypt(final String cipherText) {
        String cT = cipherText.toUpperCase(); // in case input string has lowercase
        initBoard();

        // Final plaintext result
        String plaintext = "";

        // The position of what key to sue
        int key_pos = 0;

        // Use the ciphertext and keyword to get the plaintext
        // The keyword is the row and the ciphertext is to be found in the row
        // The plaintext is the column the letter was found at row 0
        for (int i = 0; i < cT.length(); i++) {
            char letter = getDecryptionLetter(stringKey.charAt(key_pos), cT.charAt(i));
            plaintext += letter;
            key_pos = key_pos + 1;

            // If we read the last character in the keyword, then move
            // position back to the first location
            if (key_pos == stringKey.length()) {
                key_pos = 0; 
            }
        }

        // Return the plaintext
        return plaintext;
    }
}
