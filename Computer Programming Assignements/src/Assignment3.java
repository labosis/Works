import java.util.Scanner;
public class Assignment3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[][]    leftArray   = new char[10][10];
		char[][]    centreArray = new char[10][10];
		char[][]    rightArray  = new char[10][10];
		boolean[][] boatest     = new boolean[10][10];
		boolean[][] boatest2    = new boolean[10][10];
		leftArray = fillArray(leftArray);
		centreArray = fillArray(centreArray);
		rightArray = fillArray(rightArray);
		PrintArrays(leftArray,centreArray,rightArray);
		printmenu();
		String input = in.nextLine();
		while(!input.chars().allMatch( Character::isDigit) ||  Integer.parseInt(input) > 3 || Integer.parseInt(input) < 1){
			System.out.println("enter a number between 1, 2, or 3");
			input = in.nextLine();
		}
		boolean deployTurns = true;
		if(Integer.parseInt(input) == 1){
			
		}
		else if(Integer.parseInt(input) == 2){
			for(int i = 0; i < 2; i++){
				if(deployTurns == true){
					System.out.println("Player 1: ");
					boatest = BoatDeploy(boatest);
					deployTurns = false;
				}
				else{
					System.out.println("Player 2");
					boatest2 = BoatDeploy(boatest2);
					deployTurns = true;
				}
			}
		}
		else{
			
		}
		
	}
	
	
	
	static char[][] fillArray(char[][] chararray){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				chararray[i][j] = '-';
			}
		}
		return chararray;
	}
	
	
	
	static void PrintArrays(char[][] left, char[][] centre, char[][] right){
		for(int i = 0; i < 3; i++){
			System.out.print("  ");
			for(char ch = 'A'; ch <= 'J';ch++){
			System.out.printf("%4c",ch);
			System.out.print(" ");
			}
			System.out.print("                       ");//spaces for letter chart
		}
		System.out.println("");
		System.out.println("");
		for(int i = 0; i < 10; i++){
			System.out.print(i +" ");
			int row = i;
			for(int j = 0; j < 10; j++){
				System.out.printf("%4c",left[i][j]);
				System.out.print(" ");
			}
			System.out.print("                       "+ row +" ");//spaces before charts
			for(int j = 0; j < 10; j++){
				System.out.printf("%4c",centre[i][j]);
				System.out.print(" ");
			}
			System.out.print("                       "+ row +" ");//spaes before charts
			for(int j = 0; j < 10; j++){
				System.out.printf("%4c",right[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
			System.out.println("");
		}
	}
	
	
	
	static void printmenu(){
		System.out.println("");
		for(int i = 0; i < 103; i++){
			System.out.print("-");
		}
		System.out.println("");
		System.out.println("");
		for(int i = 0; i < 45; i++){
			System.out.print("*");
		}
		System.out.print(" OPTION MENU ");
		for(int i = 0; i < 45; i++){
			System.out.print("*");
		}
		System.out.println("");
		System.out.println("");
		for(int i = 0; i < 103; i++){
			System.out.print("-");
		}
		System.out.println("");
		System.out.println("1.Start game placing ships randomly");
		System.out.println("");
		System.out.println("2.Start game by user assigned ships");
		System.out.println("");
		System.out.println("3.Load a saved game from file");
		System.out.println("");
		System.out.println("/** To save at any given move enter 123 as input");
		System.out.println("");
		System.out.println("/** The input format must be: rowº,letter ie: 3,B will shoot to grid 3B");
		System.out.println("");
		System.out.println("/** The grids above are output examples, guesses will appear on the right enemys guesses on the centre");
		System.out.println("");
	}
	
	
	
	static boolean[][] BoatDeploy(boolean[][] boolboat){
		Scanner in = new Scanner(System.in);
		int[] shipsize ={5,4,3,3,2};
		String[] shipname = {"Aircraft","Battleship","Destroyer","submarine","patrol"};
		boolean ismove = false;
		for(int i = 0; i <= 4; i++){
			while(!ismove){
				System.out.println("Enter coordinate for: " + shipname[i] +" " + shipsize[i]);
				String coordinate = takeCoordinate(in.nextLine());
				System.out.println("Enter direction: Nort, South, East, West.");
				String direction = takeDirection(in.nextLine());
				ismove = possibleMove(coordinate, direction,boolboat,shipsize[i]);
				System.out.println(ismove + " inner loop " + i);
			}
			ismove = false;
			System.out.println(i);
		}
		System.out.println(ismove + " outer loop " );
		return boolboat;
	}
	
	
	
	
	static String takeCoordinate(String Input){
		Scanner in = new Scanner(System.in);
		while(Input.indexOf(',') == -1){
			System.out.println("The coordinate needs a coma");
			Input = in.nextLine();
		}
		String col = Input.substring(Input.indexOf(',')+1, Input.length()).trim().toLowerCase();
		String row = Input.substring(0,Input.indexOf(',')).trim();
		
		//Rpeats whle c is not a number OR string t is empty OR string c is empty OR the value of the integer in the string c without spaces is bigger than the number of words 
		while(!row.chars().allMatch( Character::isDigit)||!col.chars().allMatch(Character::isLetter)  || row.isEmpty() || col.isEmpty() || Integer.parseInt(row) > 9 || Integer.parseInt(row) < 0 ||col.compareTo("a") <0 ||col.compareTo("j") > 0){		//I got te expresion to check for a number in StackOverflow 
				System.out.println("the coordinates are not defined properly enter string again");											// but im not sure how to use lambda "::"
				Input = in.nextLine();
				while(Input.indexOf(',') == -1){
					System.out.println("The coordinate needs a coma");
					Input = in.nextLine();
				}
				col = Input.substring(Input.indexOf(',')+1, Input.length()).trim().toLowerCase();
				row = Input.substring(0,Input.indexOf(',')).trim();
		}
		return Input;
	}
	
	
	
	
	static String takeDirection(String Direction){
		Scanner in = new Scanner(System.in);
		String test = "nswe";
		boolean close = false; 
		while(!close){
			if(Direction.isEmpty() == true){
				System.out.println("Empty string enter letter again");
				Direction = in.nextLine();
			}
			else if(test.indexOf(Direction.toLowerCase().charAt(0)) < 0){
				System.out.println("Not valid coordinate, enter n, s, w, e");
				Direction = in.nextLine();
			}
			else{
				close = true;
			}
		}
		return Direction.toLowerCase();
	}

	
	
	
 	static boolean possibleMove(String Input, String Dir, boolean[][] booltest,int size){
 		String possibledirections = "nswe";
		String cols ="abcdefghij";
		String col = Input.substring(Input.indexOf(',')+1, Input.length()).trim().toLowerCase();
		String row = Input.substring(0,Input.indexOf(',')).trim();
		int direction = possibledirections.indexOf(Dir.charAt(0));
		int lettercolumns = cols.indexOf(col);//columns  [change in columns][change in rows]
		int numberows = Integer.parseInt(row);//rows
		int result;
		switch(direction){
			case 0:
				result = numberows - size;
				System.out.println(result + " " + numberows + " " + size);
				if(result < -1){
					return false; 
				}
				else{
					for(int changeinrows = numberows; changeinrows > result; changeinrows--){
						System.out.println(result);
						if(booltest[lettercolumns][changeinrows] == true){
							return false;
						}
					}
					return true;
				}
			case 1:
				result = numberows + size;
				System.out.println(result + " " + numberows + " " + size);
				if(result > 10){
					return false; 
				}
				else{
					for(int changeinrows = numberows; changeinrows < result; changeinrows++){
						System.out.println(result);
						if(booltest[lettercolumns][changeinrows] == true){
							return false;
						}
					}
					return true;
				}
			case 2:
				result = lettercolumns - size;
				System.out.println(result + " " + lettercolumns + " " + size);
				if(result < -1){
					return false; 
				}
				else{
					for(int changeincolumns = lettercolumns; changeincolumns > result; changeincolumns--){
						System.out.println(result);
						if(booltest[changeincolumns][numberows] == true){
							return false;
						}
					}
					return true;
				}
			case 3:
				result = lettercolumns + size;
				System.out.println(result + " " + lettercolumns + " " + size);
				if(result > 10){
					return false; 
				}
				else{
					for(int changeincolumns = lettercolumns; changeincolumns < result; changeincolumns++){
						System.out.println(result);
						if(booltest[changeincolumns][numberows] == true){
							return false;
						}
					}
					return true;
				}
			default: 
				return true;
		}
	}
}
