package simplifyingFractions;

import java.util.ArrayList;
import java.util.Scanner;

public class Function {

	/*
	 * This is my code for Challenge #277[Easy] on /r/dailyprogrammer 
	 * this does not include the bonus section 
	 * URL:
	 * https://www.reddit.com/r/dailyprogrammer/comments/4uhqdb/20160725_challenge_277_easy_simplifying_fractions/ 
	 * Author: 
	 * /user/fornosoccer12
	 */
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the numerator of the fraction:");
		int numerator = kb.nextInt();
		System.out.println("Please enter the denominator of the fraction:");
		int denominator = kb.nextInt();
		kb.close();
		System.out.println("The simplified fraction is " + simplify(numerator, denominator));
	}

	public static String simplify(int num, int deno) {
		// find greatest common divisor
		int gcd = gcd(num, deno);
		num = num / gcd;
		deno = deno / gcd;
		return num + " / " + deno;

	}

	public static int gcd(int num, int deno) {
		ArrayList<Integer> mulNum = new ArrayList<>();
		ArrayList<Integer> mulDeno = new ArrayList<>();
		// finding multiples for the numerator
		for (int i = 1; i <= num; i++) {
			if (num % i == 0) {
				mulNum.add(i);
			}
		}
		// finding multiples for the denominator
		for (int i = 1; i <= deno; i++) {
			if (deno % i == 0) {
				mulDeno.add(i);
			}
		}
		// converting both arraylists to arrays
		int[] newNums = new int[mulNum.size()];
		for (int i = 0; i < newNums.length; i++) {
			newNums[i] = mulNum.get(i);
		}

		int[] newDenos = new int[mulDeno.size()];
		for (int i = 0; i < newDenos.length; i++) {
			newDenos[i] = mulDeno.get(i);
		}

		return maxCommon(newNums, newDenos);
	}

	public static int maxCommon(int[] nums, int[] denos) {
		// finding max common multiple of the numerator and denominator
		int max = 0;
		// finding the common integers first
		ArrayList<Integer> maximums = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < denos.length; j++) {
				if (nums[i] == denos[j]) {
					maximums.add(nums[i]);
				}
			}
		}
		// converting the arraylist to an array
		int[] newMax = new int[maximums.size()];
		for (int i = 0; i < newMax.length; i++) {
			newMax[i] = maximums.get(i);
		}
		// finding the maximum value in the array
		for (int i = 0; i < newMax.length; i++) {
			if (max < newMax[i]) {
				max = newMax[i];
			}
		}
		return max;
	}
}
