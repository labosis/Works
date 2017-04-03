import java.util.Scanner;
import java.util.Random;
public class Assignment1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		boolean loop = true;
		int credits = 10;
		while(loop){
			//DISPLAY--------------------------------------------------------------
			System.out.println("Starting game");
			for(int i=0;i<20;i++){
				System.out.print("**");
			}
			System.out.println("");
			int[] move = new int[3];
			for(int i=0;i<3;i++){
				move[i] = r.nextInt(6) +1;
			}
			System.out.print("your roll: ");
			for(int i=0;i<3;i++){
				System.out.print(move[i]);;
			}
			System.out.println("");
			//CREDIT ASSIGNMENT-----------------------------------------------------
			boolean test7      = true;
			boolean testequal  = true;
			boolean testrow    = true;
			boolean testnonrow = true;
			boolean testeven   = true;
			boolean testodd    = true;
			boolean testone7   = true;
			//test for 7 in a row
			for(int i = 0; i<3;i++){
				if(move[i] != 7){
					test7 = false;
				}
			}
			//test for three equal nunmbers
			for(int i = 0; i<2;i++){
				if(move[i +1] != move[i]){
					testequal = false;
				}
			}
			//test for row sequential order
			for(int i = 0; i<=1;i++){
				if(move[i +1] - move[i] != 1){
					testrow = false;
				}
			}
			//bubble sorts the array to test later for a row in a non-sequential order
			boolean bubble = true;
			while(bubble){
				bubble  = false;
				for(int i = 0; i<2;i++){
					if(move[i +1] < move[i]){
						int temp = 0;
						temp = move[i +1];
						move[i +1] = move[i];
						move[i] = temp;
						bubble = true;
					}
				}
			}
			//test for non-sequential order
			for(int i = 0; i<=1;i++){
				if(move[i +1] - move[i] != 1){
					testnonrow = false;
				}
			}
			//test for 2 even matching numbers
			for(int i=0;i<2;i++){
				if(move[0] == move[2] && move[0] %2 ==0){
					testeven = false;
					break;
				}
				if(move[i] == move[i+1] && move[i] % 2 == 0){
					testeven = false;
				}
			}
			//test for 2 odd matching numbers
			for(int i=0;i<2;i++){
				if(move[0] == move[2] && move[0] %2 !=0){
					testodd = false;
					break;
				}
				if(move[i] == move[i+1] && move[i] % 2 != 0){
					testodd = false;
				}
			}
			//test for at least 1 seven but wont set the variable to false if there are 3 sevens
			int sevencount = 0;
			for(int i=0;i<3;i++){
				if(move[i] == 7){
					sevencount ++;
				}
			}
			if(sevencount ==1 || sevencount ==2){
				testone7 = false;
			}
			//CREDIT EVALUATION------------------------------------------------------			
			if(test7){
				credits += 150;
				System.out.println("7-7-7 in a row +150 credits");
			}
			else if(testrow == true && testnonrow == true){
				credits +=40;
				System.out.println(" Three nunmber in a row + 40 credits");
			}
			else if(testequal){
				credits += 80;
				System.out.println("Three equal numbers +80 credits");
			}
			else if(testrow == false && testnonrow == true){
				credits+= 25;
				System.out.println("Three numbers in a row non-sequential order +25 credits");
				
			}
			else if(testequal == false && testeven ==false){
				credits+=10;
				System.out.println("At least two equal even numbers +10 credits");
			}
			else if(testequal == false && testodd == false){
				credits+= 5;
				System.out.println("At least two equal odd numbers +5 credits");
				System.out.println("");
			}
			else if(testone7 == false){
				System.out.println("At least one 7 +2 credits");
				credits+= 2;
			}
			else{
				System.out.println("No wins o credits");
			}
			//MENU DISPLAY------------------------------------------------------------
			credits--;
			boolean menu = true;
			while(menu){
				System.out.println("1. Display Credits");
				System.out.println("2. Play again");
				System.out.println("3. Exit game");
				int user = in.nextInt();
				if(user == 3){
					loop = false;
					menu = false;
				}else if(user == 2){
					menu = false;
				}
				else if(user == 1){
					System.out.println("Total credits: " + credits);
				}
				else{
					System.out.println("Enter a valid option");
				}
			}
			//TEST FOR CREDIT---------------------------------------------------------
			if(credits < 1){
				break;
			}
				
		}
		System.out.println("");
		System.out.println("Exiting");
		System.out.println("Credits: " + credits);
		in.close();

	}

}
