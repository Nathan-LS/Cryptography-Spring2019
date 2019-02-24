package Ciphers;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CipherAbstractMultiFileTest extends CipherAbstractBaseTest {
    /**
     * Extend this class to test multiple files in a directory. You must override:
     * key                      The key to use for the encryption and decryption.
     * cipher_class             The class you wish to run the test on. Example Caesar.class
     * <p>
     * You must also create a directory matching the cipher class name you are testing in src/test/resources ex: src/test/resources/Caesar
     * The files will automatically be picked up from this folder. You must create 2 text files for every test case matching the exact prefixes:
     * plaintext_   ex: plaintext_case1.txt     file to encrypt the contents of
     * encrypted_   ex: encrypted_case1.txt     assert file that the string in plaintext_case1.txt must match after being encrypted.
     */
    private ClassLoader classLoader;
    private ArrayList<File> TestFiles;

    private String TestDirName() {
        return cipher_class().getSimpleName();
    }

    @Override
    @BeforeEach
    void setUp() throws Exception {
        super.setUp();
        classLoader = getClass().getClassLoader();
        try {
            File fileRoot = new File(classLoader.getResource(TestDirName()).getFile());
            TestFiles = new ArrayList<File>(Arrays.asList(fileRoot.listFiles()));
        } catch (NullPointerException ex) {
            throw new FileNotFoundException(String.format("You are missing the test resource directory for: %s", TestDirName()));
        }
    }

    @Test
    void encrypt_and_decrypt() throws Exception {
        int test_counter = 0;
        for (File f : TestFiles) {
            String plaintextFileName = f.getName();
            File encryptedFile;
            if (plaintextFileName.startsWith("plaintext_")) {
                try {
                    encryptedFile = new File(classLoader.getResource(String.format("%s/", TestDirName()) + plaintextFileName.replace("plaintext_", "encrypted_")).getFile());
                } catch (NullPointerException ex) {
                    fail(String.format("You are missing the encrypted file for the test input file of: %s.\nPlease create an assert text file called: %s", plaintextFileName, plaintextFileName.replace("plaintext_", "encrypted_")));
                    return;
                }
                final String plainText = FileUtils.readFileToString(f, "utf-8");
                final String encryptedText = FileUtils.readFileToString(encryptedFile, "utf-8");
                assertNotEquals(plainText, encryptedText, plaintextFileName);
                assertEquals(encryptedText, cipher.encrypt(plainText), "Encrypt: " + plaintextFileName);
                assertEquals(plainText, cipher.decrypt(encryptedText), "Decrypt: " + plaintextFileName);
                test_counter++;
            }
        }
        assertNotEquals(0, test_counter, "No files were tested.");
        System.out.println(String.format("Tested %s files.", test_counter));
    }
}
