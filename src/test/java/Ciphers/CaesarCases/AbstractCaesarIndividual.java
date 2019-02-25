package Ciphers.CaesarCases;

import Ciphers.AbstractCipherIndividual;
import Ciphers.Caesar;

public abstract class AbstractCaesarIndividual extends AbstractCipherIndividual {
    @Override
    protected Class cipher_class() {
        return Caesar.class;
    }
}