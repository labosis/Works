

import java.util.*;

public class Assigment2_SuggestedSolution {

	public static void main(String[] args) {
		/* a Java program to take a string s from the user. 
		 * The string is expected to be a series of words.
		 * Following the string, the user will also enter an integer value n.
		 * The program parses through the String and reverses every nth word.
		 * The Program identifies whether the reversed word is a palindrome.
		 * The Program identifies whether the user entered a pangram.
		 */

		//The String s value is provided by the function userInput	
		String s = userInput();		
	
		//The int n value is provided by the function getNumber
		int n = getNumber(s);
		
		//String s is then changed by the function subS
		s = subS(s);
		
		//Output the values of string s and int n to the user
		System.out.println(" ");
		System.out.println("Your phrase is: " + s);
		System.out.println("You entered the number: " + n);
		
		//String array for storing each of the subS words into separate variables
		String[] phrase = s.split(" ");
		
		//check if the number of words in the phrase is smaller than the number the user entered 
		if(phrase.length < n) {
			System.out.println("\nThe number of words in the phrase is less than the number that you entered.\nUnfortunately, you won't see the wonders I can do this time.\nBye!");
		}
		//if the phrase has at least the length of the given number, execute the function reverseWord, for each nth word 
		else {
			System.out.println("\nYour final result is: ");
			
			for (int i=0 ; i<phrase.length; i++)  {
				
				if(((i+1) % n == 0)) {	
					phrase[i] = reverseWord(phrase[i]);
				}						
			}
		}
		
		/*print the final phrase (after the reversal of the words) in a single line,
		 *  by showing all the variables of the String array called phrase separated by a space character */
		for (int j = 0; j<phrase.length;j++) {
			System.out.print(phrase[j] + " ");
		}
		
		//output a suitable message for the user if the phrase is (or not) a pangram, based on the return of the function pangram
		/*based on the evaluation of function called pangram, a value of false means that the user's input phrase in not a pangram
		 *(either because it does not have the minimum length or because it does not contain all the words of the alphabet) */
		if(pangram(phrase) == false) {
			System.out.println("\nAnd your phrase is not a pangram!"); 
		}
		//the function pangram only returns a value of true when all of the letters of the alphabet were found at least once
		else{
			System.out.println("\nAnd your phrase is a pangram!"); 
		}
	}
	
	//METHODS (1-5):
	
	//1 - Function for taking and validating the user input
	public static String userInput(){
				
		String s = "a";
		
		//initializing a method for reading user input
		Scanner sc = new Scanner(System.in);
		
		//
		do {
			//check if the user input is blank 
			if(s.isEmpty()) {
				System.out.println("");
				System.out.println(" Please enter a string followed by a comma and an integer between 1 and 7! Example 'Hello world, 2'");
			}
			
			System.out.println("Please enter a string followed by an integer between 1 and 7. Example: 'Hello world, 2'");
			
			s = sc.nextLine();
		} while(s.isEmpty());
		
		//storing the index value of the last occurrence of the comma in the phrase, to use it as the delimiter of the phrase
		int lastIndexOfComma = s.lastIndexOf(",");
				
		//storing the supposed number that the user provided (i.e. the whole string after the comma) into the string variable called number
		String number = s.substring(lastIndexOfComma+1, s.length()); 
		number = number.trim();
				
		/*checking if user input contains a comma as a delimiter, in order to use it in the next step of the application OR
		 * if the last word of the phrase contain only numbers OR
		 * if the number entered is between the expected interval (1-7) */
		do{
			if((lastIndexOfComma == -1) || (!(number.matches("[0-9]+"))) || (getNumber(s) < 1 || getNumber(s) > 7 )) {
				
				System.out.println("Sorry, your phrase did not match the required format.");
				System.out.println("Please enter a string followed by an integer between 1 and 7. Example 'Hello world, 2'");
				System.out.println("For example:");
				System.out.println("Hello World, 2");
				s = sc.nextLine();
				
				lastIndexOfComma = s.lastIndexOf(",");
				
				number = s.substring(lastIndexOfComma+1, s.length());
				number = number.trim();
			}
		} while((lastIndexOfComma == -1) || (!(number.matches("[0-9]+"))) || (getNumber(s) < 1 || getNumber(s) > 7 ));
	
		sc.close();
		
		return s;
	}

