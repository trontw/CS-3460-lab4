import java.lang.Integer;
import java.lang.Math;
import java.util.Random;

public class Prog {
	public static HashFunctions hash = null;

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Please execute: java Prog <n> <p>");
			System.out.println("n is the input size, and p is the program number.");
			System.exit(0);
		}

		int n = Integer.parseInt(args[0]);
		int p = Integer.parseInt(args[1]);
		hash = new HashFunctions(n);
		switch (p) {
			case 1:
				prog1(n);
				break;
			case 2:
				prog2(n);
				break;
			case 3:
				prog3(n);
				break;
			case 4:
				prog4(n);
				break;
			default:
				System.out.println("Invalid program number. Only 1-4.");
		}
	}

	private static void prog1(int n) {
		// TODO: Code to generate n keys that all get hashed to
		// the same index using hash1.
		int max = n * n;
		// System.out.println("Max is " + max);
		// Loop until reach max
		for (int i = 0; i < max; ++i) {
			// Call hash1 and return only mods of input n
			int modulo = hash.hash1(i);
			if (modulo == 0) {
				// Print out only those keys which have the same output (zero)
				System.out.println(i);
			}
		}
	}

	private static void prog2(int n) {
		// TODO: Code to generate n keys that all get hashed to
		// the same index using hash2.
		int max = n * n;
		for (int i = 0; i < max; ++i) {
			int output = hash.hash2(i);
			// System.out.println("output of hash2 = " + output);
			double fl = Math.floor(output);
			// System.out.println("floor value is " + fl);
			if ((int) fl == 0) {
				// Print out only those keys which have the same output (zero)
				System.out.println(i);
			}
		}
	}

	private static void prog3(int n) {
		// TODO: Code to generate n keys that all get hashed to
		// the same index using hash3.
		int max = n * n;
		for (int i = 0; i < max; ++i) {
			int modulo = hash.hash3(i);
			if (modulo == 0) {
				// Print out only those keys which have the same output (zero)
				System.out.println(i);
			}
		}
	}

	private static void prog4(int n) {
		// TODO: Code to generate n keys that all get hashed to
		// the same index using hash4.
		int max = n * n;
		int test = 0;// Test key
		test = hash.hash4(n);
		for (int i = 0; i < max; ++i) {
			// Get random key and compare to the test key
			if (((hash.hash4(i))) == test) {
				// Print out only those keys which have the same output (zero)
				System.out.println(i);
			}
		}
	}
}
