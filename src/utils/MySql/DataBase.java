package utils.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase extends DbConn {
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(this.dbUrl, this.user, this.pwd);
	}
}
