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
				WordCount.close();
				
				//Words array
				String strWords[][] = new String[intWordCount][2];
				TextInputFile words = new TextInputFile(strThemeChoice);
				
				for(intCount= 0; intCount < intWordCount; intCount++){
					strWords[intCount][1] = words.readLine();
					strWords[intCount][1]=(int)(Math.random()*100);
				
				//Bubble sort
				
				

			
					
					
				
				
				
			}	
				
			}else if (intMenu==2){
				//leaderboard code here
				
			}else if (intMenu==3){
				//add theme code over here
			}
		}
	}
}

	
	