	//2 - Function to extract the number from the input string
	public static int getNumber (String s) {

		//integer for storing the number that user entered, after separating and converting it from the string
		int n = 0;
		
		//storing the index of the last comma of the phrase in the variable lastIndexOfComma
		int lastIndexOfComma = s.lastIndexOf(",");
		
		//storing the character of the numeric number into the char variable called number
		String number = s.substring(lastIndexOfComma+1, s.length());
		number = number.trim();

		//getting the numeric value of the char called number
		n =  Integer.parseInt(number);

		return n;		
	}
	
	//3 - Function for taking only the phrase from the user input
	public static String subS (String s) {
		//storing the index of the last comma of the phrase in the variable lastIndexOfComma
		int lastIndexOfComma = s.lastIndexOf(",");
		//getting the text of interest from the user input before the last comma
		String x = s.substring(0, lastIndexOfComma);
		
		return x;		
	}
	
	//4 - Function for reversing every nth word of the user input. It also checks if each nth word is a palindrome
	public static String reverseWord(String s) {
		String y = "";
		
		for(int i = s.length()-1; i >= 0; i--){
			if(Character.isLetter(s.charAt(i))){
			    y = y + s.charAt(i);
			}
		}
		if(isPalindrome(s)) {
			System.out.print(s + " is a palindrome!\n");
		}
		
		return y;		
	}
	
	/*5 - function for checking if the whole phrase is a pangram, adapted from a method available at:
	 * http://stackoverflow.com/questions/29173540/code-to-tell-whether-a-string-is-a-pangram-or-not
	 */
	
	public static boolean isPalindrome(String s){ //Check for palindromes
    	s = cleanString(s); //Make sure s has only letters
    	s = s.toLowerCase(); //And is lower case
    	int lastChar = s.length()-1; //get the position of the last Char
    	for (int i = 0; i < lastChar; i++){ //Iterate through (It will compare first to last, second to second to last and so on (0+n to last-n). In an odd String they will not compare the last one, but it will be itself
			
    		if (s.charAt(lastChar) != s.charAt(i)){//If something is not equal through our iterations Return false
    			return false;
    		}
    		lastChar--; //take one for the last so the loop works properly
    	}
    	return true; //If we didn't return false, we should return true
    }
	
	public static String cleanString(String s){ //A simple regex method to clean everything
    	s = s.replaceAll("[^A-Za-z]",""); //Thank for wikipedia for the tutorial  https://en.wikipedia.org/wiki/Regular_expression#Examples
    	return s;
    }
	
	public static boolean pangram (String[] phrase) {
		//declaring variables
		//integer variable to store the number of the letters of the alphabet, which is going to be the guard of the loop if the user's phrase input has a chance (or not) to be a pangram
		int n = 26;
		//string to store the array of Strings that is the input of this method 
		String condensedPhrase = "";
		
		//transform the array of Strings that is the input of this method into a String by concatenating each variable of the array
		for(int i=0; i <phrase.length; i++){
			condensedPhrase += phrase[i];
		}
		
		/*transform the String content to only upper case letters, so the conditional statement inside the loop below (marked with !! in the comments)
		 * was simplified when compared to the original of the stackroverflow.
		 * In that one, besides doing a check for the upper case, the check also went on an lower case letters by casting the sum of its value added with 32
		 * (once all the lower case in ASCII table are the value of upper case plus 32) */
		condensedPhrase = condensedPhrase.toUpperCase();
		
		/*this check returns a false value if the string does not have the minimum length to be tested as a pangram
		 *  (in this case, if it is lesser then 26 length, for the English alphabet) */
		if(condensedPhrase.length() < n){
			return false;
		}
		
		//check if, beginning from A, and checking until Z, all the letters are contained in the phrase
		for(char letter = 'A'; letter <= 'Z' ; letter++){
			//!! this conditional statement is based on the idea that if a character is not found in the string you are passing through, it gives a -1 as an answer. 
			if(condensedPhrase.indexOf(letter) < 0){
				//at the moment that one letter in the check is not found, the loops ends returning a false value
				return false;
		    }
		}
		//if none of the two previous checks gave a positive result, then the method will return a true value
		return true;
	}
}
		