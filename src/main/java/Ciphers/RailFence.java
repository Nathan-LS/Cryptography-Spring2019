package Ciphers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RailFence extends CipherAbstractBase {
    private Integer intKey;
    private Integer row = 0;
    private Integer col = 0;
    private Boolean desc = true;

    @Override
    public boolean setKey(String key) {
        try {
            intKey = Integer.parseInt(key);
        } catch (NumberFormatException ex) {
            System.out.println("You must pass a valid integer as the key when using the RailFence cipher.");
            return false;
        }
        if (intKey <= 0) {
            System.out.println("You must pass an integer greater than 0 when using the RailFence cipher");
            return false;
        }
        return true;
    }

    private void resetRowCol() {
        row = 0;
        col = 0;
        desc = true;
    }

    private void next_position() {
        if (intKey == 1) {
            col++;
        } else if (desc) {
            if (row >= intKey - 1) {
                desc = false;
                row--;
                col++;
            } else {
                row++;
            }
        } else {
            if (row <= 0) {
                desc = true;
                row++;
                col++;
            } else {
                row--;
            }
        }
    }

    @Override
    public String encrypt(String plaintext) {
        resetRowCol();
        Character[][] matrix = new Character[intKey][plaintext.length()];
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            matrix[row][col] = plaintext.charAt(i);
            next_position();
        }
        for (int i = 0; i < intKey; i++) {
            for (int j = 0; j < plaintext.length(); j++) {
                Character c = matrix[i][j];
                if (c != null) {
                    returnString.append(c);
                }
            }
        }
        return returnString.toString();
    }

    @Override
    public String decrypt(final String cipherText) {
        resetRowCol();
        Character[][] matrix = new Character[intKey][cipherText.length()];
        List<Queue<Integer>> availablePositions = new ArrayList<>();
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < intKey; i++) {
            availablePositions.add(new LinkedList<>());
        }
        for (int i = 0; i < cipherText.length(); i++) {
            availablePositions.get(row).add(col);
            next_position();
        }
        Integer rowIndex = 0;
        for (int i = 0; i < cipherText.length(); i++) {
            Queue<Integer> availableRow = availablePositions.get(rowIndex);
            while (availableRow.isEmpty()) {
                rowIndex++;
                assert (!rowIndex.equals(intKey));
                availableRow = availablePositions.get(rowIndex);
            }
            matrix[rowIndex][availableRow.remove()] = cipherText.charAt(i);
        }
        resetRowCol();
        for (int i = 0; i < cipherText.length(); i++) {
            Character c = matrix[row][col];
            if (c != null) {
                returnString.append(c);
            }
            next_position();
        }
        return returnString.toString();
    }
}
