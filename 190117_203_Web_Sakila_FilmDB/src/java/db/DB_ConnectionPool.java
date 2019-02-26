package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_ConnectionPool implements DB_Config {

    private Queue<Connection> connPool = new LinkedList<>();
    private final int MAX_CONN = 100;
    private int numConn = 0;
    
    public static DB_ConnectionPool instance;

    public static DB_ConnectionPool getInstance()  {
        if (instance == null) {
            synchronized (DB_ConnectionPool.class) {
                instance = new DB_ConnectionPool();
            }
        }
        return instance;
    }

    private DB_ConnectionPool() {
        try {
            Class.forName(DB_Config.DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver not found!: "+ex);
        }
    }

    public Connection getConnection() throws SQLException {
        synchronized (connPool) {
            if (connPool.isEmpty()) {
                if (numConn < MAX_CONN) {
                    Connection connection = DriverManager.getConnection(DB_URL + DB_DATABASE_NAME, DB_USERNAME, DB_PASSWD);
                    numConn++;
                    return connection;
                }
                throw new RuntimeException("Try again later!");
            }
            return connPool.poll();
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (connPool) {
            connPool.offer(connection);
        }
    }
}
