/* -----------------------------------------------------------------------------
*	Data Structures
 * 	Assignment 4 - Fun With Hash Functions
 * 	Submitted by Tim Tron
 * -----------------------------------------------------------------------------
 */

import java.lang.Integer;
import java.lang.Math;
import java.lang.reflect.Array;
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
		int Pmultiple = 0;//Variable to keep track of the multiples of P
		int P = 128189;// Initialize P to given value in hash3
		int coll_Key[] = new int[n];// Array to store count of collisions
		int validKeys[] = new int[P];// Array of valid keys from 0 to p
		int C = n * n;
		int test = 0;// Test index used to find keys
		int testKey = 0;// Test Key
		int coll_count = 0;// Counter of collisions
		int Pcounter = 0;// P offset counter
		while (coll_count < n) {
			coll_count = 0;// Initialize coll_count to zero, restarting for each collision
			Pcounter = 0; // Initialize Pcounter to zero, restarting for each collision
			// Get the initial hash integer. We will increment later.
			test = hash.hash3(testKey);
			//System.out.println("test is = " + test+" testKey = "+testKey);
			//Building the Hash Table
			for (int i = 0; i < P; ++i) {
				//if (coll_count == n) {
				//	break;
				//}
				// Get the indexes that hash into the validKeys array
				// while their count is less than P
				//System.out.println("Hash3(i) = " + hash.hash3(i)+"and i = "+i);
				if (hash.hash3(i) == test) {
					validKeys[Pcounter++] = i;
				}
			}
			// Now that we have the indices built, we can began testing
			for (int i = 0; i < C; i++) {
				Pmultiple = (i * P);
				//System.out.println("Pmultiple = "+Pmultiple);
				for (int j = 0; j < Pcounter; j++) {
					// If we have found 'n' keys, we can stop
					if (coll_count == n)
						break;
					//System.out.println("validKeys = " + validKeys[j]);
					int offset = validKeys[j];
					//System.out.println("offset = "+offset);
					//System.out.println("Pmultiple + offset = " + (Pmultiple + offset));
					// If the Pmultiple added to the offset is greater than
					// the max allowed, we return out of the iteration.
					if (Pmultiple + offset > C) {
						// Invalid input, quit out
						// System.out.println("P is greater than C");
						return;
					}
					//System.out.println("Special TEST = "+hash.hash4(Pmultiple + offset));
					// Now record the collisions by the b%P offset
					//if ((hash.hash4(Pmultiple + offset)) == test) 
					coll_Key[coll_count++] = (Pmultiple + offset);
					//coll_Key[coll_count++] = j;
					
				}
			}
			// Incrementing the hash integer.
			testKey++;
		}
		// Now, print out all the collision keys collected.
		for (int i = 0; i < coll_count; i++) {
			//if (coll_Key[i] > 0)
				System.out.println(coll_Key[i]);
		}
	}

	private static void prog4(int n) {
		// TODO: Code to generate n keys that all get hashed to
		// the same index using hash4.
		int coll_Key[] = new int[n];// Array to store count of collisions
		int C = n * n;// The max value
		int test = 0;// Test index used to find keys
		int testKey = 0;// Test Key
		// Count the number of items in the linked lists per index that match
		int coll_count = 0;// Keeping track of the number of collisions
		// Loop until we reach the end of the input 'n'
		while (coll_count < n) {
			// Initialize count every time through the loop to start a new key
			coll_count = 0;
			// Get the initial hash integer. We will increment later.
			test = hash.hash4(testKey);
			//System.out.println("test is = " + test);
			for (int i = 0; i < C; ++i) {
				// we've gone through all the inputs, we're done
				if (coll_count == n) {
					break;
				}
				// Get random key and compare to the test key
				//System.out.println("Hash4(i) = " + hash.hash4(i)+"and i = "+i);			
				if (((hash.hash4(i))) == test) {
					// Increment count of linked lists items that match
					// the test index for the specific index
					// count++;//not liking my increment here, so moved inside array
					// System.out.println("Count is at " + count);
					coll_Key[coll_count++] = i;
				}
			}
			// Incrementing the hash integer.
			testKey++;
		}
		// Now print out the entire array of collisions found.
		for (int i = 0; i < coll_count; i++)
			System.out.println(coll_Key[i]);
	}
}
