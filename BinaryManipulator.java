
/* CREATOR: Nicholas Wojcik
 * 
 * DESCRIPTION: This class handles all the logic of the binary and decimal conversion, it is used by PromptOutput.java
 * 				to output data to the console.
 * 
 */

import java.util.BitSet;

public class BinaryManipulator {
	
	//*****************************DECIMAL TO BINARY***********************************************************************

	//ACCEPTS DECIMAL STRING AND RETURNS ONES COMPLIMENT STRING----------------------------------------
	public String decimalToOnes(int number){
		
			//Checks if negative
			if(number < 0) {
				
				//Passes decimal to decToBin function to return binary form, 
				//then puts it in a character array
				char[] charRepresentation = decToBin(number).toCharArray();
				
				//Loop that flips the bits in the character array
				for(int x = charRepresentation.length-1; x >= 0; x--) {
					if(charRepresentation[x] == '0') {
						charRepresentation[x] = '1';
					}else {
						charRepresentation[x] = '0';
					}
				}
				
				//Returns flipped binary
				return String.valueOf(charRepresentation);
			}
			
			//Returns binary without flip if decimal is not negative
			else {
				return decToBin(number);
			}
	}
	
	//ACCEPTS NUMERIC STRING AND RETURNS TWOS COMPLIMENT STRING-----------------------------------------
	public String decimalToTwos(int dec){
		
		//Checks if the decimal is negative
		if(dec < 0) {
			
			//Passes the a decimal through the decimalToOnes function and puts it in a character array
			char[] charRepresentation = decimalToOnes(dec).toCharArray();
			
			//Variable to check if a carry is present during addition
			boolean carry = true;
			
			//Loop that adds 1 to the character array continuing the binary string
			for(int x = charRepresentation.length-1; x >= 0; x--) {
				if(charRepresentation[x] == '1') {
					if(carry) {
						charRepresentation[x] = '0';
						carry = true;
					}else {
						charRepresentation[x] = '1';
						carry = false;
					}
				}else {
					if(carry) {
						charRepresentation[x] = '1';
						carry = false;
					}else {
						charRepresentation[x] = '0';
						carry = false;
					}
				}
			}
			
			//Returns the flipped binary string after adding 1 to it
			return String.valueOf(charRepresentation);
		}
		else {
			
			//Returns the ordinary binary if the decimal is not negative
			return decToBin(dec);
		}
	}
	
	//ACCEPTS NUMERIC STRING AND RETURNS SIGNED BINARY STRING-------------------------------------------
	public String decimalToSigned(int dec){
		
		//Converts a decimal to binary and then stores it in a string
		String binaryRepresentation = decToBin(dec);
		
		//Checks if the decimal is neg/pos and returns after appending the appropriate significant digit
		if(dec < 0) {
			return "1" + binaryRepresentation;
		}
		else
			return "0" + binaryRepresentation;
	}
	
	//ACCEPTS NUMERIC STRING WITH EXCESS PARAMETER AND RETURNS EXCESS STRING
	public String decimalToExc(int dec, int exc){
		
		//Adds the decimal and excess number then converts and returns it in binary
		int temp = dec + exc;
		return decToBin(temp);
	}
	
	//*****************************BINARY TO DECIMAL***********************************************************
	
	//ACCEPTS STRING WITH BINARY DATA AND RETURNS TWOS COMPLIMENT IN A STRING-----------------------
	public String binaryToTwos(int bin){
		
			//Puts the binary string into a character array
			char[] charRepresentation = Integer.toString(bin).toCharArray();
			
			//Checks if the number is negative
			if(charRepresentation[0] == '1') {
				
				//Loops that flips the character array bits
				for(int x = charRepresentation.length-1; x >= 0; x--) {
					if(charRepresentation[x] == '0') {
						charRepresentation[x] = '1';
					}else {
						charRepresentation[x] = '0';
					}
				}
				
				//Variable to check if a carry bit is present
				boolean carry = true;
				
				//Loop that adds 1 to the character array binary string
				for(int x = charRepresentation.length-1; x >= 0; x--) {
					if(charRepresentation[x] == '1') {
						if(carry) {
							charRepresentation[x] = '0';
							carry = true;
						}else {
							charRepresentation[x] = '1';
							carry = false;
						}
					}else {
						if(carry) {
							charRepresentation[x] = '1';
							carry = false;
						}else {
							charRepresentation[x] = '0';
							carry = false;
						}
					}
				}
				
				//Returns flipped binary after adding one to it and calling the binToDec function to convert it
				return "-" + binToDec(Integer.parseInt(String.valueOf(charRepresentation)));
			}
			else {
				
				//Returns the binary without flipping/adding 1, but still uses binToDec to convert it
				return "+" + binToDec(Integer.parseInt(String.valueOf(charRepresentation)));
			}
			
	}
	
