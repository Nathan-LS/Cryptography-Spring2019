package Ciphers.CaesarCases;

import Ciphers.AbstractCipherIndividual;
import Ciphers.Caesar;

abstract class AbstractCaesarIndividual extends AbstractCipherIndividual {
    @Override
    protected Class cipher_class() {
        return Caesar.class;
    }
}