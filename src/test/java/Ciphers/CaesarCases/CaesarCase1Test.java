package Ciphers.CaesarCases;

public class CaesarCase1Test extends CaesarAbstractTest {
    protected String key() {
        return "9";
    }

    protected String string_to_encrypt() {
        return "Hello this is a test string which we are testing. You are reading a test string.";
    }

    protected String assert_string_encrypted() {
        return "Qnuux cqrb rb j cnbc bcarwp fqrlq fn jan cnbcrwp. Hxd jan anjmrwp j cnbc bcarwp.";
    }
}