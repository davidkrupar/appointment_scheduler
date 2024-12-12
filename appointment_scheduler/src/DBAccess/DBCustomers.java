package DBAccess;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains the MYSQL queries for Customers
 */

public class DBCustomers {

    /**
     * This method is used for MYSQL select all for Customers
     *
     * @return Returns clist
     */

    public static ObservableList<Customers> getAllCustomers() {

        ObservableList<Customers> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Country_ID from customers, first_level_divisions where customers.Division_ID=first_level_divisions.Division_ID";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int customerDivision = rs.getInt("Division_ID");
                int customerCountry = rs.getInt("Country_ID");


                Customers c = new Customers(customerId, customerName, customerAddress, customerPostal, customerPhone, customerDivision, customerCountry);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL insert for Customers
     *
     * @param name     user input for Customer name
     * @param address  user input for Customer address
     * @param postal   user input for Customer postal
     * @param phone    user input for Customer phone
     * @param division user input for Customer division
     * @return Returns int rowsAffected
     * @throws SQLException error checking
     */

    public static int insert(String name, String address, String postal, String phone, int division) throws SQLException {


        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                "VALUES(?,?,?,?,?)";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postal);
        ps.setString(4, phone);
        ps.setInt(5, division);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * This method is used for MYSQL delete for Customers
     *
     * @param id int reference to Customer
     * @return Returns int rowsAffected
     * @throws SQLException error checking
     */

    public static int delete(int id) throws SQLException {

        String sql = "DELETE FROM customers WHERE Customer_ID =?";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setInt(1, id);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * This method is used for MYSQL select Customer ID
     *
     * @return Returns clist
     */

    public static ObservableList<Object> selectCustomers() {

        ObservableList<Object> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select Customer_ID from customers ORDER BY Customer_ID";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");

                clist.add(customerId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL update for Customers
     *
     * @param name     user input for Customer name
     * @param address  user input for Customer address
     * @param postal   user input for Customer postal
     * @param phone    user input for Customer phone
     * @param division user input for Customer division
     * @param id       user input for Customer id
     * @return Returns int rowsAffected
     * @throws SQLException error checking
     */

    public static int update(String name, String address, String postal, String phone, int division, int id) throws SQLException {

        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postal);
        ps.setString(4, phone);
        ps.setInt(5, division);
        ps.setInt(6, id);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * This method is used for MYSQL select Customer Name
     *
     * @param id Customer ID
     * @return Returns clist
     */

    public static ObservableList<Customers> selectCustomersName(int id) {

        ObservableList<Customers> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select Customer_Name where Customer_ID = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int customerDivision = rs.getInt("Division_ID");
                int customerCountry = rs.getInt("Country_ID");

                Customers c = new Customers(customerId, customerName, customerAddress, customerPostal, customerPhone, customerDivision, customerCountry);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }
}
