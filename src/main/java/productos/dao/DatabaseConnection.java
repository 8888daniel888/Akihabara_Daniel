package productos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private Connection conn;
	
	public DatabaseConnection() {
		try {
			// Carga del driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			String usr = "root";
			String pwd = "campusfp";
			String url = "jdbc:mysql://localhost:3306/akihabara_db";
			
			// Conexión a la base de datos
			conn = DriverManager.getConnection(url,usr,pwd);
			
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (SQLException ex) {
			System.out.println(ex);
		} 
	}
	
	public Connection getConnection() {
		return conn;
	}
}
