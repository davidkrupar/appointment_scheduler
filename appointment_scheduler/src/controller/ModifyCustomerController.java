package controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the interface of Modify Customer page
 */

public class ModifyCustomerController implements Initializable {
    Stage stage;

    Parent scene;

    @FXML
    private TextField modifyCustomerAddress;

    @FXML
    private Button modifyCustomerCancel;

    @FXML
    private Label modifyCustomerCountry;

    @FXML
    private ComboBox<Countries> modifyCustomerCountryBox;

    @FXML
    private TextField modifyCustomerID;

    @FXML
    private TextField modifyCustomerName;

    @FXML
    private TextField modifyCustomerPhone;

    @FXML
    private TextField modifyCustomerPostal;

    @FXML
    private Button modifyCustomerSave;

    @FXML
    private Label modifyCustomerState;

    @FXML
    private ComboBox<FirstLevelDivisions> modifyCustomerStateBox;
    private Object FirstLevelDivisions;

    @FXML
    void onModifyCustomerAddress(ActionEvent event) {

    }

    /**
     * This method returns user to Overview page
     *
     * @param event Triggers with button click
     */

    @FXML
    void onModifyCustomerCancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method sets the state combobox after reading country combobox
     *
     * @param event Triggers with combobox click
     */

    @FXML
    void onModifyCustomerCountryBox(ActionEvent event) throws SQLException {

        Countries country = modifyCustomerCountryBox.getValue();
        modifyCustomerStateBox.setItems(DBFirstLevelDivisions.getAllFirstLevelDivisions(country.getId()));

    }

    @FXML
    void onModifyCustomerID(ActionEvent event) {

    }

    @FXML
    void onModifyCustomerName(ActionEvent event) {

    }

    @FXML
    void onModifyCustomerPhone(ActionEvent event) {

    }

    @FXML
    void onModifyCustomerPostal(ActionEvent event) {

    }


    /**
     * This method returns user to Overview page after submitting update MYSQL.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onModifyCustomerSave(ActionEvent event) throws IOException, SQLException {

        ObservableList<FirstLevelDivisions> temp = null;
        if (modifyCustomerStateBox.getValue() == null) {

            temp = DBFirstLevelDivisions.getFirstLevelDivisionId(modifyCustomerState.getText());
            String temp1 = String.valueOf(temp.get(0).getId());
            int division = Integer.parseInt(temp1);
            String name = modifyCustomerName.getText();
            String address = modifyCustomerAddress.getText();
            String postal = modifyCustomerPostal.getText();
            String phone = modifyCustomerPhone.getText();
            int id = Integer.parseInt(modifyCustomerID.getText());
            DBCustomers.update(name, address, postal, phone, division, id);
        } else {

            FirstLevelDivisions divisionUpdateBackup = modifyCustomerStateBox.getValue();
            int division = divisionUpdateBackup.getId();
            String name = modifyCustomerName.getText();
            String address = modifyCustomerAddress.getText();
            String postal = modifyCustomerPostal.getText();
            String phone = modifyCustomerPhone.getText();
            int id = Integer.parseInt(modifyCustomerID.getText());
            DBCustomers.update(name, address, postal, phone, division, id);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer");
        alert.setHeaderText("Update Customer");
        alert.setContentText("Customer Updated");
        Optional<ButtonType> result = alert.showAndWait();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onModifyCustomerStateBox(ActionEvent event) {

    }

    /**
     * This method is used for sending selected customer from overview, allows data to be loaded into this page.
     *
     * @param customer Instance of model.Customers used to hold selected customer to modify.
     * @throws SQLException error checking
     */

    public void sendCustomer(Customers customer) throws SQLException {

        ObservableList<Countries> country = DBCountries.select(customer.getCountry());
        ObservableList<FirstLevelDivisions> division = DBFirstLevelDivisions.select(customer.getDivision());
        String divisionName = String.valueOf(division);
        String countryName = String.valueOf(country);

        modifyCustomerID.setText(Integer.toString(customer.getId()));
        modifyCustomerName.setText(String.valueOf(customer.getName()));
        modifyCustomerAddress.setText(String.valueOf(customer.getAddress()));
        modifyCustomerCountry.setText(String.valueOf(countryName.substring(1, countryName.length() - 1)));
        modifyCustomerState.setText(String.valueOf(divisionName.substring(1, divisionName.length() - 1)));
        modifyCustomerPostal.setText(String.valueOf(customer.getPostal()));
        modifyCustomerPhone.setText(String.valueOf(customer.getPhone()));

    }

    /**
     * This method initializes the Modify Customer screen.
     *
     * @param url input
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modifyCustomerCountryBox.setItems(DBCountries.getAllCountries());

    }
}



