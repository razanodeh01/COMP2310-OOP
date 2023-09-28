import java.util.Scanner;

public class Patients {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// Read number of days that a patient has entered the hospital.
		System.out.print("Please enter the number of days  that a patient has entered the hospital :");
		int numberOfDays = in.nextInt();
		if (numberOfDays <= 0) {
			System.out.println("Error ! Please enter a valid number :");
			numberOfDays = in.nextInt();
		}
		double[][] temperatures = new double[numberOfDays][];

		// Read number of times that nurse read the temperature of the patient in
		// Celsius.
		int n = 0, sum = 0;
		for (int k = 0; k < temperatures.length; k++) {
			System.out.print(
					"Please enter the number of  times that nurse read the temperature of the patient in Celsius in day "
							+ (k + 1) + ":");
			n = in.nextInt();
			if (n < 2) {
				System.out.println("Error ! Please enter a number more than or equal to 2 :");
				n = in.nextInt();
			}
			sum += n;

			temperatures[k] = new double[n];
			for (int i = 0; i < n; ++i) {
				// Read the temperatures that was read by the nurse and store each temperature
				// in the array.
				System.out.print("Please enter the " + (i + 1) + " temperature :");
				temperatures[k][i] = in.nextDouble();
				// Check if the temperature is valid.
				while (temperatures[k][i] < 30 || temperatures[k][i] > 45) {
					System.out.print("Error!!!!");
					System.out.println();
					System.out.print("Please enter a valid value for the temperature :");
					temperatures[k][i] = in.nextDouble();
				}
			}

		}

		// Print the average of the temperatures , maximum and minimum temperature.
		double[] results = Summary(temperatures, sum);
		System.out.println();
		System.out.print("Tha Maximum temperature is :" + results[0]);
		System.out.println();
		System.out.print("Tha Minimum temperature is :" + results[1]);
		System.out.println();
		System.out.print("Tha average is :" + results[2]);

		// Print the number of temperatures that are below or equal and above.
		int[] counters = countbelowAboveAverage(temperatures, results[2]);
		System.out.println();
		System.out.print("Total number of readings below or equal to average is  :" + counters[0]);
		System.out.println();
		System.out.println("Total number of readings above average is " + counters[1]);
		System.out.println();

		// Sort the array .
		double[][] sortedArray = sortArray(temperatures);
		System.out.println("The form of the sorted array is : ");
		printArray(sortedArray);

		// Make the decision
		System.out.println();
		System.out.print("Does the patient can leave the hosiptal ?");
		System.out.println();
		boolean leavingResult = leaveHospital(sortedArray);
		if (leavingResult == true)
			System.out.println("The average is Stable , he/she can leave.");
		else
			System.out.println("The average is not Stable ,he/she can't leave.");

	}

		// A method that return the average, maximum, and minimum of temperatures.
	static double[] Summary(double temp[][], int num) {

		double[] results = new double[3];

		double MAX = temp[0][0];
		for (int m = 0; m < temp.length; m++) {
			for (int n = 0; n < temp[m].length; n++) {
				if (temp[m][n] > MAX)
					MAX = temp[m][n];
			}
		}
		results[0] = MAX;

		double MIN = temp[0][0];
		for (int m = 0; m < temp.length; m++) {
			for (int n = 0; n < temp[m].length; n++) {
				if (temp[m][n] < MIN)
					MIN = temp[m][n];
			}
		}
		results[1] = MIN;

		int sum = 0;
		for (int m = 0; m < temp.length; m++) {
			for (int n = 0; n < temp[m].length; n++) {
				sum += temp[m][n];
			}
		}
		double AVG = sum / (double) num;
		results[2] = AVG;
		// Return the results in array.
		return results;

	}

		// A method will return a number of temperatures less than or equal to the
		// average of temperatures.
		// And the number of temperatures above the average.
	static int[] countbelowAboveAverage(double temp[][], double avg) {
		int[] count = new int[2];
		int belowEqualCount = 0, aboveCount = 0;

		for (int m = 0; m < temp.length; m++) {
			for (int n = 0; n < temp[m].length; n++) {
				if (temp[m][n] < avg || temp[m][n] == avg)
					belowEqualCount++;
				else
					aboveCount++;

			}
		}
		count[0] = belowEqualCount;
		count[1] = aboveCount;

		return count;
	}

		// A method to sort the values of temperature in ascending order .
	static double[][] sortArray(double temp[][]) {

		double temporary = 0.0;
		double[][] result = new double[temp.length][];
		for (int m = 0; m < temp.length; m++) {
			for (int n = 0; n < (temp[m].length - 1); n++) {
				for (int l = n + 1; l < temp[m].length; l++) {
					if (temp[m][n] > temp[m][l]) {
						temporary = temp[m][l];
						temp[m][l] = temp[m][n];
						temp[m][n] = temporary;
					}
				}
			}
		}
		// Creat a new 2D array to take a copy from the sorted array.
		for (int m = 0; m < temp.length; m++) {
			result[m] = new double[temp[m].length];
			for (int n = 0; n < result[m].length; n++) {
				result[m][n] = temp[m][n];
			}
		}

		return result;
	}

		// A method to print the sorted array.
	static void printArray(double temp[][]) {
		for (int m = 0; m < temp.length; m++) {
			System.out.print(temp[m].length);
			for (int n = 0; n < temp[m].length; n++) {
				System.out.print("   " + temp[m][n] + "  ");
			}
			System.out.println();
		}
	}

		// A method to decide if the patient can leave the hospital .
	static boolean leaveHospital(double temp[][]) {
		double sum = 0.0;

		for (int m = temp.length - 1; m >= temp.length - 2; --m) {
			for (int n = temp[m].length - 1; n >= temp[m].length - 2; --n) {
				sum += temp[m][n];
			}
		}
		
		// Calculate the average to make a decision.
		double average = sum / 4.0;
		System.out.print(average);
		System.out.println();
		if (average >= 35.5 && average <= 36.5)
			return true;

		return false;
	}
}