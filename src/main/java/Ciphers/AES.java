package Ciphers;


public class AES extends CipherAbstractByteBase {

    @Override
    public boolean setKey(final String key) {
        if (key.length() != 16){
            System.out.println("Key length must be 16 characters.");
            return false;
        }else{
            CipherKey = key;
            return true;
        }
    }

    @Override
    public byte[] encrypt(final byte[] plaintext) {
        return plaintext;
    }

    @Override
    public byte[] decrypt(byte[] cipherText) {
        return cipherText;
    }
}
