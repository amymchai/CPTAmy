import arc.*;

public class hangman{
	public static void main(String[]args){
		Console con = new Console();
		
		int intMenu = 0;
		
		//MAIN MENU
		while(intMenu!=4){
			con.println("HANGMAN");
			con.println("1. Play Game");
			con.println("2. View Leaderboard");
			con.println("3. Add Theme");
			con.println("4. Quit");	
			con.println("Select an option");
			intMenu = con.readInt();
			
			if (intMenu==1){
				//Play game code will go here
				//game logic here
				
				con.println("TEST: play game");
				
				//Get player name
				String strPlayerName;
				con.println("What is your name?");
				strPlayerName = con.readLine();
				
				//Count themes
				String strThemes[];
				TextInputFile CountThemes = new TextInputFile("themes.txt");
				String strTemp;
				int intThemeCount = 0;
				while(CountThemes.eof()==false){
					strTemp = CountThemes.readLine();
					intThemeCount++;
				}
				CountThemes.close();
				//con.println("TEST:"+intThemeCount);
				
				//Load data into array
				strThemes = new String[intThemeCount];
				int intCount;
				CountThemes = new TextInputFile("themes.txt");
				for(intCount = 0; intCount < intThemeCount; intCount++){
					strThemes[intCount]=CountThemes.readLine();
				}
				CountThemes.close();
				
				//Print themes
				con.println("Here are the themes:");
				for(intCount = 0; intCount < intThemeCount; intCount++){
					con.println(strThemes[intCount]);
				}
				
				//Choose theme
				con.println("Enter your theme choice:");
				String strThemeChoice = con.readLine();
				
				//Count words in theme file
				TextInputFile WordCount = new TextInputFile(strThemeChoice);
				int intWordCount = 0;
				while(WordCount.eof()==false){
					WordCount.readLine();
					intWordCount++;
				}
				//con.println("TEST WORD COUNT: "+intWordCount);
				WordCount.close();
				
				//Assign random numbers
				String strWords[][];
				strWords = new String [intWordCount][2];
				TextInputFile words = new TextInputFile(strThemeChoice);
				
				int intRand;
				int intCOUNT = 0;
				for (intCOUNT = 0; intCOUNT < intWordCount; intCOUNT++){
					while(words.eof()==false){
						strWords[intCount][0]=words.readLine(); //store word
						intRand = (int)(Math.random()*100+1); //random number
						strWords[intCount][1] = intRand +""; //forces integer to be string
						con.println(strWords[intCount][0] + strWords[intCount][1]);
					}
				}
				words.close();
				
				//bubble sort in descending order
				int intA;
				int intB;
				String strNameTemp;
				String strNumberTemp;
				
				for(intA = 0; intA < intWordCount -1; intA++){
					for(intB = 0; intB < intWordCount -1; intB++){
						if(Integer.parseInt(strWords[intB][1]) < Integer.parseInt(strWords[intB+1][1])){
							//swap words
							strNameTemp = strWords[intB][0];
							strWords[intB][0]=strWords[intB+1][0];
							strWords[intB+1][0]=strNameTemp;
							
							//swap numbers
							strNumberTemp = strWords[intB][1];
							strWords[intB][1]=strWords[intB+1][1];
							strWords[intB+1][1] = strNumberTemp;
						}
					}
				}
				
				//test print sorted array
				con.println("\n\nAfter sorting");
				int intCount2;
				for(intCount2 = 0; intCount2 < intWordCount; intCount2++){
					con.println(strWords[intCount2][0] + "-" + strWords[intCount2][1]);
				}
				String strChosenWord;
				strChosenWord = strWords[0][0];
				con.println("TEST: chosen word is "+strChosenWord);
	
		
				
				
				//hi
			
			
				
			}else if (intMenu==2){
				//leaderboard code here
				
			}else if (intMenu==3){
				//add theme code over here
			}
		}
	}
}

	
	
