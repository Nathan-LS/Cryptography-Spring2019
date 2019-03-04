package Ciphers.RowTranspositionCases;

import Ciphers.AbstractCipherMultiFile;
import Ciphers.RowTransposition;


abstract class AbstractRowTransposition extends AbstractCipherMultiFile {
    @Override
    protected Class cipher_class() {
        return RowTransposition.class;
    }

}