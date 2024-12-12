package DBAccess;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * This class contains the MYSQL queries for Appointments
 */

public class DBAppointments {

    /**
     * This method is used for MYSQL select all for Appointments
     *
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments, contacts where appointments.Contact_ID = contacts.Contact_ID order by Appointment_ID";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL insert for Appointments
     *
     * @param title       user input for appointment title
     * @param description user input for appointment description
     * @param location    user input for appointment location
     * @param contact     user input for appointment contact ID
     * @param type        user input for appointment type
     * @param start       user input for appointment start date/time
     * @param end         user input for appointment end date/time
     * @param customer    user input for appointment customer name
     * @param user        user input for appointment user ID
     * @return Returns int rowsAffected
     * @throws SQLException error checking
     */

    public static int insert(String title, String description, String location, int contact, String type, LocalDateTime start, LocalDateTime end, int customer, int user) throws SQLException {


        String sql = "INSERT INTO appointments (Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID, User_ID) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setInt(4, contact);
        ps.setString(5, type);
        ps.setString(6, String.valueOf(start));
        ps.setString(7, String.valueOf(end));
        ps.setInt(8, customer);
        ps.setInt(9, user);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * This method is used for MYSQL update for Appointments
     *
     * @param title         user input for appointment title
     * @param description   user input for appointment description
     * @param location      user input for appointment location
     * @param contact       user input for appointment contact ID
     * @param type          user input for appointment type
     * @param start         user input for appointment start date/time
     * @param end           user input for appointment end date/time
     * @param customer      user input for appointment customer name
     * @param user          user input for appointment user ID
     * @param appointmentId user input for appointment ID
     * @return Returns int rowsAffected
     * @throws SQLException error checking
     */

    public static int update(String title, String description, String location, int contact, String type, LocalDateTime start, LocalDateTime end, int customer, int user, int appointmentId) throws SQLException {

        String sql = "UPDATE appointments SET Customer_ID = ?, User_ID = ?, Title = ?, Description = ?,  Location = ?, Contact_ID = ?, Type = ?, Start = ?, End = ? WHERE Appointment_ID = ?";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, customer);
        ps.setInt(2, user);
        ps.setString(3, title);
        ps.setString(4, description);
        ps.setString(5, location);
        ps.setInt(6, contact);
        ps.setString(7, type);
        ps.setString(8, String.valueOf(start));
        ps.setString(9, String.valueOf(end));
        ps.setInt(10, appointmentId);


        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * This method is used for MYSQL update for Appointments
     *
     * @param id String of input box we are checking for errors
     * @return Returns int rowsAffected
     * @throws SQLException error checking
     */

    public static int delete(int id) throws SQLException {

        String sql = "DELETE FROM appointments WHERE Appointment_ID =?";

        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, id);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * This method is used for MYSQL select all for Appointments order by type
     *
     * @return Returns clist
     */

    public static ObservableList<Object> allAppointmentType() {

        ObservableList<Object> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select DISTINCT Type from appointments ORDER BY Type";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("Type");

                clist.add(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL select all for Appointments given type input
     *
     * @param appointmentTypeInput input from user
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointmentsByType(String appointmentTypeInput) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments, contacts WHERE Type = ? GROUP BY Appointment_ID order by Appointment_ID";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, appointmentTypeInput);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments given int month input
     *
     * @param month input from user
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointmentsByMonth(int month) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments, contacts WHERE MONTH(Start) = ? and Month(End) = ? group by Appointment_ID ORDER BY Appointment_ID";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments given contactName input
     *
     * @param contactName input from user
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointmentsByContact(String contactName) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments, contacts where contacts.Contact_Name = ? and contacts.Contact_ID = appointments.Contact_ID group by Appointment_ID order by Appointment_ID";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, contactName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments given contactName input, uses now() to find past appointments
     *
     * @param contactName input from user
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointmentsByContactPast(String contactName) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments, contacts where contacts.Contact_Name = ? and contacts.Contact_ID = appointments.Contact_ID and appointments.Start < now() group by Appointment_ID order by Appointment_ID";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, contactName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments, orders by month
     *
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointmentsOrderMonth() {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments, contacts WHERE contacts.Contact_ID = appointments.Contact_ID ORDER BY MONTH(appointments.Start)";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments, orders by week
     *
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointmentsOrderWeek() {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from appointments, contacts WHERE contacts.Contact_ID = appointments.Contact_ID ORDER BY WEEK(appointments.Start)";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments, checks for appointments within 15 min given login userID
     *
     * @param userInput user input for appointment userID
     * @param first     user input for appointment start date/time
     * @param second    user input for appointment end date/time
     * @return Returns alist
     */

    public static ObservableList<Appointments> getAllAppointmentsByLogin(String userInput, LocalDateTime first, LocalDateTime second) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments, users, contacts where User_Name = ? and appointments.Start between ? and ? group by Appointment_ID";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, userInput);
            ps.setString(2, String.valueOf(first));
            ps.setString(3, String.valueOf(second));


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");


                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments, checks for appointments within business hours
     *
     * @param first user input for appointment start date/time
     * @param end   user input for appointment end date/time
     * @return Returns alist
     */

    public static ObservableList<Appointments> appointmentHoursLogic(LocalDateTime first, LocalDateTime end) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "select * from users, appointments, contacts where (dayofweek(?) <'7' and dayofweek(?) > '1') and (hour(?) >='13' or hour(?) <'03') and (dayofweek(?) <'7' and dayofweek(?) > '1') and (hour(?) >='13' or hour(?) <'03') group by User_Name";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(first));
            ps.setString(2, String.valueOf(first));
            ps.setString(3, String.valueOf(first));
            ps.setString(4, String.valueOf(first));
            ps.setString(5, String.valueOf(end));
            ps.setString(6, String.valueOf(end));
            ps.setString(7, String.valueOf(end));
            ps.setString(8, String.valueOf(end));


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");


                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method is used for MYSQL select all for Appointments, checks for appointment time overlap given customer ID
     *
     * @param first      user input for appointment start date/time
     * @param end        user input for appointment end date/time
     * @param customerId user input for customer ID
     * @return Returns alist
     */

    public static ObservableList<Appointments> appointmentOverlapLogic(LocalDateTime first, LocalDateTime end, int customerId) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "select * from appointments, contacts where ? >= Start and ?<= End and ? = Customer_ID";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, String.valueOf(end));
            ps.setString(2, String.valueOf(first));
            ps.setInt(3, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                LocalDateTime appointmentStart = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rs.getTimestamp("End").toLocalDateTime();
                int customer = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contact = rs.getInt("Contact_ID");
                String contactFormat = rs.getString("contacts.Contact_Name");

                Appointments a = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customer, user, contact, contactFormat);
                alist.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alist;
    }

    /**
     * This method converts dateTime input from systemDefault to UTC
     *
     * @param dateTime user input for date/time
     * @return Returns LocalDateTime formatted from systemDefault to UTC
     */

    public static LocalDateTime convertToUtc(LocalDateTime dateTime) {
        ZonedDateTime dateTimeInMyZone = ZonedDateTime.
                of(dateTime, ZoneId.systemDefault());

        return dateTimeInMyZone
                .withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();
    }
}
