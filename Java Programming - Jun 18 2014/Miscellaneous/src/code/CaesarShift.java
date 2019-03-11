package code;

public class CaesarShift
{
	public static String caesarShift(String str, String keyword)
	{
		String output = "";
		String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int j = 0;
		for(int i = 0; i < str.length(); i++) {
			if(ALPHA.indexOf(str.toUpperCase().charAt(i)) != -1) {
				int val = ALPHA.indexOf(str.toUpperCase().charAt(i));
				int offset = 0;
				if(ALPHA.indexOf(keyword.toUpperCase().charAt(j%keyword.length())) != -1) {
					offset = ALPHA.indexOf(keyword.toUpperCase().charAt(i%keyword.length()))+1;
				}
				int newVal = (val + offset) % 26;
				output += ALPHA.charAt(newVal);
				j++;
			} else {
				output += str.charAt(i);
			}
		}
		return output;
	}
}
