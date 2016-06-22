package game.selection;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import gamesDB.GamesDB;

public class AddGame {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String response;
		String gameName;
		String gameSystem;
		
		do {
			//System.out.print("Please enter the game's name: ");
			//String gameName = keyboard.nextLine();
			gameName = JOptionPane.showInputDialog(null, "Enter the game's name");
			//System.out.print("Please enter the game's system: ");
			//String gameSystem = keyboard.nextLine();
			gameSystem = JOptionPane.showInputDialog(null, "Enter the game's system");
			
			Game game = new Game(gameName, gameSystem);
			GamesDB.insert(game);
			
			response = JOptionPane.showInputDialog(null, "Would you like to add another game? \n Enter 'Y' for yes or 'N' for no:");
			//System.out.println("Would you like to add another game?");
			//System.out.print("Enter 'Y' for yes or 'N' for no: ");
			//response = keyboard.nextLine();
		} while (response.equalsIgnoreCase("Y"));
			@SuppressWarnings("unused")
			List<Game> games = GamesDB.retrieveGames();
			keyboard.close();
	}
}