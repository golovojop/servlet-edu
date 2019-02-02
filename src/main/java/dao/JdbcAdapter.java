package dao;

import java.sql.*;

public class JdbcAdapter {

    private Connection connection;
    private Statement statement;

    private JdbcAdapter() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class JdbcAdapterHolder {
        public static JdbcAdapter instance = new JdbcAdapter();
    }

    public static JdbcAdapter getIdbc() {
        return JdbcAdapterHolder.instance;
    }

    // Отключиться от базы
    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    // Выполнить SELECT
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {e.printStackTrace();}
        return rs;
    }

    // Выполнить INSERT
    public void executeInsert(String... sql) {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(sql[0]);

            for(int i = 1; i < sql.length; i++) {
                prepStatement.setString(i, sql[i]);
            }

            prepStatement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
    }

    // Выполнить UPDATE
    public void executeUpdate(int id, String... sql) {
        PreparedStatement prepStatement = null;
        try {
            prepStatement = connection.prepareStatement(sql[0]);

            for(int i = 1; i < sql.length; i++) {
                prepStatement.setString(i, sql[i]);
            }

            prepStatement.setInt(sql.length, id);

            prepStatement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
    }

    // Удалить строку
    public void executeDelete(int id) {
        try {
            statement.execute(String.format("delete from people where id = %s", id));
        } catch (SQLException e) {e.printStackTrace();}
    }
}
