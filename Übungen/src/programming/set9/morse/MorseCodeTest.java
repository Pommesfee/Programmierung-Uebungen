package programming.set9.morse;

import acm.program.ConsoleProgram;

public class MorseCodeTest extends ConsoleProgram {

	@Override
	public void run() {
		
		System.out.println(MorseCoder.decode("- .... --- ..- \n... .... .- .-.. - \n-. --- - \n.--. .-. --- -.. ..- -.-. . \n-... ..- --. ..."));
		
		System.out.println(MorseCoder.encode("THOU SHALT NOT PRODUCE BUGS"));
	}
	
}
