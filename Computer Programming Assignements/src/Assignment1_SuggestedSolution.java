import java.util.*;
public class Assignment1_SuggestedSolution {

	public static void main(String[] args) {
		//declare variables
		
		int credit = 10;
		int response = 0;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		String menuOption = "";
		
		Scanner scan = new Scanner(System.in);
				
		do {
			
			do {
			
				//display menu
			
				System.out.println("\n          *****************************");
				System.out.println("            --- Slot Machine Menu ---");
				System.out.println("          *****************************\n");
				System.out.println("          1. Display remaining credits");
				System.out.println("          2. Play again");
				System.out.println("          3. Exit game\n");
				System.out.println("          *****************************");
		
				//ask user to choose an option from the menu
			
				System.out.print("Choose option: ");				
				
				//accept input as a string to get full features of character validation
				
				menuOption = scan.nextLine();
				System.out.println("");
				
				//reject alphabetics and special character inputs
				
				if(Character.isLetter(menuOption.charAt(0)) || Character.isLetterOrDigit(menuOption.charAt(0)) == false){
					System.out.println("Invalid input! Enter a number between 1 and 3: ");				
				}
			
			//request user to choose again if input is not a number
				
			}
			
			while(Character.isLetter(menuOption.charAt(0)) || Character.isLetterOrDigit(menuOption.charAt(0)) == false);
			
			//if this is a number, parse it to get integer value
			
			response = Integer.parseInt(menuOption);			
			
			//check whether input is 1,2,3 or out of range
			
			switch (response) {
				case 1:
				{
					//show remaining credits
					
					System.out.println("Your remaining credits: " + credit);
					break;
				}
				case 2:
				{
					//if there is no credit left, show credit and exit
					
					if(credit < 1){
						System.out.println("Game over! You have no credit left to play.");
						System.out.println("Your credit is " + credit);
						System.exit(0);
					}
					else{
						//still have credits, deduct one credit and play the game
						
						credit = credit - 1;
						
						//add 1 to random, in order not to get 0
						
						num1 = (int)(Math.random() * 7 + 1);
						num2 = (int)(Math.random() * 7 + 1);
						num3 = (int)(Math.random() * 7 + 1);
						System.out.println(num1 + " " + num2 + " " + num3);
						
						//7-7-7 hit the jackpot! 150 credits
						
						if((num1==7) && (num2==7) && (num3==7)){
							
							System.out.println("You have hit the jackpot! 150 credits awarded!");
							credit = credit + 150;
							break;
						}
						
						//3 matching numbers. 80 credits
						
						if((num1==num2) && (num2==num3) && (num3==num1)){
							System.out.println("3 matching numbers! 80 cedits awarded!");
							credit = credit + 80;
							break;
						}
						
						//sequential order. 40 credits
						
						if((num1==num2-1) && (num2==num3-1)){
							System.out.println("3 numbers in a row in sequential order! 40 credits awarded!");
							credit = credit + 40;
							break;
						}
						
						//extra feature
						//reversed sequential order. 30 credits
						
						if((num3==num2-1) && (num2==num1-1)){
							System.out.println("3 numbers in a row in reversed sequential order! 30 credits awarded!");
							credit = credit + 30;
							break;
						}
						
						//non-sequential order. 25 credits
						
						if(((num1-num2==1 || num2-num1==1) && (num1-num3==1 || num3-num1==1) && (num2-num3==2 || num3-num2==2)) || ((num1-num2==2 || num2-num1==2) 
								&& (num2-num3==1 || num3-num2==1) && (num1-num3==1 || num3-num1==1))){
							
							System.out.println("3 numbers in a row in non-sequential order! 25 credits awarded!");
							credit = credit + 25;
							break;
						}
						
						//2 matching even. 10 credits
						
						if((num1%2==0) && (num2%2==0) && (num1==num2) || (num2%2==0) && (num3%2==0) && (num2==num3) || (num1%2==0) && (num3%2==0) && (num1==num3) ){
							System.out.println("2 matching even numbers! 10 credits awarded!");
							credit = credit + 10;
							break;
						}
						
						//2 matching odd. 5 credits
						
						if((num1%2==1) && (num2%2==1) && (num1==num2) || (num2%2==1) && (num3%2==1) && (num2==num3) || (num1%2==1) && (num3%2==1) && (num1==num3) ){
							System.out.println("2 matching odd numbers! 5 credits awarded!");
							credit = credit + 5;
							break;
						}
						
						//includes one 7. 2 credits
						
						if(num1==7 || num2==7 || num3==7){
							System.out.println("Includes one 7! 2 credits awarded!");
							credit = credit + 2;
							break;
						}	
						
						//nothing is matched. No credits 
						
						else {
							System.out.println("Sorry! No match! 1 credit deducted!");
							credit = credit - 1;
							break;
						}
					}
				}
				case 3:
				{
					//user chooses to exit
					
					System.out.println("Goodbye! Thank you for playing....");
					System.out.println("Your remaining credits: " + credit);
					System.exit(0);
				}
				default:
				{
					// option out of range
					
					System.out.println("Invalid input! Enter a number between 1 and 3: ");
				}
			}
		} 
		
		while (response != 3);                // until user types 3 to exit
		
		scan.close();		

	}

	}


