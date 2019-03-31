package Ciphers;


public class AES extends CipherAbstractBase {

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
    public String encrypt(final String plaintext) {
        return null;
    }

    @Override
    public String decrypt(final String cipherText) {
        return null;
    }
}
