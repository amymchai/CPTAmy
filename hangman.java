import arc.*;

public class hangman{
	public static void main(String[]args){
		Console con = new Console();
		
		int intMenu = 0;
		
		//MAIN MENU
		while(intMenu!=4){
			con.println("HANGMAN");
			con.println("(1) Play Game");
			con.println("(2) View Leaderboard");
			con.println("(3) Add Theme");
			con.println("(4) Quit");	
			con.println("Select an option");
			intMenu = con.readInt();
			
			if (intMenu==1){
				//play game code will go here
				//game logic here
				
				//con.println("TEST: play game");
				
				//get player name
				String strPlayerName;
				con.println("What is your name?");
				strPlayerName = con.readLine();
				
				//count themes
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
				
				//load data into array
				strThemes = new String[intThemeCount];
				int intCount;
				CountThemes = new TextInputFile("themes.txt");
				for(intCount = 0; intCount < intThemeCount; intCount++){
					strThemes[intCount]=CountThemes.readLine();
				}
				CountThemes.close();
				
				//print themes
				con.println("Here are the themes:");
				for(intCount = 0; intCount < intThemeCount; intCount++){
					con.println(strThemes[intCount]);
				}
				
				//choose theme
				con.println("Enter your theme choice:");
				String strThemeChoice = con.readLine();
				
				//count words in theme file
				TextInputFile WordCount = new TextInputFile(strThemeChoice);
				int intWordCount = 0;
				while(WordCount.eof()==false){
					WordCount.readLine();
					intWordCount++;
				}
				//con.println("TEST WORD COUNT: "+intWordCount);
				WordCount.close();
				
				//assign random numbers
				String strWords[][];
				strWords = new String [intWordCount][2];
				TextInputFile words = new TextInputFile(strThemeChoice);
				
				int intRand;
				int intCOUNT = 0;
				for (intCOUNT = 0; intCOUNT < intWordCount; intCOUNT++){
					strWords[intCOUNT][0]=words.readLine(); //store word
					intRand = (int)(Math.random()*100+1); //random number
					strWords[intCOUNT][1] = intRand +""; //forces integer to be string
					//TEST: con.println(strWords[intCOUNT][0] + " - " + strWords[intCOUNT][1]);
				}
				words.close();
				
				//bubble sort in descending order
				int intCount2=0;
				String strNameTemp;
				String strNumberTemp;
				for (intCount2=0; intCount2 < intWordCount-1; intCount2++){
					for(intCount = 0; intCount < intWordCount-1; intCount++){
						if(Integer.parseInt(strWords[intCount][1]) < Integer.parseInt(strWords[intCount+1][1])){
							//swap
							//swap name
							strNameTemp = strWords[intCount][0];
							strWords[intCount][0]=strWords[intCount+1][0];
							strWords[intCount+1][0]=strNameTemp;
							//swap numbers
							strNumberTemp = strWords[intCount][1];
							strWords[intCount][1]=strWords[intCount+1][1];
							strWords[intCount+1][1]=strNumberTemp;
						}
					}
				}
				
				/*TEST SORT BY PRINTING:con.println("\n\nAfter sorting");
				for(intCount=0; intCount < intWordCount; intCount++){
					con.println(strWords[intCount][0] + " - " + strWords[intCount][1]);
				}*/
				
				//make the word with the highest randomized number the chosen one
				String strChosenWord; 
				strChosenWord = strWords[0][0];
				con.println("Selected word: " + strChosenWord);
	
				//count letters in the chosen word
				int intWordLength = strChosenWord.length();
				
				//game setup
				String strAgain;
				strAgain = "yes";
				int intWordList;
				intWordList = 0;
				
				String strBodyParts[] = new String[7];
				strBodyParts[0] = "Head";
				strBodyParts[1] = "Body";
				strBodyParts[2] = "Left Arm";
				strBodyParts[3] = "Right Arm";
				strBodyParts[4] = "Left Leg";
				strBodyParts[5] = "Right Leg";
				strBodyParts[6] = "Dead";
				
				while (intWordList < intWordCount && strAgain.equalsIgnoreCase("yes")){
					strChosenWord = strWords[intWordList][0];
					intWordLength = strChosenWord.length();
					
					String strUnderlines[] = new String[intWordLength];
					int intCount3;
					for (intCount3 = 0; intCount3 < intWordLength; intCount3++){
						if (strChosenWord.charAt(intCount3)== ' '){
							strUnderlines[intCount3] = " ";
						} else {
							strUnderlines[intCount3] = "_";
						}
					}
				
					boolean blnGuessed;
					blnGuessed = false;
					int intFails;
					intFails = 0;
				
					while (intFails < 7 && blnGuessed == false){
						con.clear();
						con.println("HANGMAN");
						
						//underlines
						con.println("Word: ");
						int intCount4;
						for (intCount4 = 0; intCount4 < intWordLength; intCount4++){
							con.print(strUnderlines[intCount4] + " ");
						}
						con.println();
					
						//stickman DRAW LATER
						con.println("Body parts drawn: " + intFails + "/6");
						int intCount5;
						for (intCount5 = 0; intCount5 < intFails; intCount5++){
							con.print("- " + strBodyParts[intCount5]);
						}
					
						con.println();
						con.println("Guess the full word:");
						String strGuess;
						strGuess = con.readLine();
					
						if (strGuess.equalsIgnoreCase(strChosenWord)){
							con.println("You got it!");
							con.println("You saved the stickman!");
							blnGuessed = true;
							int intWins = 0;
							intWins++;
						} else {
							con.println("Uh oh, wrong guess.");
							intFails++;
						
							//reveal a random letter
							boolean blnLetterRevealed;
							blnLetterRevealed = false;
							int intRandomLetter;
							while (!blnLetterRevealed){
								intRandomLetter = (int)(Math.random()*intWordLength);
								if (!strChosenWord.substring(intRandomLetter, intRandomLetter+1).equals(" ") && strUnderlines[intRandomLetter].equals("_")){
									strUnderlines[intRandomLetter] = strChosenWord.substring(intRandomLetter, intRandomLetter+1);
									blnLetterRevealed = true;
								}
							}
							con.println("Press enter to continue");
							con.readLine();
						}
					}
					
					if (!blnGuessed) {
						con.println("You lost :(");
						con.println("The word was: " + strChosenWord);
					}
					intWordList++;
					
					//ask to play again
					if (intWordList < intWordCount){
						con.println("Do you want to play again? yes/no");
						strAgain = con.readLine();
					} else{
						con.println("No more words left in this theme!");
						strAgain = "no";
					}
				}
				
				
				
				
				
				
							
				
			
			
					
					
					
								
				
				
				
				 
				
				
				
				
			
			
				
			} else if (intMenu==2){
				//leaderboard code here
				
				
				
				
			} else if (intMenu==3){
				//add theme code over here
				
				
								
				
			}
		}
	}
}





	
	
