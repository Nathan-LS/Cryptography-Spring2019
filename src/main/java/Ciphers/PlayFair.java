package Ciphers;

public class PlayFair extends CipherAbstractBase {

	private String Keyword = "";
	private String key = "";
	private char[][] matrixPF = new char[5][5];

	public void keySet(String k) {
		String kadjust = "";
		boolean flag = false;
		kadjust = kadjust + k.charAt(0);
		//System.out.println("setting key");

		for(int i = 1; i < k.length() - 1; i++)
		{
			for(int j = 0; j < kadjust.length() - 1; j++)
			{

				if(k.charAt(i) == kadjust.charAt(j))
				{
					flag = true;
				}
			}

			if(!flag)
				kadjust = kadjust + k.charAt(i);
			flag = false;
		}

		Keyword = kadjust;
	}

	public void genKey()
	{
		boolean flag = true;
		char current;
		key = Keyword;

		for(int i = 0; i < 26; i++)
		{
			current = (char)(i + 97);
			if(current == 'j')
				continue;

			for(int j = 0; j < Keyword.length(); j++)
			{
				if(current == Keyword.charAt(j))
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
				System.out.print(matrixPF[i][j] + " ");
				counter++;
			}
			System.out.println();
		}
	}

	public String format(String oldText)
	{
		int i = 0;
		int len;
		String text = oldText;
		len = oldText.length();

		for(int t = 0; t < len - 1; t++)
		{
			if(text.charAt(i + 1) == text.charAt(i))
			{
				System.out.println(text);
				text = text.substring(0, i + 1) + 'x' + text.substring(i + 1);
				System.out.println(text);
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

		for (int i = 0; i < (size / 2) - 1; i++)
		{
			x[i] = original.substring(counter, counter + 2);
			System.out.println(x[i]);
			counter += 2;
		}

		return x;
	}

	public int[] getDimensions(char character)
	{
		int[] key = new int[2];

		if (character == 'j')
			character = 'i';
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++) {
				if (matrixPF[i][j] == character) {
					key[0] = i;
					key[1] = j;
					break;
				}
			}
		}
		return key;
	}

	@Override
	public String encrypt(final String plaintext) {

		String result = "";

		String [] source = Pairs(plaintext);
		char first;
		char second;
		int[] partOne;
		int[] partTwo;

		for(int i = 0; i < source.length - 1; i++)
		{
			first = source[i].charAt(0);
			second = source[i].charAt(1);
			partOne = getDimensions(first);
			partTwo = getDimensions(second);

			if(partOne[0] == partTwo[0])
			{
				if(partOne[1] < 4)
					partOne[1]++;
				else
					partOne[1] = 0;
				if(partTwo[1] < 4)
					partTwo[1]++;
				else
					partTwo[1] = 0;
			}
			else if (partOne[1] == partTwo[1])
			{
				if(partOne[0] < 4)
					partOne[0]++;
				else
					partOne[0] = 0;
				if(partTwo[0] < 4)
					partTwo[0]++;
				else
					partTwo[0] = 0;
			}
			else
			{
				int temp = partOne[1];
				partOne[1] = partTwo[1];
				partTwo[1] = temp;
			}
			result = result + matrixPF[partOne[0]][partOne[1]] + matrixPF[partTwo[0]][partTwo[1]];
		}


		return result;
	}

	@Override
	public String decrypt(final String cipherText) {
		String result = "";

		String [] source = Pairs(cipherText);
		char first;
		char second;
		int[] partOne;
		int[] partTwo;

		for(int i = 0; i < source.length - 1; i++)
		{
			first = source[i].charAt(0);
			second = source[i].charAt(1);
			partOne = getDimensions(first);
			partTwo = getDimensions(second);

			if(partOne[0] == partTwo[0])
			{
				if(partOne[1] > 0)
					partOne[1]--;
				else
					partOne[1] = 4;
				if(partTwo[1] > 0)
					partTwo[1]--;
				else
					partTwo[1] = 4;
			}
			else if (partOne[1] == partTwo[1])
			{
				if(partOne[0] > 0)
					partOne[0]--;
				else
					partOne[0] = 4;
				if(partTwo[0] < 4)
					partTwo[0]--;
				else
					partTwo[0] = 4;
			}
			else
			{
				int temp = partOne[1];
				partOne[1] = partTwo[1];
				partTwo[1] = temp;
			}
			result = result + matrixPF[partOne[0]][partOne[1]] + matrixPF[partTwo[0]][partTwo[1]];
		}


		return result;
	}
}
