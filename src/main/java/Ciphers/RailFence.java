package Ciphers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RailFence extends CipherAbstractTextBase {
    protected Integer intKey;
    protected Integer row = 0;
    protected Integer col = 0;
    protected Boolean desc = true;

    @Override
    public boolean setKey(final String key) {
        try {
            intKey = Integer.parseInt(key);
        } catch (NumberFormatException ex) {
            System.out.println("You must pass a valid integer as the key when using the RailFenceInverted cipher.");
            return false;
        }
        if (intKey <= 0) {
            System.out.println("You must pass an integer greater than 0 when using the RailFenceInverted cipher");
            return false;
        }
        return true;
    }

    private void resetRowCol() {
        row = 0;
        col = 0;
        desc = true;
    }

    protected void next_position() {
        if (intKey == 1) {
            col++;
        } else {
            if (row >= intKey - 1) {
                row = 0;
                col++;
            } else {
                row++;
            }
        }
    }

    @Override
    public String encrypt(final String plaintext) {
        String plainText = stripWindowsNewLines(plaintext);
        resetRowCol();
        Character[][] matrix = new Character[intKey][plainText.length()];
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            matrix[row][col] = plainText.charAt(i);
            next_position();
        }
        for (int i = 0; i < intKey; i++) {
            for (int j = 0; j < plainText.length(); j++) {
                Character c = matrix[i][j];
                stringBuilderAppend(returnString, c);
            }
        }
        return returnString.toString();
    }

    @Override
    public String decrypt(final String cipherText) {
        String ciphertext = stripWindowsNewLines(cipherText);
        resetRowCol();
        Character[][] matrix = new Character[intKey][ciphertext.length()];
        List<Queue<Integer>> availablePositions = new ArrayList<>();
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < intKey; i++) {
            availablePositions.add(new LinkedList<>());
        }
        for (int i = 0; i < ciphertext.length(); i++) {
            availablePositions.get(row).add(col);
            next_position();
        }
        Integer rowIndex = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            Queue<Integer> availableRow = availablePositions.get(rowIndex);
            while (availableRow.isEmpty()) {
                rowIndex++;
                assert (!rowIndex.equals(intKey));
                availableRow = availablePositions.get(rowIndex);
            }
            matrix[rowIndex][availableRow.remove()] = ciphertext.charAt(i);
        }
        resetRowCol();
        for (int i = 0; i < ciphertext.length(); i++) {
            Character c = matrix[row][col];
            stringBuilderAppend(returnString, c);
            next_position();
        }
        return returnString.toString();
    }
}
