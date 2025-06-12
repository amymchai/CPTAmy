import arc.*;
import java.awt.image.BufferedImage;

public class hangman{
	public static void main(String[]args){
		Console con = new Console("Hangman", 1280, 720);		
		String strMenu = "";
		
		//MAIN MENU
		while(strMenu.equalsIgnoreCase("q")==false){
			//draw logo
			con.setDrawColor(java.awt.Color.WHITE);
			//H
			con.drawLine(500, 30, 500, 90);
			con.drawLine(530, 30, 530, 90);
			con.drawLine(500, 60, 530, 60);
			//A
			con.drawLine(540, 90, 560, 30);
			con.drawLine(560, 30, 580, 90);
			con.drawLine(550, 70, 570, 70);
			//N
			con.drawLine(590, 30, 590, 90);
			con.drawLine(620, 30, 620, 90);
			con.drawLine(590, 30, 620, 90);
			//G
			con.drawOval(630, 30, 30, 60);
			con.drawLine(650, 60, 660, 60);
			con.drawLine(660, 60, 660, 90);
			//M
			con.drawLine(670, 30, 670, 90);
			con.drawLine(700, 30, 700, 90);
			con.drawLine(670, 30, 680, 70);
			con.drawLine(680, 70, 700, 30);
			//A
			con.drawLine(710, 90, 730, 30);
			con.drawLine(730, 30, 750, 90);
			con.drawLine(720, 70, 740, 70);
			//N
			con.drawLine(760, 30, 760, 90);
			con.drawLine(790, 30, 790, 90);
			con.drawLine(760, 30, 790, 90);
			
			int intX = 900;
			int intY = 20;
			BufferedImage imgstickman = con.loadImage("stickman.png");
			con.drawImage(imgstickman, intX, intY);
			//some spaces
			con.println();
			con.println();
			con.println();
			con.println();
			con.println();
			
			//menu 
			con.println("(p) Play Game");
			con.println("(l) View Leaderboard");
			con.println("(t) Add Theme");
			con.println("(q) Quit");
			con.println("(h) Help");	
			con.println("Select an option");
			strMenu = con.readLine();
			
			//secret menu
			if (strMenu.equalsIgnoreCase("s")){
				con.clear();
				con.println();
				con.println();
				con.println();
				con.println("Welcome to the secret menu!");
				con.println("Here is a funny joke for you:");
				con.println("What do you call a lion with no eyes?");
				con.println("A lon!");
			}
			
			//help menu
			if (strMenu.equalsIgnoreCase("h")){
				con.clear();
				con.println();
				con.println();
				con.println();
				con.println();
				con.println("Help menu:");
				con.println("Hangman is a guessing game for players");
				con.println("The word to guess is represented by dashes that signify each letter");
				con.println("Try to guess the word");
				con.println("If it is correct, you will win");
				con.println("If it is incorrect, a body part will be drawn on the stickman and a letter will be randomly revealed");
				con.println("The goal is to keep the stickman alive instead of getting hanged");
			}
			
			if (strMenu.equalsIgnoreCase("p")){
				//play game code will go here
				//game logic here
				
				//con.println("TEST: play game");
				
				//get player name
				String strPlayerName;
				con.println();
				con.println("What is your name?");
				strPlayerName = con.readLine();
				
				//count themes
				String strThemes[];
				con.println();
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
				TextInputFile LoadThemes = new TextInputFile("themes.txt");
				for(intCount = 0; intCount < intThemeCount; intCount++){
					strThemes[intCount]= LoadThemes.readLine();
				}
				LoadThemes.close();
				
				//print themes
				con.println("Here are the themes:");
				for(intCount = 0; intCount < intThemeCount; intCount++){
					con.println(strThemes[intCount]);
				}
				
				//choose theme
				con.println("Enter your theme choice (include the .txt):");
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
				//TEST con.println("Selected word: " + strChosenWord);
	
				//count letters in the chosen word
				int intWordLength = strChosenWord.length();
				
				//game setup
				String strAgain;
				strAgain = "yes";
				int intWordList;
				intWordList = 0;
				int intWins = 0;
				
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
						
						// stickman
						if (intFails>=1){
							// drawing
							con.setDrawColor(java.awt.Color.WHITE);
							con.drawLine(450, 600, 450, 200); // vertical line
							con.drawLine(450, 200, 600, 200); // horizontal line
							con.drawLine(600, 200, 600, 250); // rope 
							con.drawOval(575, 250, 50, 50); // head
						} 
						 if (intFails>=2){
							// drawing
							con.setDrawColor(java.awt.Color.WHITE);
							con.drawLine(450, 600, 450, 200); // vertical line
							con.drawLine(450, 200, 600, 200); // horizontal line
							con.drawLine(600, 200, 600, 250); // rope 
							con.drawOval(575, 250, 50, 50); // head
							con.drawLine(600, 300, 600, 400); // body

						}
						 if (intFails>=3){
							// drawing
							con.setDrawColor(java.awt.Color.WHITE);
							con.drawLine(450, 600, 450, 200); // vertical line
							con.drawLine(450, 200, 600, 200); // horizontal line
							con.drawLine(600, 200, 600, 250); // rope 
							con.drawOval(575, 250, 50, 50); // head
							con.drawLine(600, 300, 600, 400); // body
							con.drawLine(600, 320, 560, 360); // left arm
						}
						 if (intFails>=4){
							// drawing
							con.setDrawColor(java.awt.Color.WHITE);
							con.drawLine(450, 600, 450, 200); // vertical line
							con.drawLine(450, 200, 600, 200); // horizontal line
							con.drawLine(600, 200, 600, 250); // rope 
							con.drawOval(575, 250, 50, 50); // head
							con.drawLine(600, 300, 600, 400); // body
							con.drawLine(600, 320, 560, 360); // left arm
							con.drawLine(600, 320, 640, 360); // right arm
						}
						 if (intFails>=5){
							// drawing
							con.setDrawColor(java.awt.Color.WHITE);
							con.drawLine(450, 600, 450, 200); // vertical line
							con.drawLine(450, 200, 600, 200); // horizontal line
							con.drawLine(600, 200, 600, 250); // rope 
							con.drawOval(575, 250, 50, 50); // head
							con.drawLine(600, 300, 600, 400); // body
							con.drawLine(600, 320, 560, 360); // left arm
							con.drawLine(600, 320, 640, 360); // right arm
							con.drawLine(600, 400, 570, 480); // left leg
						}
						 if (intFails>=6){
							// drawing
							con.setDrawColor(java.awt.Color.WHITE);
							con.drawLine(450, 600, 450, 200); // vertical line
							con.drawLine(450, 200, 600, 200); // horizontal line
							con.drawLine(600, 200, 600, 250); // rope 
							con.drawOval(575, 250, 50, 50); // head
							con.drawLine(600, 300, 600, 400); // body
							con.drawLine(600, 320, 560, 360); // left arm
							con.drawLine(600, 320, 640, 360); // right arm
							con.drawLine(600, 400, 570, 480); // left leg
							con.drawLine(600, 400, 630, 480); // right leg
						}
						con.setDrawColor(java.awt.Color.BLACK);
						con.println();
						
						//underlines
						con.println("Word: ");
						int intCount4;
						for (intCount4 = 0; intCount4 < intWordLength; intCount4++){
							con.print(strUnderlines[intCount4] + " ");
						}
						con.println();
						con.println();
					
						//stickman 
						con.println("Body parts drawn: " + intFails + "/6");
					
						con.println();
						con.println("Guess the full word:");
						String strGuess;
						strGuess = con.readLine();
					
						
						if (strGuess.equalsIgnoreCase(strChosenWord)){
							con.println("You got it!");
							con.println("You saved the stickman!");
							blnGuessed = true;
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
					
					//TEST WIN NUMBER: con.println("Wins: "+intWins);
					
				}
			
			//leaderboard
					TextOutputFile addtoleaderboard = new TextOutputFile("leaderboard.txt", true);
					addtoleaderboard.println(strPlayerName);
					addtoleaderboard.println(intWins+"");
					addtoleaderboard.close();
			
			} if (strMenu.equalsIgnoreCase("l")){
				//leaderboard code here
				
				//count the lines
				TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
				int intLineCount = 0;
				while (leaderboard.eof()==false){
					leaderboard.readLine();
					leaderboard.readInt();
					intLineCount++;
				}
				leaderboard.close();
				//TEST: con.println(intLineCount);
				
				//count players by dividing line number by 2 (2 lines for name and win#)
				int intPlayerCount = intLineCount/2;
				String strLeaderboard[][] = new String[intPlayerCount][2];
				
				//reload file for names and wins
				TextInputFile leaderboardinput = new TextInputFile("leaderboard.txt");
				int intCountL = 0;
				for (intCountL = 0; intCountL < intPlayerCount; intCountL++){
					strLeaderboard[intCountL][0] = leaderboardinput.readLine();
					strLeaderboard[intCountL][1] = leaderboardinput.readLine();
				}
				leaderboardinput.close();
				
				//bubble sort by descending wins
				String strTempLeaderName;
				String strTempWins;
				for (int i = 0; i < intPlayerCount - 1; i++){
					for (int I = 0; I < intPlayerCount - 1; I++){
						if(Integer.parseInt(strLeaderboard[I][1]) < Integer.parseInt(strLeaderboard[I+1][1])){
							//swap
							//swap name
							strTempLeaderName = strLeaderboard[I][0];
							strLeaderboard[I][0] = strLeaderboard[I+1][0];
							strLeaderboard[I+1][0] = strTempLeaderName;
							//swap wins
							strTempWins = strLeaderboard[I][1];
							strLeaderboard[I][1] = strLeaderboard[I+1][1];
							strLeaderboard[I+1][1] = strTempWins;
						}
					}
				}
				
				//print leaderboard
				con.println("Leaderboard:");
				for (int i = 0; i < intPlayerCount; i++){
					con.println((i+1)+". "+strLeaderboard[i][0]+" - "+strLeaderboard[i][1] + "wins");
				}
			
			
		
			
				
			} if (strMenu.equalsIgnoreCase("t")){
				//add theme code over here
				
				//creating name of theme file
				con.println("Enter the name of your new theme file (e.g. animals.txt)");
				String strNewThemeName = con.readLine();
				
				//adding words
				con.println("Enter words for the theme one at a time and type 'stop' to finish");
				
				TextOutputFile newThemeFile = new TextOutputFile(strNewThemeName, false);
				String strWord = "";
				while (!strWord.equalsIgnoreCase("stop")){
					con.print("Enter word: ");
					strWord = con.readLine();
					
					if (!strWord.equalsIgnoreCase("stop")){
						newThemeFile.println(strWord);
					}
				}
				newThemeFile.close();
				
				//add theme to master theme list
				TextOutputFile themeslist = new TextOutputFile ("themes.txt", true);
				themeslist.println(strNewThemeName);
				themeslist.close();
				
				con.println("New theme "+strNewThemeName+ " has been created and added to the themes list.");
				
				//refresh the themes list
				TextInputFile refreshThemes = new TextInputFile("themes.txt");
				int intNewThemeCount;
				intNewThemeCount = 0;

				while (refreshThemes.eof()==false){
					refreshThemes.readLine();
					intNewThemeCount++;
				}
				refreshThemes.close();

				String strThemes[];
				strThemes = new String [intNewThemeCount];
				TextInputFile loadNewThemes = new TextInputFile("themes.txt");
				int intCount9=0;
				for (intCount9=0; intCount9 < intNewThemeCount; intCount9++){
					strThemes[intCount9] = loadNewThemes.readLine();
				}
				loadNewThemes.close();			
				
			}	
		}
	}		
}
