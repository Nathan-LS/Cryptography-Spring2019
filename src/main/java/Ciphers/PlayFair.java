package Ciphers;

public class PlayFair extends CipherAbstractBase {

    public String[] Pairs(String plaintext) {
		int size = plaintext.length();
		
		if(size % 2 != 0)
			size++;

        String[] x = new String[size / 2];
		
		int counter = 0;
		
		for (int i = 0; i < size / 2; i++)
		{
			x[i] = plaintext.substring(counter, counter + 2);
			System.out.println(x[i]);
			counter += 2;
		}
	
		return x;
	}
	
    @Override
    public String encrypt(final String plaintext) { //TODO
		
		String result = "";
		
		String [][] encmatrix = new String[5][5];
		
		for(int i = 0; i < plaintext.length(); i++)
		{
			
		}
		

        return null;
    }

    @Override
    public String decrypt(final String cipherText) { //TODO
        return null;
    }
}
