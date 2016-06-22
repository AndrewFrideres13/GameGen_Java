package game.selection;
import java.util.List;
import javax.swing.JOptionPane;
import gamesDB.GamesDB;
/*
 * @author Andrew
 */
public class Game_Select {
	public static void main(String[] args) {
		char reply = 'y';
		String choice;
		JOptionPane.showMessageDialog(null, "Welcome to the Game Selector!");
		
		while (reply == 'y') {
			choice = JOptionPane.showInputDialog(null, "    Would you like to (1) Add a game\n(2) Show games list (3) Remove a game");
			
			if(choice.equalsIgnoreCase("1")) {
				AddGame.main(args);
			} else if(choice.equalsIgnoreCase("2")) {
				@SuppressWarnings("unused")
				List<Game> games = GamesDB.retrieveGames();
			} else if(choice.equalsIgnoreCase("3")) {
				GamesDB.removeGames();
			} else {
				JOptionPane.showMessageDialog(null, choice + " is invalid you fool!\n");
			}
		}
	}
}