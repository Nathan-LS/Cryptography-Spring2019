package Ciphers;


public abstract class CipherAbstractBase implements CipherInterface {
    protected String CipherKey;

    CipherAbstractBase() {
    }

    @Override
    public boolean setKey(String key) {  // TODO: 2/23/2019  what is a valid key?
        CipherKey = key;
        return true;
    }
}
