package tea.com;

public class EncryptionKey {
	private String encrypted = null;
	private String encryptor = null;
	
	private int keynum;
	private String keyword = null;
	private String phrase = null;
	
	
	public EncryptionKey(String a, String b, int c)
	{
		setKey(trimString(a));
		setPhrase(trimString(b));
		setNum(c);
	}
	public void encryptIt()
	{
		String a = "";
		int b = 0;
		char c;
		int d;
		int e;
		int f;
		final int G = phrase.length();
		final int H = encryptor.length();
		final int J = keynum;
		final int K = 65536;
		for(int i = 0; i < phrase.length(); i++) {
			if(Character.isWhitespace(phrase.charAt(i)) || Character.isSpaceChar(phrase.charAt(i))) {
				c = phrase.charAt(i);
			} else {
				try {
					c = (char) ((phrase.charAt(i) ^ ((char) encryptor.charAt(b))));
					d = (J + i % 2 == 0)
							? ((J / 100) + (J/H) + J % H)
							: ((J % 150) + (J/G) + J % G);
					e = (J + i % 2 == 0)
							? ((J % 100) + (J/G) + J % G)
							: ((J / 150) + (J/H) + J % H);
					f = (byte)d ^ (byte) e;
					c = (char) (((int) c ^ f));
if(TextEncryptorEnum.DEBUG) System.out.println("" + (int) c + "\t Location in String:" + i + "\t Characters:" + phrase.charAt(i) + " " + encryptor.charAt(b) + "\tResulting character:" + c);
					if((int) c <= -1) {
						c += K;
					}
					if((int) c >= 65535) {
						c -= K;
					}
				} catch (Exception z) {
					c = (char) (phrase.charAt(i) ^ (char)(TextEncryptorEnum.DEFAULTKEY.charAt(b)));
				}
				if(Character.isWhitespace(c) || Character.isSpaceChar(c)) {
					c = phrase.charAt(i);
				} else {
					b++;
					if(b >= encryptor.length()) {
						b = 0;
					}
				}
			}
			a += c;
		}
		encrypted = a;
if(TextEncryptorEnum.DEBUG) System.out.println("__________________________________________");
	}
	private char encryptKey(int n)
	{
		char a;
		int j = 128;
		int[] k = {0, 0, 0, 0, 0, 0, 0, 0};
		int[] l = {0, 0, 0, 0, 0, 0, 0, 0};
		int m = 0;
		for(int i = 0; i < 8;  j /= 2, i++) {
			if(n >= j) {
				k[i] = 1;
				n -= j;
			} else {
				k[i] = 0;
			}
		}
		j = 128;
		l[0] = k[5] != 0 ? 0 : 1;
		l[1] = k[3] != 0 ? 1 : 0;
		l[2] = k[1] != 0 ? 0 : 1;
		l[3] = k[7] != 0 ? 1 : 0;
		l[4] = k[6] != 0 ? 0 : 1;
		l[5] = k[2] != 0 ? 1 : 0;
		l[6] = k[0] != 0 ? 0 : 1;
		l[7] = k[4] != 0 ? 1 : 0;
		for(int i = 0; i < 8;  j /= 2, i++) {
			m += l[i]*j;
		}
		a = (char) m;
		return a;
	}
	public String getDecrypted()
	{
		return encrypted;
	}
	public String getEncrypted()
	{
		return encrypted;
	}
	private void keyScramble()
	{
		String temp = "";
		char a;
		char b;
		for(int i = 0; i < keyword.length(); i++) {
			a = (char)(keyword.charAt(i));
			int c = (int) a;
			b = encryptKey(c);
			temp += b;
		}
		encryptor = temp;
	}
	public void setEncrypted(String a)
	{
		encrypted = a;
	}
	public void setKey(String a)
	{
		try {
			int b = 1/a.length();
			keyword = a;
			if(b == 0) {
				b = 0;
			}
		} catch (java.lang.ArithmeticException e) {
			keyword = TextEncryptorEnum.DEFAULTKEY;
		}
		
		keyScramble();
	}
	public void setNum(int a)
	{
		try {
			if((a <= 9999 && a >= 0) == false) {
				a = 1337;
			}
		} catch (Exception e) {
			a = TextEncryptorEnum.DEFAULTNUM;
		} finally {			
			keynum = a;
		}
	}
	public void setPhrase(String a)
	{
		phrase = a;
	}
	public String trimString(String a)
	{
		String b = a;
		try {
			
			int e = 0;
			int f = (b.length() - 1);
			for(; e < b.length(); e++) {
				char d = b.charAt(e);
				if((Character.isWhitespace(d) && Character.isSpaceChar(d)) == false) {
					break;
				}
			}
			for(; f > -1; f--) {
				char d = b.charAt(f);
				if((Character.isWhitespace(d) && Character.isSpaceChar(d)) == false) {
					break;
				}
			}
if(TextEncryptorEnum.DEBUG) System.out.println(e + " " + f);
			String g = b.substring(e, f+1);
			return g;
		} catch (Exception e) {
			System.out.println("Incorrect String.");
			b = null;
		}
		return b;
	}
}
