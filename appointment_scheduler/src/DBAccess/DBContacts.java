package DBAccess;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains the MYSQL queries for Contacts
 */

public class DBContacts {

    /**
     * This method is used for MYSQL select all for Contacts
     *
     * @return Returns clist
     */

    public static ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from contacts";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contacts c = new Contacts(contactId, contactName, contactEmail);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL select all for Contacts given Contact_Name
     *
     * @param name user input Contact_Name
     * @return Returns clist
     */

    public static ObservableList<Contacts> getContactsIdByName(String name) {

        ObservableList<Contacts> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from contacts where ? = Contact_Name";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contacts c = new Contacts(contactId, contactName, contactEmail);
                clist.add(c);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL select Contact_Name for Contacts given Contact_ID
     *
     * @param id user input Contact_ID
     * @return Returns clist
     */

    public static ObservableList<Object> getSelectContactsNames(int id) {

        ObservableList<Object> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select Contact_Name from contacts WHERE Contact_ID = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");

                Contacts c = new Contacts(contactId, contactName, contactEmail);
                clist.add(c);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }
}
