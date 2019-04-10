package Ciphers;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractCipherMultiByteFile extends AbstractCipherBaseTest<CipherAbstractByteBase> {
    protected Path testResource;
    protected List<File> TestFiles;

    private String TestDirName() {
        return cipher_class().getSimpleName();
    }


    protected String helper_newLine(String inputText){
        //convert input text to have consistent new lines for Windows.
        return inputText.replace("\r\n", "\n");
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
                final byte[] plainBytes = FileUtils.readFileToByteArray(f);
                final byte[] encryptedBytes = FileUtils.readFileToByteArray(encryptedFile);
                if (Arrays.equals(plainBytes, encryptedBytes)) {
                    System.err.println(String.format("WARNING: Encryption and decryption test case files should not have the same exact content unless the encryption results in the plaintext. Double check your test case file: %s", plaintextFileName));
                }
                assertArrayEquals(encryptedBytes, cipher.encrypt(plainBytes), "Encrypt: " + plaintextFileName);
                assertArrayEquals(plainBytes, cipher.decrypt(encryptedBytes), "Decrypt: " + plaintextFileName);
                test_counter++;
            }
        }
        assertNotEquals(0, test_counter, "No files were tested.");
        System.out.println(String.format("Tested %s files.", test_counter));
    }
}
