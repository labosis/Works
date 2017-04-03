//2908722 Victor Marí Rodríguez
//Second aproach for program in Assigment 2
//For this aproach I am using some of the logic from the suggested solution and changing the way validation is done 

import java.util.*;
public class Assigment2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("String Reversing method: enter a string and will be reversed any nth word");
		System.out.println("Example: 'this is, an example of a string, 3'");
		//MAIN PROGRAM
		String reverse = ReversString(TakeInput());							//Only two functions are used to test and edit the string from the user, validation is done in the two of them.
		System.out.print(reverse + isPangram(reverse.toLowerCase()));		//Reversedword() function is invoked when executing ReversString() function, main function.                             
		//MAIN PROGRAM END
		in.close();
	}
	
	
	
	public static String TakeInput(){										//input will be taken using this function only a coma validation is done
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int looptest = s.lastIndexOf(',');
		
		while(looptest == -1){
			System.out.println("The String has no comas or spaces");
			s = in.nextLine();
			looptest = s.lastIndexOf(',');
		} 
		return s;
	}
	
	
	
	public static String ReversString(String input){											//the string coming from the previus function is passed and validated, if it stills not meeting the conditions it will
																							//repeat the funtion TakeInput() again until both validations are correct.
		String c = input.substring(input.lastIndexOf(',')+1, input.length()).trim();
		String t = input.substring(0,input.lastIndexOf(',')).trim();
		
		//Rpeats whle c is not a number OR string t is empty OR string c is empty OR the value of the integer in the string c without spaces is bigger than the number of words 
		while(!c.chars().allMatch( Character::isDigit)  || t.isEmpty() || c.isEmpty() || Integer.parseInt(c) > t.split(" ").length){		//I got te expresion to check for a number in StackOverflow 
				System.out.println("the parameters are not defined properly enter string again");											// but im not sure how to use lambda "::"
				String newString = TakeInput();
				c = newString.substring(newString.lastIndexOf(',')+1, newString.length()).trim();
				t = newString.substring(0, newString.lastIndexOf(','));
		}
		System.out.println("your string is: " +t);
		System.out.println("your nunmber is: " +c);
		String[] word = t.split(" ");
		String reversed = "";
		//Similar loop to the one in the suggested solution slightly modified
		for(int i = 0; i < word.length; i++){
			if((i + 1) % Integer.parseInt(c) == 0){
				word[i] = ReverseWord(word[i]); //reverses word[i] every nth word
			}
			
			if((i + 1) % Integer.parseInt(c) == 0 &&  word[i].toLowerCase().compareTo(ReverseWord(word[i].toLowerCase())) ==0){ //funtion to compare two strings all in one line in lower case so validation wont bee needed
				word[i] = ReverseWord(word[i]) + " " +"Palindrome!" +" " ; //if the allready reversed word[i] is equal to word[i] reverse again it will change again the string and add palindrome to it
			}
			
			
			reversed += word[i] +" ";
		}
		
		return reversed;
	}
	
	
	
	public static String ReverseWord(String wordToReverse){
		String reversedWord = "";// samw function as the suggested solution
		
		for(int i = wordToReverse.length() - 1; i >= 0; i--){
			reversedWord += wordToReverse.charAt(i);
		}
		
		return reversedWord;
	}
	
	
	
	public static String isPangram(String s){// checks for pangram and autputs at the end o the string
		if(s.length() < 26){
			s = "Not Pangram";
			return s;
		}
		else{
			
			for(char ch='a'; ch<='z'; ch++){
				if(s.indexOf(ch) < 0){
					s = "Not Pangram";
					return s;
				}
			}
		}
		
		s = "Pangram";
		return s;
	}
}