	//ACCEPTS STRING WITH BINARY IN IT AND RETURNS ONES COMPLIMENT STRING-----------------------------
	public String binaryToOnes(int bin){
		
		//Puts the binary string into a character array
		char[] charRepresentation = Integer.toString(bin).toCharArray();
		
		//Checks if the binary is negative
		if(charRepresentation[0] == '1') {
			
			//Loop to flip the bits within the character array
			for(int x = charRepresentation.length-1; x >= 0; x--) {
				if(charRepresentation[x] == '0') {
					charRepresentation[x] = '1';
				}else {
					charRepresentation[x] = '0';
				}
			}
			
			//Returns the flipped binary string after converting it to decimal
			return "-" + binToDec(Integer.parseInt(String.valueOf(charRepresentation)));
		}
		else {
			
			//Returns the converted binary string without flipping it
			return "+" + binToDec(Integer.parseInt(String.valueOf(charRepresentation)));
		}
		
	}
	//ACCEPTS STRING OF BINARY AND RETURNS A SIGNED MAGNITUDE STRING------------------------------------------
	public String binaryToSigned(int bin){
		
		//Puts the string of binary numbers into a character array
		char[] charRepresentation = Integer.toString(bin).toCharArray();
		
		//If the leading significant bit is 1 then it changes it to zero, converts, and prepends a negative sign
		if(charRepresentation[0] == '1') {
			charRepresentation[0] = '0';
			return "-" + binToDec(Integer.parseInt(String.valueOf(charRepresentation)));
		}
		else {
			
			//Returns the binary string after converting it to decimal and prepending a "+"
			return "+" +  binToDec(Integer.parseInt(String.valueOf(charRepresentation)));
		}
	}
	
	//ACCEPTS STRING OF BINARY AND EXCESS INT, RETURNS STRING AFTER CONVERTING TO DECIMAL AND ADDING EXCESS----
	public String binaryToExc(int bin, int exc){
		int temp = Integer.parseInt(binToDec(bin));
		temp = temp + exc;
		return Integer.toString(temp);
	}
	
	//ACCEPTS AN INTEGER, RETURNS A STRING WITH THE BINARY FORM IN IT
	public String decToBin(int num) {
		
		//Strips the sign from the decimal
		int temp = Math.abs(num);
		String returnString = "";
		
		//Uses the modulus to calculate remainders and prepending the remainder to a return string
		//The remainder is prepended instead of appended to mimic the down to up answer while calculating the binary by hand
		while(temp > 0) {
			int remainder = temp % 2;
			returnString = remainder + returnString;
			temp = temp / 2;
		}
		return returnString;
	}
	
	//ACCEPTS STRING OF BINARY AND RETURNS A STRING WITH THE BINARY CONVERTED TO DECIMAL----------------
	public String binToDec(int num) {
		
		//Variables for the painful for-loop to follow
		int cursor = 0;
		int temp = 0;
		int returnInt = 0;
		
		//Incredibly painful for-loop that calculates the decimal equivalent of the inputed int
		//In hindsight, a while loop would have been easier
		for(int x = num; x != 0; cursor++) {
			temp = x%10;
			returnInt += temp*Math.pow(2, cursor);
			x = x/10;
		}
		return Integer.toString(returnInt);
		
	}
}
