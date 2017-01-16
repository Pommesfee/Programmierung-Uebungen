package programming.set9.parser;

import acm.program.ConsoleProgram;

public class PolishNotation extends ConsoleProgram {

    @Override
    public void run() {
        println(new PNParser("+ + 3 4 5"));
    }
}