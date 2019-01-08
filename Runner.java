
/* CREATOR: Nicholas Wojcik
 * 
 * DESCRIPTION: This is the entry to the programs main method, it contains a loop to continously run the program.  
 * 				It is responsible for calling the prompts from PromptOutput.java, gathering and validating input, and
 * 				passing the data to PromptOutput.java.  Its only connection with BinaryManipulator.java is to create a 
 * 				object to pass to the PromptOut.java.
 * 
 */

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		
		boolean repeat = true;
		//conversionType is 3, because only 1 and 2 are accepted, assuring that the validation loop is entered if something goes wrong
		int conversionType = 3;
		int userNum = 0;
		int exc = 0;
		Scanner keyboard = new Scanner(System.in);
		
		//MAIN PROGRAM LOOP********************************************************************************
		while(repeat) {
			
			//Prompt asking for binary to decimal, or decimal to binary
			PromptOutput.decisionPrompt();
			//User input for decision
			System.out.print("Choice: ");
			conversionType = keyboard.nextInt();
			//Validation for input
			while((conversionType != 1) && (conversionType != 2)) {
				System.out.println("INVALID ENTRY, try again.");
				conversionType = keyboard.nextInt();
			}
			
			
			//Prompt confirming the prior decision
			PromptOutput.selectionPrompt(conversionType);
			
			
			//Prompt asking for the main binary/decimal
			PromptOutput.numberInputPrompt();
			//User input for main binary/decimal
			System.out.print("Digits: ");
			//Validation for input
			while(true) {
				try {
					userNum = keyboard.nextInt();
					//Throws exception if length is over 10 bits to enter invalid entry loop
					if(Integer.toString(userNum).length() > 10) {
						throw new Exception();
					}
					if(Integer.toString(userNum).matches("[01]+"))
						break;
					System.out.println("INVALID ENTRY, try again.");
				}catch(Exception e) {
					System.out.println("INVALID ENTRY, try again.");
					keyboard.nextLine();
				}
			}
			
			
			//Prompt asking for excess notation number
			PromptOutput.excessInputPrompt();
			//User input for excess
			System.out.print("Excess Number: ");
			//Validation for input	
			while(true) {
				try {
					exc = keyboard.nextInt();
					//Throws exception if inputs over 1024 or negative, to enter validation loop
					if(exc > 1024 || exc < 0) {
						throw new Exception();
					}
					break;
				}catch(Exception e) {
					System.out.println("INVALID ENTRY, try again.");
					keyboard.nextLine(); //Flushes the faulty input, to avoid infinite exception loop because the faulty input would remain
				}
			}
			System.out.println();
			
			
			//BinaryManipulator object to pass to PromptOutput for calculations
			BinaryManipulator temp = new BinaryManipulator();
			
			
			//Calls the prompt to output the desired information based on the users decision
			if(conversionType == 1) {
				PromptOutput.outputDecimalToBinary(userNum, exc, temp);
			}
			else if(conversionType == 2) {
				PromptOutput.outputBinaryToDecimal(userNum, exc, temp);
			}
			
			
			//Prompt to ask user to continue
			System.out.println();
			System.out.println("-----------------");
			System.out.println("Again? (Y/N)");
			//Loop checking if user wants to stay in the loop (WITH VALIDATION)
			while(true) {
				String userResponse = keyboard.next();
				System.out.println(userResponse);
				if(userResponse.equals("Y") || userResponse.equals("y")) {
					repeat = true;
					break;
				}else if(userResponse.equals("N") || userResponse.equals("n")) {
					repeat = false;
					System.out.println();
					System.out.println("-----------------");
					keyboard.close();
					System.out.println("Scanner stream closed.");
					System.out.println("Exiting program.");
					break;
				}else {
					System.out.println("INVALID ENTRY, try again.");
				}
			}
		}
	}
}
