# Cryptography-Spring2019
[![Build Status](https://travis-ci.org/Nathan-LS/Cryptography-Spring2019.svg?branch=master)](https://travis-ci.org/Nathan-LS/Cryptography-Spring2019)
[![Build status](https://ci.appveyor.com/api/projects/status/5ii1i4fvjvmc6lee?svg=true)](https://ci.appveyor.com/project/Nathan-LS/cryptography-spring2019)
[![codecov](https://codecov.io/gh/Nathan-LS/Cryptography-Spring2019/branch/master/graph/badge.svg)](https://codecov.io/gh/Nathan-LS/Cryptography-Spring2019)

### Group Members:
  - Nathan S
  - Stephen S
  - Jasper C
  - Allen S

### Programming Language: Java

### How to Execute:
1. Clone the project if you don't have it yet.
    ```
    https://github.com/Nathan-LS/Cryptography-Spring2019.git
    ```
2. Make the gradle wrapper file executable.
    ```
    cd Cryptography-Spring2019
    chmod +x ./gradlew
    ```
3. Compile and run the application with arguments as specified in the project assignment details.
    ```
    ./gradlew run --args="PLF TestKey ENC src/test/resources/PlayFair/TestKey/plaintext_1.txt demo.txt"
    ```

### Notes on testing
This application is fully unit tested with JUnit and runs with continuous integration services. Files exist in the ```/src/test/resources/``` folder based on encryption key and cipher type.
For example, the file ```src/test/resources/PlayFair/TestKey/plaintext_1.txt``` will have an assert file named ```src/test/resources/PlayFair/TestKey/encrypted_1.txt``` and test that encryption matches this file and decryption from this file matches the plaintext file.
You should run all of our unit tests with the following command:
```text
./gradlew test
```
Path format:
```text
src/test/resources/CIPHER/KEY/plaintext_CASE.txt
```

### Extra Credit Implementation:
Not attempted.

### Project Notes:
  - Vigenre and Playfair cipher must take in input of no spaces, symbols or numbers. Lowercase input is converted to uppercase.

