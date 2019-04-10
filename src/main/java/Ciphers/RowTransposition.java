package Ciphers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowTransposition extends CipherAbstractTextBase {
    @Override
    public boolean setKey(String key) {
        if (key.length() == 0) {
            System.err.println("The key length for this cipher must be greater than 0.");
            return false;
        } else {
            CipherKey = key;
            return true;
        }
    }

    @Override
    public String encrypt(final String plaintext) {
        String pt = stripWindowsNewLines(plaintext);
        List<List<Character>> m = new ArrayList<>();
        StringBuilder returnString = new StringBuilder();
        int col = 0;
        for (int i = 0; i < pt.length(); i++) {
            if (col >= CipherKey.length()) {
                col = 0;
            }
            try {
                m.get(col).add(pt.charAt(i));
            } catch (IndexOutOfBoundsException ex) {
                m.add(new ArrayList<>(Arrays.asList(pt.charAt(i))));
            }
            col++;
        }
        m = matrixSwap(m, reorderPositions(), false);
        for (List<Character> r : m) {
            for (Character c : r) {
                stringBuilderAppend(returnString, c);
            }
        }
        return returnString.toString();
    }

    @Override
    public String decrypt(final String cipherText) {
        String cT = stripWindowsNewLines(cipherText);
        StringBuilder returnString = new StringBuilder();
        List<List<Character>> matrix = new ArrayList<>();
        int col = 0;
        int row = 0;
        final int maxRow = (int) Math.ceil((float) cT.length() / CipherKey.length());
        int extra = (maxRow * CipherKey.length()) - cT.length();
        List<Integer> positions = reorderPositions();
        List<Integer> nullPadder = new ArrayList<>();
        while (extra > 0) {
            nullPadder.add(positions.indexOf(CipherKey.length() - extra));
            extra--;
        }
        for (int i = 0; i < cT.length(); i++) {
            if (row >= maxRow) {
                row = 0;
                col++;
            }
            List<Character> rowMatrix;
            try {
                rowMatrix = matrix.get(col);
            } catch (IndexOutOfBoundsException ex) {
                rowMatrix = new ArrayList<>();
                matrix.add(rowMatrix);
            }
            if (nullPadder.contains(col) && row == maxRow - 1) {
                rowMatrix.add(null);
                i = i - 1;
            } else {
                rowMatrix.add(cT.charAt(i));
            }
            row++;
        }
        while (row > 0 && row < maxRow) {
            matrix.get(CipherKey.length() - 1).add(null);
            row++;
        }
        matrix = matrixSwap(matrix, reorderPositions(), true);
        for (int r = 0; r < maxRow; r++) {
            for (int c = 0; c < CipherKey.length(); c++) {
                try {
                    stringBuilderAppend(returnString, matrix.get(c).get(r));
                } catch (IndexOutOfBoundsException ex) {
                    continue;
                }
            }
        }
        return returnString.toString();
    }

    protected List<Integer> reorderPositions() {
        return reorderPositions(CipherKey);
    }

    protected List<Integer> reorderPositions(String s) {
        List<Character> keyArray = new ArrayList<>();
        List<Integer> order = new ArrayList<>();
        StringBuilder reorderedString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            keyArray.add(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            Integer minPos = null;
            Character minChar = null;
            for (int j = 0; j < keyArray.size(); j++) {
                Character compareChar = keyArray.get(j);
                if (minPos == null || minChar == null) {
                    minChar = compareChar;
                    minPos = j;
                    continue;
                }
                if (compareChar != null) {
                    if (Character.toUpperCase(compareChar) < Character.toUpperCase(minChar)) {
                        minPos = j;
                        minChar = compareChar;
                    }
                }
            }
            keyArray.set(minPos, null);
            reorderedString.append(minChar);
            order.add(minPos);
        }
        return order;
    }

    protected List<List<Character>> matrixSwap(List<List<Character>> matrixOld, List<Integer> positions, Boolean inverted) {
        if (positions == null) {
            positions = new ArrayList<>();
            for (int i = 0; i < CipherKey.length(); i++) {
                positions.add(i);
            }
        }
        List<List<Character>> matrixNew = new ArrayList<>();
        for (int index = 0; index < positions.size(); index++) {
            int swapPos = inverted ? positions.indexOf(index) : positions.get(index);
            try {
                matrixNew.add(matrixOld.get(swapPos));
            } catch (IndexOutOfBoundsException ex) {
                for (int p = matrixOld.size(); p <= swapPos; p++) {
                    matrixOld.add(new ArrayList<>());
                }
                matrixNew.add(matrixOld.get(swapPos));
            }
        }
        return matrixNew;
    }
}