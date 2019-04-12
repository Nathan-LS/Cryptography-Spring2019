package Ciphers.RowTranspositionCases;

import Ciphers.AbstractCipherMultiTextFile;
import Ciphers.RowTransposition;


abstract class AbstractRowTransposition extends AbstractCipherMultiTextFile {
    @Override
    protected Class cipher_class() {
        return RowTransposition.class;
    }

}