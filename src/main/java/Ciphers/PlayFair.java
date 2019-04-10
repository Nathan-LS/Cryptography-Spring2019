package Ciphers;

public class PlayFair extends CipherAbstractTextBase {
    private String Keyword = "";
    private String key = "";
    private char[][] matrixPF = new char[5][5];


    public void keySet(String k) {
        String format_k = k.toUpperCase();
        String kadjust = "";
        boolean flag = false;
        kadjust = kadjust + format_k.charAt(0);

        for(int i = 1; i < format_k.length(); i++)
        {
            for(int j = 0; j < kadjust.length() - 1; j++)
            {

                if(format_k.charAt(i) == kadjust.charAt(j))
                {
                    flag = true;
                }
            }

            if(!flag)
                kadjust = kadjust + format_k.charAt(i);
            flag = false;
        }

        Keyword = kadjust;
    }

    public void genKey()
    {
        boolean flag = true;
        char current;
        key = Keyword;

        for(int i = 0; i < 26; i++)
        {
            current = (char)(i + 65);
            if(current == 'J')
                continue;

            for(int j = 0; j < Keyword.length(); j++)
            {
                if(current == Keyword.charAt(j))
                {
                    flag = false;
                    break;
                }
            }

            if(flag)
                key = key + current;
            flag = true;
        }
        matrix();
    }

    public void matrix()
    {
        int counter = 0;

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                matrixPF[i][j] = key.charAt(counter);
                counter++;
            }
        }
    }

    public String format(String oldText)
    {
        int i = 0;
        int len = 0;
        String text = "";

        String format_string = oldText.replace(" ", "");
        format_string = format_string.toUpperCase();

        len = format_string.length();

        for(int t = 0; t < len; t++)
        {
            if(format_string.charAt(t) == 'J')
                text = text + 'I';
            else
                text = text + format_string.charAt(t);
        }

        len = text.length();
        if(len % 2 == 0)
        {
            for (i = 0; i < len; i += 2) {
                if (text.charAt(i + 1) == text.charAt(i)) {
                    text = text.substring(0, i + 1) + 'X' + text.substring(i + 2, len);
                }
            }
        }
        else
        {
            for(int j = 0; j < len + 1; j += 2)
            {
                if (text.charAt(j + 1) == text.charAt(j))
                {
                    text = text.substring(0, j + 1) + 'X' + text.substring(j + 2, len) + 'X';
                }
            }
        }
        return text;
    }

    public String[] Pairs(String new_string)
    {
        String original = format(new_string);
        int size = original.length();

        if(size % 2 != 0)
        {
            size++;
            original = original + 'x';
        }

        String[] x = new String[size / 2];

        int counter = 0;

        for (int i = 0; i < size / 2; i++)
        {
            x[i] = original.substring(counter, counter + 2);
            counter += 2;
        }

        return x;
    }

    public int[] getDimensions(char character)
    {
        int[] key = new int[2];

        if (character == 'j')
            character = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++) {
                if (matrixPF[i][j] == character) {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }

    public String encrypt(final String plaintext) {
        keySet(CipherKey);
        genKey();
        String result = "";

        String [] source = Pairs(plaintext);

        char first;
        char second;
        int[] partOne;
        int[] partTwo;

        for(int i = 0; i < source.length; i++)
        {
            first = source[i].charAt(0);
            second = source[i].charAt(1);
            partOne = getDimensions(first);
            partTwo = getDimensions(second);

            if(partOne[0] == partTwo[0])
            {
                if(partOne[1] < 4)
                    partOne[1]++;
                else
                    partOne[1] = 0;
                if(partTwo[1] < 4)
                    partTwo[1]++;
                else
                    partTwo[1] = 0;
            }
            else if (partOne[1] == partTwo[1])
            {
                if(partOne[0] < 4)
                    partOne[0]++;
                else
                    partOne[0] = 0;
                if(partTwo[0] < 4)
                    partTwo[0]++;
                else
                    partTwo[0] = 0;
            }
            else
            {
                int temp = partOne[1];
                partOne[1] = partTwo[1];
                partTwo[1] = temp;
            }
            result = result + matrixPF[partOne[0]][partOne[1]] + matrixPF[partTwo[0]][partTwo[1]];
        }


        return result;
    }

    public String decrypt(final String cipherText) {
        keySet(CipherKey);
        genKey();
        String result = "";

        String [] source = Pairs(cipherText);
        char first;
        char second;
        int[] partOne;
        int[] partTwo;

        for(int i = 0; i < source.length; i++)
        {
            first = source[i].charAt(0);
            second = source[i].charAt(1);
            partOne = getDimensions(first);
            partTwo = getDimensions(second);

            if(partOne[0] == partTwo[0])
            {
                if(partOne[1] > 0)
                    partOne[1]--;
                else
                    partOne[1] = 4;
                if(partTwo[1] > 0)
                    partTwo[1]--;
                else
                    partTwo[1] = 4;
            }
            else if (partOne[1] == partTwo[1])
            {
                if(partOne[0] > 0)
                    partOne[0]--;
                else
                    partOne[0] = 4;
                if(partTwo[0] > 0)
                    partTwo[0]--;
                else
                    partTwo[0] = 4;
            }
            else
            {
                int temp = partOne[1];
                partOne[1] = partTwo[1];
                partTwo[1] = temp;
            }
            result = result + matrixPF[partOne[0]][partOne[1]] + matrixPF[partTwo[0]][partTwo[1]];
        }


        return result;
    }
}
