package Ciphers;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractCipherMultiFile extends AbstractCipherBaseTest {
    /**
     * Extend this class to test multiple files in a directory. You must override:
     * key                      The key to use for the encryption and decryption.
     * cipher_class             The class you wish to run the test on. Example Caesar.class
     * <p>
     * You must also create a directory matching the cipher class name you are testing and the key used for your cases in src/test/resources ex: src/test/resources/Caesar/key
     * The files will automatically be picked up from this folder. You must create 2 text files for every test case matching the exact prefixes:
     * plaintext_   ex: plaintext_case1.txt     file to encrypt the contents of
     * encrypted_   ex: encrypted_case1.txt     assert file that the string in plaintext_case1.txt must match after being encrypted.
     */
    private Path testResource;
    private List<File> TestFiles;

    private String TestDirName() {
        return cipher_class().getSimpleName();
    }

    @Override
    @BeforeEach
    void setUp() throws Exception {
        super.setUp();
        ClassLoader cL = getClass().getClassLoader();
        try {
            testResource = Paths.get(cL.getResource(TestDirName()).toURI());
            testResource = Paths.get(testResource.toString(), key());
            File fileRoot = new File(testResource.toUri());
            TestFiles = Arrays.asList(fileRoot.listFiles());
        } catch (NullPointerException ex) {
            throw new FileNotFoundException(String.format("You are missing the test resource directory for: %s/%s", TestDirName(), key()));
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
                    Path eFilePath = Paths.get(testResource.toString(), plaintextFileName.replace("plaintext_", "encrypted_"));
                    encryptedFile = eFilePath.toFile();
                } catch (NullPointerException ex) {
                    fail(String.format("You are missing the encrypted file for the test input file of: %s.\nPlease create an assert text file called: %s", plaintextFileName, plaintextFileName.replace("plaintext_", "encrypted_")));
                    return;
                }
                System.out.println(String.format("Testing encryption and decryption for file: '%s' with key '%s' using the %s cipher.", plaintextFileName, key(), cipher_class().getSimpleName()));
                final String plainText = FileUtils.readFileToString(f, "utf-8");
                final String encryptedText = FileUtils.readFileToString(encryptedFile, "utf-8");
                if (plainText.equals(encryptedText)) {
                    System.err.println(String.format("WARNING: Encryption and decryption test case files should not have the same exact content unless the encryption results in the plaintext. Double check your test case file: %s", plaintextFileName));
                }
                assertEquals(encryptedText, cipher.encrypt(plainText), "Encrypt: " + plaintextFileName);
                assertEquals(plainText, cipher.decrypt(encryptedText), "Decrypt: " + plaintextFileName);
                test_counter++;
            }
        }
        assertNotEquals(0, test_counter, "No files were tested.");
        System.out.println(String.format("Tested %s files.", test_counter));
    }
}
