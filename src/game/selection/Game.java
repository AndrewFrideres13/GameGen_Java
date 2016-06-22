package game.selection;
public class Game {
	private String gameName;
	private String gameSystem;
	
	public Game() {
		this.gameName = "";
		this.gameSystem = "";
	}
	
	public Game(String gameName, String gameSystem) {
		if (gameName.equals("")) {
			throw new IllegalArgumentException("Game Name is an empty string.");
		}
		
		if (gameSystem.equals("")) {
			throw new IllegalArgumentException("Game System is an empty string.");
		}
		this.gameName = gameName;
		this.gameSystem = gameSystem;
	}
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameSystem() {
		return gameSystem;
	}
	public void setGameSystem(String gameSystem) {
		this.gameSystem = gameSystem;
	}
	
	@Override
	public String toString() {
		return "[Game Name = " + gameName + " System = " + gameSystem
				+ "]";
	}
}