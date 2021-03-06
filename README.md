# Cryptography-Spring2019 Project 2 AES and DES
[![Build Status](https://travis-ci.org/Nathan-LS/Cryptography-Spring2019.svg?branch=master)](https://travis-ci.org/Nathan-LS/Cryptography-Spring2019)
[![Build status](https://ci.appveyor.com/api/projects/status/5ii1i4fvjvmc6lee?svg=true)](https://ci.appveyor.com/project/Nathan-LS/cryptography-spring2019)
[![codecov](https://codecov.io/gh/Nathan-LS/Cryptography-Spring2019/branch/master/graph/badge.svg)](https://codecov.io/gh/Nathan-LS/Cryptography-Spring2019)

### Group Members:
  - Nathan S
  - Stephen S
  - Jasper C
  - Allen S
  - Hector M

### Programming Language: Java

### How to Execute:
This project builds upon the existing testing frameworks and cipher interfaces used in project 1.

1. Clone the project if you don't have it yet.
    ```
    https://github.com/Nathan-LS/Cryptography-Spring2019.git
    ```
2. Make the gradle wrapper file executable.
    ```
    cd Cryptography-Spring2019
    chmod +x ./gradlew
    ```
3. Compile and run the application with arguments as specified in the project assignment details. Format:
    ```
    ./gradlew run --args=<CIPHER NAME> <KEY> <ENC/DEC> <INPUT FILE> <OUTPUT FILE>
    ```
    For example:
    ```
    ./gradlew run --args="DES 0123456789abcedf ENC src/test/resources/DES/0123456789abcedf/plaintext_image_tux.png encrypted_tux.png"
    ```
    This will compile the Java application and encrypt a picture of Tux to the ```encrypted_tux.png``` file.
4. Let's decrypt the image of Tux and ensure it matches the plain original.
    ```
    ./gradlew run --args="DES 0123456789abcedf DEC encrypted_tux.png decrypted_tux.png"
    sha256sum decrypted_tux.png src/test/resources/DES/0123456789abcedf/plaintext_image_tux.png
    ```
    

### Notes on testing
This application is fully unit tested with JUnit and runs with continuous integration services. Files exist in the ```/src/test/resources/``` folder based on encryption key and cipher type.
For example, the file ```src/test/resources/DES/0123456789abcedf/plaintext_1.txt``` will have a corresponding assert file named ```src/test/resources/DES/0123456789abcedf/encrypted_1.txt``` which are used for decryption and encryption testing.
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
  - None

