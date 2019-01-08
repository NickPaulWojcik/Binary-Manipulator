
/* CREATOR: Nicholas Wojcik
 * 
 * DESCRIPTION: This is a utility class full of static methods to output prompts to the 
 * 				console as well as print information from the BinaryManipulator class
 * 
 */

public class PromptOutput {
	
	//Prompts user for conversion type
	public static void decisionPrompt() {
		System.out.println();
		System.out.println("########################################");
		System.out.println("Please select conversion type.");
		System.out.println("1. Decimal to Binary");
		System.out.println("2. Binary to Decimal");
		System.out.println("########################################");
	}

	//Prompts user to confirm conversion type decision
	public static void selectionPrompt(int selection) {
		if(selection == 1) {
			System.out.println("------------------------------------");
			System.out.println("Decimal to Binary selected.");
			System.out.println("------------------------------------");
		}
		else if(selection == 2) {
			System.out.println("------------------------------------");
			System.out.println("Binary to Decimal selected.");
			System.out.println("------------------------------------");
		}
	}
	
	//Prompts user for digits of their number
	public static void numberInputPrompt() {
		System.out.println();
		System.out.println("Enter digits for conversion (up to 10 digits).");
	}
	
	//Prompts user for excess number
	public static void excessInputPrompt() {
		System.out.println();
		System.out.println("Enter Excess Number (positive, up to 1024).");
	}
	
	//Prints Binary to Decimal information
	public static void outputBinaryToDecimal(int bin, int exc, BinaryManipulator temp) {
		System.out.println("Binary: " + bin);
		System.out.println("Decimal: " + temp.binToDec(bin));
		System.out.println("One's Compliment: " + temp.binaryToOnes(bin));
		System.out.println("Two's Compliment: " + temp.binaryToTwos(bin));
		System.out.println("Signed Magnitude: " + temp.binaryToSigned(bin));
		System.out.println("Excess Notation-" + exc + ": " + temp.binaryToExc(bin, exc));
	}
	
	//Prints Decimal to Binary information
	public static void outputDecimalToBinary(int dec, int exc, BinaryManipulator temp) {
		System.out.println("Decimal: " + dec);
		System.out.println("Binary: " + temp.decToBin(dec));
		System.out.println("One's Compliment: " + temp.decimalToOnes(dec));
		System.out.println("Twos's Compliment: " + temp.decimalToTwos(dec));
		System.out.println("Signed Magnitude: " + temp.decimalToSigned(dec));
		System.out.println("Excess Notation-" + exc + ": " + temp.decimalToExc(dec, exc));
	}
}
