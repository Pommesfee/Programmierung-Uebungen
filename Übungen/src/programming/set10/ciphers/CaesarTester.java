package programming.set10.ciphers;

public class CaesarTester {

	public static void main(String[] args) {

		CaesarCipher c = new CaesarCipher(CryptMode.ENCRYPT, 51);
		String s = c.encode("Dies ist ein Test");
		System.out.println(s);
		CaesarCipher cc = new CaesarCipher(CryptMode.DECRYPT, 51);
		String ss = cc.encode(s);
		System.out.println(ss);
	}

}
