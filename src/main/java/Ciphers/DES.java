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

public class DES extends CipherAbstractByteBase {

    // global objects for enc/dec
    private byte des_key[] = new byte[8];
    private static SecretKey keyKey;
    private static Cipher cipher;

    @Override
    public boolean setKey(final String key) {

        if (key.length() != 16) {
            System.out.println("Key length must be 16 characters." + " for key " + key);
            return false;
        }
        //Turning 16 bit key to 8 bit key
        int keyIndex = 0; /* The key index */
        char[] keyArray = key.toCharArray();
        int desKeyIndex = 0; /* The DES key index */
        try {
        while (desKeyIndex != 8) {
            /* Convert the key if the character is valid */
            System.out.println(keyArray[keyIndex] + " " + keyArray[keyIndex + 1]);
            if ((des_key[desKeyIndex] = (byte)twoCharToHexByte(keyArray[keyIndex], keyArray[keyIndex + 1])) == 'z') {
                System.out.println("twoCharToHexByte returns " + des_key[desKeyIndex] + ", false will be returned.");
                return false;
            }
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
    public byte[] encrypt(final byte[] plaintext){
        byte[] byteFile;

        try {
            //reading plain file into binary
            byteFile = plaintext;
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keyKey);

            //padding only the necessary number of bytes
            byte[] plain = this.padder(byteFile, byteFile.length+(8-byteFile.length%8));
            System.out.println("bytefile length = " + byteFile.length);
            System.out.println("padder worked; plain length " + plain.length);

            byte[] encrypted = new byte[plain.length];
            for(int i=0; i < plain.length; i+=8){
                byte[] block = this.get_block(plain, 8, i);
                byte[] encryptedBlock = cipher.doFinal(block);
                System.arraycopy(encryptedBlock, 0, encrypted, i, 8);
                System.out.println(new String(Base64.getEncoder().encode(encryptedBlock)));
                System.out.println(new String(Base64.getEncoder().encode(encrypted)));

            }

            // encrypted data needs to be encoded in base64 before writing to file
            return encrypted;
        } catch (Exception e ) {
            System.out.println("Error while encrypting: " + e.toString());
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] cipherText) {
        byte[] byteFile;

        try {
            // encrypted data needs to be decoded from base64 before decrypting
            byteFile = cipherText;
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, keyKey);

            //padding only the necessary number of bytes
            byte[] plain = this.padder(byteFile, byteFile.length+(8-byteFile.length%8));
            System.out.println("bytefile length = " + byteFile.length);
            System.out.println("padder worked; plain length " + plain.length);

            byte[] encrypted = new byte[plain.length];
            // by 16 bytes to skip over weird data
            for(int i=0; i < plain.length; i+=8){
                byte[] block = this.get_block(plain, 8, i);

                byte[] encryptedBlock = cipher.doFinal(block);
                //I'm looking at each block and it should be printing it to output properly
                System.out.println(new String(encryptedBlock));
                System.arraycopy(encryptedBlock, 0, encrypted, i, 8);
                System.out.println("DECRYPTED TEXT:: " + new String(encrypted));
            }

            /* NOTE: Updated version: Remove the last 8 bytes of padding */
            byte[] result = new byte[encrypted.length - (8*4)];
            System.arraycopy(encrypted, 0, result, 0, encrypted.length - (8*4));
            return result;

            /* NOTE: Original version: Remove the block above to revert */
            //return encrypted;

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
            System.out.println("charToHex(firstChar) returning z with " + singleByte);
            return 'z';
        }
        /* Move the newly inserted nibble from the
	    * lower to upper nibble.
        */
        singleByte <<= 4;

        if((nextChar = (byte) charToHex(secondChar)) == 'z') {
            System.out.println("charToHex(secondChar) returning z with " + singleByte);
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

      // NOTE: Should we possibly be checking A-Z and a-z instead??

        /* Invalid character */
      else return 'z';
    }
 }
