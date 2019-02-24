package Ciphers.CaesarCases;

public class CaesarCase2 extends CaesarAbstractTest {
    protected String key() {
        return "5";
    }

    protected String string_to_encrypt() {
        return "this is another demo string! Now this string contains numbers such as : 1, 2, 3,4, 5. Any characters that are not letters should not be encrypted and appear as plaintext.";
    }

    protected String assert_string_encrypted() {
        return "ymnx nx fstymjw ijrt xywnsl! Stb ymnx xywnsl htsyfnsx szrgjwx xzhm fx : 1, 2, 3,4, 5. Fsd hmfwfhyjwx ymfy fwj sty qjyyjwx xmtzqi sty gj jshwduyji fsi fuujfw fx uqfnsyjcy.";
    }
}
