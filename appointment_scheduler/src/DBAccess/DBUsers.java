package DBAccess;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains the MYSQL queries for Users
 */

public class DBUsers {

    /**
     * This method is used for MYSQL select all for Users
     *
     * @return Returns clist
     */

    public static ObservableList<Users> getAllUsers() {

        ObservableList<Users> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from users";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPass = rs.getString("Password");
                Users c = new Users(userId, userName, userPass);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL select UserId for Users
     *
     * @return Returns clist
     */

    public static ObservableList<Object> getAllUsersId() {

        ObservableList<Object> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select User_ID from users ORDER BY User_ID";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");

                int c = userId;
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL select UserName, Password, UserID
     *
     * @param userName reference to Users name
     * @param userPass ID reference to Users password
     * @return Returns rlist
     * @throws SQLException error checking
     */

    public static ObservableList<Users> select(String userName, String userPass) throws SQLException {

        ObservableList<Users> rlist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT User_Name, Password, User_ID FROM client_schedule.users WHERE User_Name=? and Password=?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, userPass);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String user = rs.getString("User_Name");
                String pass = rs.getString("Password");
                int id = rs.getInt("User_ID");
                Users r = new Users(id, user, pass);
                rlist.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rlist;
    }
}



