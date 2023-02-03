package datos;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conn;

    public boolean conectarse() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*String url = "jdbc:mysql://127.0.0.1:3306/simame?useSSL=false&serverTimezone=UTC";
            String usuario = "root";
            String password ="123456789";*/
            String url = "jdbc:mysql://localhost:3306/simame?useSSL=false&serverTimezone=UTC";
            String usuario = "root";
            String password = "9649";
            conn = (Connection) DriverManager.getConnection(url, usuario, password);
            return true;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public boolean desconectarse() {
        try {
            this.conn.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
