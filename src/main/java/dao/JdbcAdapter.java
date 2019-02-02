package dao;

import javax.servlet.ServletContext;
import java.sql.*;

public class JdbcAdapter {

    private Connection connection;
    private Statement statement;

    public JdbcAdapter(ServletContext context) {
        try {

            String path = context.getRealPath("users.db");
            System.out.println(path);
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Отключиться от базы
    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Выполнить SELECT
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // Выполнить INSERT
    public void executeInsert(String... sql) {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(sql[0]);

            for (int i = 1; i < sql.length; i++) {
                prepStatement.setString(i, sql[i]);
            }

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Выполнить UPDATE
    public void executeUpdate(int id, String... sql) {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(sql[0]);

            for (int i = 1; i < sql.length; i++) {
                prepStatement.setString(i, sql[i]);
            }

            prepStatement.setInt(sql.length, id);

            prepStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удалить строку
    public void executeDelete(int id) {
        try {
            statement.execute(String.format("delete from people where id = %s", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
