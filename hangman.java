import arc.*;

public class hangman{
	public static void main(String[]args){
		Console con = new Console();
		menu(con);
	}
	public static void menu(Console con){
		int intChoice = 0;
		
		while (intChoice==0){
			con.println("1. Play Game");
			con.println("2. Leaderboard");
			con.println("3. Add Theme");
			con.println("4. Quit");
			con.println("Your choice: ");
			intChoice = con.readInt();
			
			if (intChoice==1){
				
			}else if (intChoice==2){

			}else if (intChoice==3){
				0
			}else if (intChoice==4){
				con.println("Thanks for playing!");
			}else{
				con.println("Options are between 1-4. Try again.");
			}
		}
		
	}
}
			
