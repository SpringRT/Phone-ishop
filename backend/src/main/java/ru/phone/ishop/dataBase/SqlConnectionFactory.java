package ru.phone.ishop.dataBase;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionFactory {
//    private static final String URL = "jdbc:mysql://localhost:3306/phone_ishop";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "databasemysql";

    public static Connection createNewConnection() throws Exception {
//        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/PHONE_ISHOP_DB");
        Connection connection = ds.getConnection();
        connection.setAutoCommit(true);
        return connection;

    }
}
