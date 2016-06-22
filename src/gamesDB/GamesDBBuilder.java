package gamesDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GamesDBBuilder {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		
		try {
			// register the driver 
			final String sDriverName = "org.sqlite.JDBC";
			Class.forName(sDriverName);
			
			final String DB_URL = "jdbc:sqlite:games.db";
			// create a database connection
			connection = DriverManager.getConnection(DB_URL);
			statement = connection.createStatement();
			final int iTimeout = 30;
			statement.setQueryTimeout(iTimeout);
			statement.execute("DROP TABLE if exists games");
			final String createGameTable = "CREATE TABLE games (id INTEGER, gameName CHAR(50), gameSystem CHAR(15));";
			System.out.println(createGameTable);
			statement.execute(createGameTable);
		} catch (final Exception e) {
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
}