package gamesDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import game.selection.Game;

public class GamesDB {
	public static void insert(Game game) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			// register the driver 
			final String sDriverName = "org.sqlite.JDBC";
			Class.forName(sDriverName);
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:games.db");
			final int iTimeout = 30;
			final String insertGame = "INSERT INTO games (gameName, gameSystem) VALUES ('"
					+ game.getGameName() + "', '" + game.getGameSystem() + "');";
			statement = connection.createStatement();
			statement.setQueryTimeout(iTimeout);
			statement.execute(insertGame);
		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != statement) {
					statement.close();
				}
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Game> retrieveGames() {
		final List<Game> games = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		Random rndm = new Random();
		int response = 0;
		
		try {
			// register the driver 
			final String sDriverName = "org.sqlite.JDBC";
			Class.forName(sDriverName);
			 
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:games.db");
			statement = connection.createStatement();
			final int iTimeout = 30;
			statement.setQueryTimeout(iTimeout);
			final String selectGames = "SELECT * FROM games";
			final ResultSet results = statement.executeQuery(selectGames);

			buildGames(games, results, statement);
			
			ResultSet gamesResults = statement.executeQuery("SELECT gameName, gameSystem FROM games");
		    JTable gameTable = new JTable(buildTableModel(gamesResults));
		    JOptionPane.showMessageDialog(null, new JScrollPane(gameTable));
		    response = JOptionPane.showConfirmDialog(null, "Select a Random game?");
	        
		    if (response == 0) {
		    	int rndmGameIndex = rndm.nextInt(games.size());
		    	Game game = games.get(rndmGameIndex);
		    	JOptionPane.showMessageDialog(null, game);
		    }
		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != statement) {
					statement.close();
				}
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
		return games;
	}
	
	public static List<Game> removeGames() {
		final List<Game> games = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		
		try {
			String gameName;
			// register the driver 
			final String sDriverName = "org.sqlite.JDBC";
			Class.forName(sDriverName);
			 
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:games.db");
			statement = connection.createStatement();
			final int iTimeout = 30;
			
			gameName = JOptionPane.showInputDialog(null, "Enter the game's name that you wish to remove");
			
				if (gameName != null) {
					final String removeGame = "DELETE FROM games WHERE gameName = '"
							+ gameName + "'";
					statement = connection.createStatement();
					statement.setQueryTimeout(iTimeout);
					int gameDeleted = statement.executeUpdate(removeGame);	
					
					if (gameDeleted > 0) {
						JOptionPane.showMessageDialog(null, "       Removed " + gameName);
					} else {
						JOptionPane.showMessageDialog(null, "Please enter a valid game that you wish to remove");
					}
				}
		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
				if (null != statement) {
					statement.close();
				}
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
		return games;
	}
	
	private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		 ResultSetMetaData metaData = rs.getMetaData();

		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		    return new DefaultTableModel(data, columnNames);
	}

	private static void buildGames(List<Game> games, ResultSet results, Statement statement) throws SQLException {
		while (results.next()) {
			final String gameName = results.getString("gameName");
			final String gameSystem = results.getString("gameSystem");
			final Game newGame = new Game(gameName, gameSystem);
			games.add(newGame);
		}
	}
}