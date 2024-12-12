package DBAccess;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains the MYSQL queries for FirstLevelDivisions
 */

public class DBFirstLevelDivisions {

    /**
     * This method is used for MYSQL select all for FirstLevelDivisions given country ID
     *
     * @param countryId ID reference to countries
     * @return Returns flist
     */

    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions(int countryId) {

        ObservableList<FirstLevelDivisions> flist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from first_level_divisions where Country_ID = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int firstleveldivisionsId = rs.getInt("Division_ID");
                String firstleveldivisionsName = rs.getString("Division");
                FirstLevelDivisions f = new FirstLevelDivisions(firstleveldivisionsId, firstleveldivisionsName);
                flist.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flist;
    }

    /**
     * This method is used for MYSQL select Division from FirstLevelDivisions given division ID
     *
     * @param division ID reference to division
     * @return Returns rlist
     */

    public static ObservableList<FirstLevelDivisions> select(int division) {

        ObservableList<FirstLevelDivisions> rlist = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setInt(1, division);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String firstleveldivisionsName = rs.getString("Division");
                FirstLevelDivisions r = new FirstLevelDivisions(division, firstleveldivisionsName);
                rlist.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rlist;

    }

    /**
     * This method is used for MYSQL select all from FirstLevelDivisions given countryName
     *
     * @param countryName string reference to country
     * @return Returns flist
     */

    public static ObservableList<FirstLevelDivisions> getFirstLevelDivisionId(String countryName) {

        ObservableList<FirstLevelDivisions> flist = FXCollections.observableArrayList();

        try {
            String sql = "Select * from first_level_divisions where Division = ?";

            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, countryName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int firstleveldivisionsId = rs.getInt("Division_ID");
                String firstleveldivisionsName = rs.getString("Division");
                FirstLevelDivisions f = new FirstLevelDivisions(firstleveldivisionsId, firstleveldivisionsName);
                flist.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flist;
    }
}


