package Ciphers;


public abstract class CipherAbstractBase implements CipherInterface {
    String CipherKey;

    CipherAbstractBase() {
    }

    @Override
    public boolean setKey(final String key) {  // TODO: 2/23/2019  what is a valid key?
        CipherKey = key;
        return true;
    }
}
