package DBAccess;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.*;

/**
 * This class contains the MYSQL queries for Countries
 */

public class DBCountries {

    /**
     * This method is used for MYSQL select all for Countries
     *
     * @return Returns clist
     */

    public static ObservableList<Countries> getAllCountries() {

        ObservableList<Countries> clist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from countries";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries c = new Countries(countryId, countryName);
                clist.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }

    /**
     * This method is used for MYSQL select Countries given countryId
     *
     * @param countryId int reference to country by number
     * @return Returns rlist
     */

    public static ObservableList<Countries> select(int countryId) {

        ObservableList<Countries> rlist = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country FROM COUNTRIES WHERE Country_ID = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setInt(1, countryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                String countryName = rs.getString("Country");
                Countries r = new Countries(countryId, countryName);
                rlist.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rlist;
    }
}
