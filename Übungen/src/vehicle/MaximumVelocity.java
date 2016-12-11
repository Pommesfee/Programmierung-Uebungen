package vehicle;

import acm.program.ConsoleProgram;

public class MaximumVelocity extends ConsoleProgram {

	@Override
	public void run() {
		System.out.println(new Car("VW Polo", 45));
		System.out.println(new Car("Porsche 911", 218));
		System.out.println(new Car("Lamborghini Countach", 454));
		System.out.println(new SteamShip("HMS Titanic", 51000, 45000, 269));
		System.out.println(new SteamShip("USS Nimitz", 280000, 80000, 332));
		System.out.println(new RowingBoat("Greek Trireme", 170, 6.1, 0.9));
		System.out.println(new Bicycle("Hands on the tops", Bicycle.OBERLENKER_RENNRAD));
		System.out.println(new Bicycle("Hands on the drops", Bicycle.UNTERLENKER_RENNRAD));
		System.out.println(new Bicycle("Roadster", Bicycle.HOLLANDRAD));
	}
	
}
