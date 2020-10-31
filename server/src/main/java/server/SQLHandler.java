package server;

import java.sql.*;

public class SQLHandler {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psNickname;
    private static PreparedStatement psNewUser;
    private static PreparedStatement psNewNick;


//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        connect();
////        clearTable();
//        prepareAllStatements();
////        System.out.println(getNicknameByLoginAndPassword("qw","qwe"));
////        exInsert();
//        exSelect();
//    }

    public static boolean registration(String login, String password, String nickname) {
        try {
            psNewUser.setString(1, login);
            psNewUser.setString(2, password);
            psNewUser.setString(3, nickname);
            psNewUser.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getNicknameByLoginAndPassword(String login, String password) {
        String nick = null;
        try {
            psNickname.setString(1, login);
            psNickname.setString(2, password);
            ResultSet rs = psNickname.executeQuery();
            while (rs.next()) {
                nick = rs.getString(1);
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nick;
    }


    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:server\\src\\main\\java\\server\\users.db");
            prepareAllStatements();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    private static void prepareAllStatements() throws SQLException {
        psNickname = connection.prepareStatement("SELECT nickname FROM users WHERE login = ? and password = ?;");
        psNewUser = connection.prepareStatement("INSERT INTO users(login, password, nickname) VALUES (?,?,?);");
        psNewNick = connection.prepareStatement("UPDATE users SET nickname = ? WHERE nickname = ?");
    }


    private static void exSelect() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT login, password FROM users;");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
        rs.close();
    }

    private static void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM users;");
    }

    public static boolean changeNick(String nickname, String newNick) {
        try {
            psNewNick.setString(1, newNick);
            psNewNick.setString(2, nickname);
            psNewNick.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void exInsert() throws SQLException {
        stmt.executeUpdate("INSERT INTO users (login, password) " +
                "VALUES ('qwe','qwe'),('asd','asd'),('zxc','zxc')");
    }


    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
