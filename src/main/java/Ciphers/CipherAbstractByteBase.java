package Ciphers;


public abstract class CipherAbstractByteBase implements CipherInterface<byte[]> {
    String CipherKey;

    public byte[] padder(final byte[] inputBytes, Integer length){
        int size;
        if (inputBytes.length <= length){
            size = length;
        }else{
            size = (int)Math.ceil((double)inputBytes.length / length) * length;
        }
        byte[] returnArray = new byte[size];
        for (int i = 0; i < inputBytes.length; i++){
            returnArray[i] = inputBytes[i];
        }
        for (int i = inputBytes.length; i <size; i++){
            returnArray[i] = 0;
        }
        return returnArray;
    }

    public byte[] padderStrip(final byte[] inputBytes){
        int i = inputBytes.length -1;
        while (i != 0){
            if (inputBytes[i] != 0){
                i++;
                break;
            }else{
                i--;
            }
        }
        byte[] r = new byte[i];
        System.arraycopy(inputBytes, 0, r, 0, i);
        return r;
    }


    /**
     * Gets a block of bytes of length blockSize from an input byte array.
     * @param inputBytes - the input byte array.
     * @param blockSize - the size of the return block size in bytes.
     * @param currentIndex - the current starting index. Be sure to keep track of the index by adding it to blocksize for every function call.
     * @return - the block byte array
     */
    public byte[] get_block(final byte[] inputBytes, int blockSize, int currentIndex){
        if (currentIndex + blockSize > inputBytes.length){
            throw new IndexOutOfBoundsException("Attempting get a block that would be out of bounds. Check your index or padding function.");
        }else{
            byte[] r = new byte[blockSize];
            for (int i = 0; i < blockSize; i++){
                r[i] = inputBytes[i+currentIndex];
            }
            return r;
        }
    }

    public Integer blockSize(){
        return 16;
    }
}
