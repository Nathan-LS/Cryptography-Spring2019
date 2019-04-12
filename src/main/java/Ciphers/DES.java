package Ciphers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Vector;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class DES extends CipherAbstractBase {

    // global objects for enc/dec
    private byte des_key[] = new byte[8];
    private static SecretKey keyKey;
    private static Cipher cipher;

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
            if ((des_key[desKeyIndex] = (byte) twoCharToHexByte(keyArray[keyIndex],
                    keyArray[keyIndex + 1])) == 'z')
                return false;

            keyIndex += 2; /* Go to the second pair of characters */
            ++desKeyIndex; /* Increment the index */
        }
            this.keyKey = new SecretKeySpec(des_key, "DES");
            System.out.println("DES key bit length ::: " + des_key.length);
        } catch (Exception e) {
            System.out.println(e);
        }

        //     this.cipher = Cipher.getInstance("DES");

        System.out.print("DES KEY: ");
        for (keyIndex = 0; keyIndex < 8; ++keyIndex) {
            System.out.print(this.des_key[keyIndex] + "||");
        }
        System.out.print("\n");

        return true;
    }
    @Override
    public String encrypt(final String plaintext) {
        
        // Taking file in locally
        File file = new File("/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/plaintext_1.txt");
        byte[] byteFile = new byte[(int)file.length()];

        try {
            //reading plain file into binary
            byteFile = FileUtils.readFileToByteArray(file);
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keyKey);

            //padding only the necessary number of bytes
            byte[] plain = this.padder(byteFile, byteFile.length+(8-byteFile.length%8));
            System.out.println("bytefile length = " + byteFile.length);
            System.out.println("padder worked; plain length " + plain.length);
            
            byte[] encrypted = new byte[byteFile.length * 2];
            for(int i=0; i < plain.length; i+=8){
                byte[] block = this.get_block(plain, 8, i);
                byte[] encryptedBlock = cipher.doFinal(block);
                System.arraycopy(encryptedBlock, 0, encrypted, i*2, 8);
                System.out.println(new String(Base64.getEncoder().encode(encryptedBlock)));
            }

            // encrypted data needs to be encoded in base64 before writing to file
            return new String(Base64.getEncoder().encode(encrypted));
        } catch (Exception e ) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }

    @Override
    public String decrypt(final String cipherText) {
        
        // Taking file in locally
        File file = new File("/Users/hmedina/TerminalProjects/testingCodes/Cryptography/Cryptography-Spring2019/src/test/resources/DES/TestKey/encrypt.txt");
        byte[] byteFile = new byte[(int)file.length()];

        try {
            // encrypted data needs to be decoded from base64 before decrypting
            byteFile = Base64.getDecoder().decode(FileUtils.readFileToString(file));
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, keyKey);

            //padding only the necessary number of bytes
            byte[] plain = this.padder(byteFile, byteFile.length+(8-byteFile.length%8));
            System.out.println("bytefile length = " + byteFile.length);
            System.out.println("padder worked; plain length " + plain.length);

            byte[] encrypted = new byte[byteFile.length * 2];
            // by 16 bytes to skip over weird data
            for(int i=0; i < plain.length; i+=16){
                byte[] block = this.get_block(plain, 8, i);

                byte[] encryptedBlock = cipher.doFinal(block);
                //I'm looking at each block and it should be printing it to output properly
                System.out.println(new String(encryptedBlock));
                System.arraycopy(encryptedBlock, 0, encrypted, i*2, 8);
                System.out.println("ENCRYPYP" + new String(encrypted));
            }
            return new String(encrypted);
        } catch (Exception e ) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
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

