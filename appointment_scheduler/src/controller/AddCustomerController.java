package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.Countries;
import model.FirstLevelDivisions;
import resources.FormatFunction;
import resources.RootInputException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the interface of Add Customer page
 */
public class AddCustomerController implements Initializable {

    /**
     * This method is used for our Lambda #1, it is used to set a string with a standard format for errors.
     *
     * @param str    String of input box we are checking for errors
     * @param format used to enter lambda which loads standardized string for error messages
     * @return Returns result will appear in error message box used to check for empty values
     */

    public static String IOFormatted(String str, FormatFunction format) {
        String result = format.run(str);
        return result;
    }

    FormatFunction add = (f) -> "Please enter value for " + f;
    Stage stage;
    Parent scene;

    @FXML
    private TextField addCustomerAddress;

    @FXML
    private Button addCustomerCancel;

    @FXML
    private TextField addCustomerName;

    @FXML
    private TextField addCustomerPhone;

    @FXML
    private TextField addCustomerPostalCode;

    @FXML
    private Button addCustomerSave;

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private ComboBox<FirstLevelDivisions> stateComboBox;

    @FXML
    void onAddCustomerAddress(ActionEvent event) {

    }

    /**
     * This method returns user to Overview page.
     *
     * @param event Triggers with button click
     */
    @FXML
    void onAddCustomerCancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onAddCustomerName(ActionEvent event) {

    }

    @FXML
    void onAddCustomerPhone(ActionEvent event) {

    }

    @FXML
    void onAddCustomerPostalCode(ActionEvent event) {

    }


    /**
     * This method returns user to Overview page after submitting insert MYSQL.
     *
     * @param event Triggers with button click
     * @re
     */

    @FXML
    void onAddCustomerSave(ActionEvent event) throws IOException, SQLException {


        try {

            // Checking for empty values, USES LAMBDA #1

            if (addCustomerName.getText().equals("")) {
                throw new RootInputException(IOFormatted("Name", add));
            }

            if (addCustomerAddress.getText().equals("")) {
                throw new RootInputException(IOFormatted("Address", add));
            }

            if (addCustomerPostalCode.getText().equals("")) {
                throw new RootInputException(IOFormatted("Postal Code", add));
            }

            if (addCustomerPhone.getText().equals("")) {
                throw new RootInputException(IOFormatted("Phone", add));
            }

            if (stateComboBox.getValue() == null) {
                throw new RootInputException(IOFormatted("State", add));
            }

            String name = addCustomerName.getText();
            String address = addCustomerAddress.getText();
            String postal = addCustomerPostalCode.getText();
            String phone = addCustomerPhone.getText();


            FirstLevelDivisions divisionAdd = stateComboBox.getValue();
            int division = divisionAdd.getId();
            DBCustomers.insert(name, address, postal, phone, division);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer");
            alert.setHeaderText("Add Customer");
            alert.setContentText("Customer Added");
            Optional<ButtonType> result = alert.showAndWait();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (
                RootInputException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(ex.toString());
            alert.showAndWait();
        }
    }

    /**
     * This method sets the state combobox after reading country combobox
     *
     * @param event Triggers with combobox click
     */
    @FXML
    void onCountryCombobox(ActionEvent event) throws SQLException {

        Countries country = countryComboBox.getValue();
        stateComboBox.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisions(country.getId()));

    }

    @FXML
    void onStateComboBox(ActionEvent event) throws IOException {

    }

    /**
     * This method initializes the Add Appointment screen.
     *
     * @param url input
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryComboBox.setItems(DBCountries.getAllCountries());

    }

}

