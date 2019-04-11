package Ciphers;

import java.io.File;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
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
        //Turning 16 bit key to 8 bit key
        int keyIndex = 0; /* The key index */
        char[] keyArray = key.toCharArray();
        int desKeyIndex = 0; /* The DES key index */
        try {
        while (desKeyIndex != 8) {
            /* Convert the key if the character is valid */
            if ((this.des_key[desKeyIndex] = (byte) twoCharToHexByte(keyArray[keyIndex],
                    keyArray[keyIndex + 1])) == 'z')
                return false;

            keyIndex += 2; /* Go to the second pair of characters */
            ++desKeyIndex; /* Increment the index */
        }
            this.keyKey = new SecretKeySpec(this.des_key, "DES");
            System.out.println("DES key bit length ::: " + this.des_key.length);
        } catch (Exception e) {
            System.out.println(e);
        }

        //     this.cipher = Cipher.getInstance("DES");
        // System.out.print("DES KEY: ");
        // for (keyIndex = 0; keyIndex < 8; ++keyIndex) {
        //     System.out.print(this.des_key[keyIndex] + "||");
        // }
        // System.out.print("\n");

        return true;
    }
    @Override
    public String encrypt(final String plaintext) {
        File file = new File("/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/plaintext_1.txt");
        byte[] byteFile = new byte[(int)file.length()];
        try {
            DataInputStream dataStream = new DataInputStream(new BufferedInputStream(new FileInputStream("/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/plaintext_1.txt")));
            dataStream.readFully(byteFile);
            dataStream.close();
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.keyKey);
            byte[] plain = this.padder(byteFile, 128);
            byte[] encrypted = new byte[byteFile.length * 2];
            for(int i=0; i < byteFile.length; i+=8){
                byte[] block = this.get_block(byteFile, 8, i);
                byte[] encryptedBlock = cipher.doFinal(block);
                System.arraycopy(encryptedBlock, 0, encrypted, i*2, 32);
            }
            return new String(encrypted, "UTF-8");
        } catch (Exception e ) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }

        // System.out.println("Number of BITS in file ::: " + byteFile.length);
        // System.out.println("byteFile in String ::: " + new String(byteFile));

        // // Padding values
        // int padding = byteFile.length % 8;
        // if (padding != 0) {
        //     byte[] paddedByteFile = new byte[byteFile.length + (8-byteFile.length%8)];
        //     for (int i = 0; i < byteFile.length; ++i) {
        //         paddedByteFile[i] = byteFile[i];
        //     }
        //     for (int i = byteFile.length; i < byteFile.length + (8-byteFile.length%8); ++i){
        //         paddedByteFile[i] = (byte) 0x00;
        //     }
        //     byteFile = paddedByteFile;
        //     System.out.println("Number of BITS in file after PADDING::: " + byteFile.length);
        //     System.out.println("byteFile in String ::: " + new String(byteFile));
        // }

        // Vector<byte[]> blocks = new Vector<byte[]>();
        // byte[] byteBlock = new byte[8];
        // int segment = 0;
        // int index = 0;
        // while (segment != byteFile.length) {
        //     if (index == 8) {
        //         blocks.add(byteBlock.clone());
        //         System.out.println("Block Index " + (segment / 8) + " ::: " + blocks.lastElement());
        //         index = 0;
        //     }
        //     byteBlock[index] = byteFile[segment];
        //     index++;
        //     segment++;
        // }
        // // One last round of blocks to clone
        // blocks.add(byteBlock.clone());
        // byte[] encryptedData = {};
        // String encryptedText = "";
        // for (int i = 0; i < blocks.size(); ++i) {
        //     try {
        //         System.out.println("block index " + i + " :: ");
        //         System.out.println("plain block :: " + new String(blocks.get(i)));
        //         System.out.println("LENGTH OF THE BYTEs +++ " + blocks.get(i).length);
        //         encryptedData = this.cipher.update(blocks.get(i));
        //         System.out.println("encrypted block :: " + new String(encryptedData));
        //         System.out.println("BASE64 encoding block :: " + Base64.getEncoder().encodeToString(encryptedData));
        //         encryptedText += new String(encryptedData, "UTF-8");
        //         // encryptedText += Base64.getEncoder().encodeToString(encryptedData);

        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }
        // try {
        //     this.cipher.doFinal();
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    //     return encryptedText;
    // }

    @Override
    public String decrypt(final String cipherText) {
        File file = new File("/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/encrypt.txt");
        byte[] byteFile = new byte[(int)file.length()];
        try {
            DataInputStream dataStream = new DataInputStream(new BufferedInputStream(new FileInputStream("/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/encrypt.txt")));
            dataStream.readFully(byteFile);
            dataStream.close();
            this.cipher = Cipher.getInstance("DES/ECB/NoPadding");
            this.cipher.init(Cipher.ENCRYPT_MODE, this.keyKey);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Number of BITS in file ::: " + byteFile.length);
        System.out.println("byteFile in String ::: " + new String(byteFile));

        // Padding values
        int padding = byteFile.length % 8;
        if (padding != 0) {
            byte[] paddedByteFile = new byte[byteFile.length + (8-byteFile.length%8)];
            for (int i = 0; i < byteFile.length; ++i) {
                paddedByteFile[i] = byteFile[i];
            }
            for (int i = byteFile.length; i < byteFile.length + (8-byteFile.length%8); ++i){
                paddedByteFile[i] = (byte) 0x00;
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

    public byte[] padder(final byte[] inputBytes, Integer length){
        int size;
        if (inputBytes.length <= length){
            size = length;
        }else{
            size = (int)Math.ceil((double)inputBytes.length / length) * length;
        }
        byte[] returnArray = new byte[size];
        for (int i = 0; i < inputBytes.length; i++){
            returnArray[i] = inputBytes[i];
        }
        for (int i = inputBytes.length; i <size; i++){
            returnArray[i] = 0;
        }
        return returnArray;
    }

    public byte[] get_block(final byte[] inputBytes, int blockSize, int currentIndex){
        if (currentIndex + blockSize > inputBytes.length){
            throw new IndexOutOfBoundsException("Attempting get a block that would be out of bounds. Check your index or padding function.");
        }else{
            byte[] r = new byte[blockSize];
            for (int i = 0; i < blockSize; i++){
                r[i] = inputBytes[i+currentIndex];
            }
            return r;
        }
    }
 }

