package defusingBomb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author CrisForno
 *
 *         You have to start with either with a white or a red wire. If you
 *         picked white wire you can either pick another white wire again or you
 *         can take an orange one. If you picked a red wire you have the choice
 *         between a black and red wire. When a second red wire is picked, you
 *         can start from rule one again. Back to the second rule, if you picked
 *         another white one you will have to pick a black or red one now When
 *         the red wire is picked, you again go to rule one. On the other hand
 *         if you then picked an orange wire, you can choose between green,
 *         orange and black. When you are at the point where you can choose
 *         between green, orange and black and you pick either green or orange
 *         you have to choose the other one and then the bomb is defused.
 */
public class Bomb {

	public static void main(String[] args) throws FileNotFoundException {
		for (int i = 1; i <= 6; i++) {
			Scanner sc = new Scanner(new File("inputBomb0" + i + ".txt"));
			ArrayList<String> wires = new ArrayList<>();

			while (sc.hasNextLine()) {
				wires.add(sc.nextLine());
			}
			sc.close();
			// start at rule 1
			State curr = State.S0;
			// look through the wires
			for (String wire : wires) {
				if (curr == State.S0) {
					if (wire.equals("white")) {
						curr = State.S1;
					} else if (wire.equals("red")) {
						curr = State.S2;
					} else {
						curr = State.BOOM;
						break;
					}
				} else if (curr == State.S1) {
					if (wire.equals("white")) {
						curr = State.S2;
					} else if (wire.equals("orange")) {
						curr = State.S3;
					} else {
						curr = State.BOOM;
						break;
					}
				} else if (curr == State.S2) {
					if (wire.equals("red")) {
						curr = State.S0;
					} else if (wire.equals("black")) {
						curr = State.S3;
					} else {
						curr = State.BOOM;
						break;
					}
				} else if (curr == State.S3) {
					if (wire.equals("black")) {
						// stay in the same state
					} else if (wire.equals("green")) {
						curr = State.S5;
					} else if (wire.equals("orange")) {
						curr = State.S4;
					} else {
						curr = State.BOOM;
						break;
					}
				} else if (curr == State.S4) {
					if (wire.equals("green")) {
						curr = State.DEFUSE;
						break;
					} else {
						curr = State.BOOM;
						break;
					}
				} else if (curr == State.S5) {
					if (wire.equals("orange")) {
						curr = State.DEFUSE;
						break;
					} else {
						curr = State.BOOM;
						break;
					}
				}
			}
			// see if the sequence of wires is able to defuse the bomb
			if (curr == State.BOOM) {
				System.out.println("Booom!");
			} else if (curr == State.DEFUSE) {
				System.out.println("defused");
			} else {
				System.out.println("Time's Up; Booom!");
			}
		}

	}
}
