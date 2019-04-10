package Ciphers.VigenreCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.Vigenre;


abstract class AbstractVigenre extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return Vigenre.class;
    }
}