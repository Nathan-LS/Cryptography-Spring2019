package Ciphers.VigenreCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.Vigenre;


abstract class AbstractVigenre extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return Vigenre.class;
    }
}