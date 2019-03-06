package Ciphers;

public class PlayFair extends CipherAbstractBase {

	private String keyword = "";
	private String key = "";
	private char[][] matrixPF = new char[5][5];

	public void keySet(String k) {
		String kadjust = "";
		boolean flag = false;
		kadjust = kadjust + k.charAt(0);

		for(int i = 1; i < k.length(); i++)
		{
			for(int j = 0; j < kadjust.length(); j++)
			{
				if(kadjust.charAt(i) == kadjust.charAt(j))
				{
					flag = true;
				}
			}

			if(flag == false)
				kadjust = kadjust + k.charAt(i);
		}

		keyword = kadjust;
	}

	public void genKey()
	{
		boolean flag = true;
		char current;
		key = keyword;

		for(int i = 0; i < 26; i++)
		{
			current = (char)(i + 97);
			if(current == 'j')
				continue;

			for(int j = 0; j < keyword.length(); j++)
			{
				if(current == keyword.charAt(j))
				{
					flag = false;
					break;
				}
			}

			if(flag)
				key = key + current;
			flag = true;
		}
		System.out.println(key);
		matrix();
	}

	public void matrix()
	{
		int counter = 0;
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				matrixPF[i][j] = key.charAt(counter);
				System.out.println(matrixPF[i][j] + " ");
				counter++;
			}
			System.out.println();
		}
	}

	public String format(String oldText)
	{
		int i = 0;
		int len = 0;
		String text = "";
		len = oldText.length();

		for(int t = 0; t < len; t++)
		{
			if(text.charAt(i + 1) == text.charAt(i))
			{
				text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
			}
		}

		return text;
	}

	public String[] Pairs(String new_string)
	{
		String original = format(new_string);
		int size = original.length();
		
		if(size % 2 != 0)
		{
			size++;
			original = original + 'x';
		}

        String[] x = new String[size / 2];
		
		int counter = 0;
		
		for (int i = 0; i < size / 2; i++)
		{
			x[i] = original.substring(counter, counter + 2);
			System.out.println(x[i]);
			counter += 2;
		}
	
		return x;
	}

	public int[] getDimensions(char character)
	{
		return null;  //todo fix compile error
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
