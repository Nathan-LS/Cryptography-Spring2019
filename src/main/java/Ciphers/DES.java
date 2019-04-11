package Ciphers;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Vector;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class DES extends CipherAbstractBase {

    // global objects for enc/dec
    byte des_key[] = new byte[8];
    SecretKey keyKey;
    Cipher cipher;

    @Override
    public boolean setKey(final String key) {

        if (key.length() != 16) {
            System.out.println("Key length must be 16 characters.");
            return false;
        }
        char[] keyArray = key.toCharArray();
        System.out.println(keyArray);

        int keyIndex = 0;        /* The key index */

        int desKeyIndex = 0;        /* The DES key index */

        /* Go through the entire key character by character */
        while (desKeyIndex != 8) {
            /* Convert the key if the character is valid */
            if ((this.des_key[desKeyIndex] = (byte) twoCharToHexByte(keyArray[keyIndex],
                    keyArray[keyIndex + 1])) == 'z')
                return false;

            keyIndex += 2;            /* Go to the second pair of characters */
            ++desKeyIndex;            /* Increment the index */
        }

        System.out.print("DES KEY: ");
        for (keyIndex = 0; keyIndex < 8; ++keyIndex) {
            System.out.print(this.des_key[keyIndex] + "||");
        }
        System.out.print("\n");

        try {
            this.keyKey = new SecretKeySpec(this.des_key, "DES");
            this.cipher = Cipher.getInstance("DES");
        } catch (Exception e) {
            System.out.println("Error on crypto Key init.\n" + e);
        }

        return true;
    }
    @Override
    public String encrypt(final String plaintext) {
        byte[] byteFile = {};
        try {
            byteFile = Files.readAllBytes(new File(
                    "/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/plaintext_1.txt")
                            .toPath());
            // byteFile = new String(byteFile, "UTF-8").getBytes();
            // System.out.println("KEY USED IN ENC ::: " + this.keyKey);
            this.cipher = Cipher.getInstance("DES/ECB/NoPadding");
            this.cipher.init(Cipher.ENCRYPT_MODE, this.keyKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Number of BITS in file ::: " + byteFile.length);
        System.out.println("byteFile in String ::: " + new String(byteFile));

        // Padding values
        int padding = byteFile.length % 8;
        if (padding != 0) {
            byte[] paddedByteFile = new byte[byteFile.length + (8 % padding)];
            for (int i = 0; i < byteFile.length; ++i) {
                paddedByteFile[i] = byteFile[i];
            }
            byteFile = paddedByteFile;
            System.out.println("Number of BITS in file after PADDING::: " + byteFile.length);
            System.out.println("byteFile in String ::: " + new String(byteFile));
        }
        Vector<byte[]> blocks = new Vector<byte[]>();
        byte[] byteBlock = new byte[8];
        int segment = 0;
        int index = 0;
        while (segment != byteFile.length) {
            if (index == 8) {
                blocks.add(byteBlock.clone());
                System.out.println("Block Index " + (segment / 8) + " ::: " + blocks.lastElement());
                index = 0;
            }
            byteBlock[index] = byteFile[segment];
            index++;
            segment++;
        }
        // One last round of blocks to clone
        blocks.add(byteBlock.clone());
        byte[] encryptedData = {};
        String encryptedText = "";
        for (int i = 0; i < blocks.size(); ++i) {
            try {
                System.out.println("block index " + i + " :: ");
                System.out.println("plain block :: " + new String(blocks.get(i)));
                System.out.println("LENGTH OF THE BYTEs +++ " + blocks.get(i).length);
                encryptedData = this.cipher.update(blocks.get(i));
                System.out.println("encrypted block :: " + new String(encryptedData));
                System.out.println("BASE64 encoding block :: " + Base64.getEncoder().encodeToString(encryptedData));
                encryptedText += new String(encryptedData, "UTF-8");
                // encryptedText += Base64.getEncoder().encodeToString(encryptedData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            this.cipher.doFinal();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }

    @Override
    public String decrypt(final String cipherText) {
        byte[] byteFile = {};
        try {
            byteFile = Files.readAllBytes(new File(
                    "/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/encrypt_1")
                            .toPath());
            System.out.println("KEY USED IN DEC ::: " + this.keyKey);
            this.cipher = Cipher.getInstance("DES/ECB/NoPadding");
            this.cipher.init(Cipher.DECRYPT_MODE, this.keyKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byteFile = cipherText.getBytes();
        System.out.println("Number of BITS in file ::: " + byteFile.length);
        System.out.println("byteFile in String ::: " + new String(byteFile));

        // Padding values
        int padding = byteFile.length % 8;
        if (padding != 0) {
            byte[] paddedByteFile = new byte[byteFile.length + (8 % padding)];
            for (int i = 0; i < byteFile.length; ++i) {
                paddedByteFile[i] = byteFile[i];
            }
            byteFile = paddedByteFile;
            System.out.println("Number of BITS in file after PADDING::: " + byteFile.length);
            System.out.println("byteFile in String ::: " + new String(byteFile));
        }
        Vector<byte[]> blocks = new Vector<byte[]>();
        byte[] byteBlock = new byte[8];
        int segment = 0;
        int index = 0;
        while (segment != byteFile.length) {
            if (index == 8) {
                blocks.add(byteBlock.clone());
                System.out.println("Block Index " + (segment / 8) + " ::: " + blocks.lastElement());
                index = 0;
            }
            byteBlock[index] = byteFile[segment];
            index++;
            segment++;
        }
        // One last round of blocks to clone
        blocks.add(byteBlock.clone());
        byte[] encryptedData = {};
        String encryptedText = "";
        for (int i = 0; i < blocks.size(); ++i) {
            try {
                System.out.println("block index " + i + " :: ");
                System.out.println("encrypted block :: " + new String(blocks.get(i)));
                encryptedData = this.cipher.update(blocks.get(i));
                System.out.println("decrypted block :: " + new String(encryptedData, "UTF-8"));
                encryptedText += new String(encryptedData, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            this.cipher.doFinal();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }

    /**
	* Converts two characters into a hex integers
	* and then inserts the integers into the higher
	* and lower bits of the byte
	*/
    byte twoCharToHexByte(final char firstChar, final char secondChar) {
        byte singleByte;
        byte nextChar;

        /* Convert the first character */
        if((singleByte = (byte) charToHex(firstChar)) == 'z') {
            /* Invalid digit */
            return 'z';
        }
        /* Move the newly inserted nibble from the
	    * lower to upper nibble.
        */
        singleByte <<= 4;

        if((nextChar = (byte) charToHex(secondChar)) == 'z') {
            return 'z';
        }
        /* Insert the second value into the lower nibble */	
        singleByte |= nextChar;
        
        return singleByte;
    }

    /**
	* Converts a character into a hexidecimal integer
	* @param character - the character to convert
	* @return - the converted character, or 'z' on error
    */
    char charToHex(final char character) {

        /* Is the first digit 0-9 ? */	
	    if(character >= '0' && character <= '9')	
            /* Convert the character to hex */
            return (char)(character - '0');
        /* It the first digit a letter 'a' - 'f'? */
        else if(character >= 'a' && character <= 'f')
            /* Conver the cgaracter to hex */
            return (char)((character - 97) + 10);	
        /* Invalid character */
        else return 'z';
    }
 }

