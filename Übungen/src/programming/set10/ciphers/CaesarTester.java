package programming.set10.ciphers;

public class CaesarTester {

	public static void main(String[] args) {

		CaesarCipher c = new CaesarCipher(CryptMode.ENCRYPT, 2);
		String s = c.encode("z");
		System.out.println(s);
		CaesarCipher cc = new CaesarCipher(CryptMode.DECRYPT, 2);
		String ss = cc.encode(s);
		System.out.println(ss);
	}

}
