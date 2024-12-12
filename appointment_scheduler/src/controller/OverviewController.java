package controller;

import DBAccess.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * This class controls the interface of Overview page
 */
public class OverviewController implements Initializable {

    Stage stage;
    Parent scene;

    private final ObservableList<Customers> displayCustomers = FXCollections.observableArrayList();

    @FXML
    private Button addAppointment;

    @FXML
    private Button addCustomer;

    @FXML
    private TableColumn<?, ?> allAppointmentId;

    @FXML
    private TableColumn<?, ?> allAppointmentTitle;

    @FXML
    private TableColumn<String, ?> allContactID;

    @FXML
    private TableColumn<?, ?> allCustomePostalCode;

    @FXML
    private TableColumn<?, ?> allCustomerAddress;

    @FXML
    private TableColumn<?, ?> allCustomerDivisionID;

    @FXML
    private TableColumn<?, ?> allCustomerID;

    @FXML
    private TableColumn<?, ?> allCustomerName;

    @FXML
    private TableColumn<?, ?> allCustomerPhone;

    @FXML
    private TableColumn<?, ?> allCustomerAppointmentID;

    @FXML
    private TableColumn<?, ?> allDescription;

    @FXML
    private TableColumn<?, ?> allEnd;

    @FXML
    private TableColumn<?, ?> allLocation;

    @FXML
    private TableColumn<?, ?> allStart;

    @FXML
    private TableColumn<?, ?> allType;

    @FXML
    private TableColumn<?, ?> allCustomerIdAppointment;

    @FXML
    private TableColumn<?, ?> allUserIdAppointment;

    @FXML
    private TableView<Appointments> appointmentTable;

    @FXML
    private RadioButton byCustomer;

    @FXML
    private RadioButton byMonth;

    @FXML
    private RadioButton byWeek;

    @FXML
    private TableView<Customers> customerTable;

    @FXML
    private Button deleteAppointment;

    @FXML
    private Button deleteCustomer;

    @FXML
    private Button reportsButton;

    @FXML
    private Button updateAppointment;

    @FXML
    private Button updateCustomer;

    @FXML
    private RadioButton viewAll;

    /**
     * This method sends user to Add Appointment page.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onAddAppointment(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method sends user to Add Customer page.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onAddCustomer(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method sets the appointment table with a MYSQL query of sorting BY MONTH, as required.
     *
     * @param event Triggers with toggle click
     */

    @FXML
    void onByMonth(ActionEvent event) {

        appointmentTable.setItems(DBAppointments.getAllAppointmentsOrderMonth());

    }

    /**
     * This method sets the appointment table with a MYSQL query of sorting BY WEEK, as required
     *
     * @param event Triggers with toggle click
     */

    @FXML
    void onByWeek(ActionEvent event) {

        appointmentTable.setItems(DBAppointments.getAllAppointmentsOrderWeek());

    }

    /**
     * This method handles event for DELETE of selected Appointment from appointment tableview.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onDeleteAppointment(ActionEvent event) throws SQLException {

        Appointments deleteAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if (deleteAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment");
            alert.setHeaderText("Delete");
            alert.setContentText("Please select appointment to delete");
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Appointment");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this appointment?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment");
                alert.setHeaderText("Delete");
                alert.setContentText("Appointment " + "ID: " + deleteAppointment.getId() + " Type: " + deleteAppointment.getType() + " Deleted");
                result = alert.showAndWait();
                DBAppointments.delete(deleteAppointment.getId());
                appointmentTable.setItems(DBAppointments.getAllAppointments());
            } else {

                appointmentTable.setItems(DBAppointments.getAllAppointments());
            }
        }
    }


    /**
     * This method handles event for DELETE of selected Customer from customer tableview.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onDeleteCustomer(ActionEvent event) throws SQLException {

        Customers deleteCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (deleteCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer");
            alert.setHeaderText("Delete");
            alert.setContentText("Please select customer to delete");
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Customer");
            alert.setHeaderText("Delete");
            alert.setContentText("Do you want to delete this customer?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer");
                alert.setHeaderText("Delete");
                alert.setContentText("Customer " + deleteCustomer.getName() + " deleted.");
                result = alert.showAndWait();
                DBCustomers.delete(deleteCustomer.getId());
                customerTable.setItems(DBCustomers.getAllCustomers());
            } else {

                customerTable.setItems(DBCustomers.getAllCustomers());

            }
        }
    }

    /**
     * This method sends user to Reports page.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onReportsButton(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method sends user to Update Appointment page.  Contains logic for holding and sending selected Appointment.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onUpdateAppointment(ActionEvent event) throws IOException, SQLException {

        Appointments updateAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if (updateAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment");
            alert.setHeaderText("Update");
            alert.setContentText("Please select Appointment to Update");
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyAppointment.fxml"));
            loader.load();
            ModifyAppointmentController AppointmentController = loader.getController();
            AppointmentController.sendAppointment(appointmentTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    /**
     * This method sends user to Update Customer page.  Contains logic for holding and sending selected Customer.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onUpdateCustomer(ActionEvent event) throws IOException, SQLException {

        Customers updateCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (updateCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer");
            alert.setHeaderText("Update");
            alert.setContentText("Please select customer to Update");
            Optional<ButtonType> result = alert.showAndWait();
        } else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
            loader.load();
            ModifyCustomerController CustomerController = loader.getController();
            CustomerController.sendCustomer(customerTable.getSelectionModel().getSelectedItem());
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }
    }

    /**
     * This method sets the appointment table with a MYSQL query of selecting all
     *
     * @param event Triggers with toggle click
     */

    @FXML
    void onViewAll(ActionEvent event) {

        appointmentTable.setItems(DBAppointments.getAllAppointments());

    }

    /**
     * This method initializes the Overview screen.
     *
     * @param url input
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //  Sets Customer table cell values, then sets table with select all MYSQL

        allCustomerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        allCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        allCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        allCustomePostalCode.setCellValueFactory(new PropertyValueFactory<>("postal"));
        allCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        allCustomerDivisionID.setCellValueFactory(new PropertyValueFactory<>("division"));

        customerTable.setItems(DBCustomers.getAllCustomers());


        //  Sets Appointment table cell values, then sets table with select all MYSQL

        allAppointmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        allAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        allDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        allLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        allType.setCellValueFactory(new PropertyValueFactory<>("type"));
        allStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        allEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        allCustomerIdAppointment.setCellValueFactory(new PropertyValueFactory<>("customer"));
        allUserIdAppointment.setCellValueFactory(new PropertyValueFactory<>("user"));
        allContactID.setCellValueFactory(new PropertyValueFactory<>("contactText"));

        appointmentTable.setItems(DBAppointments.getAllAppointments());

    }
}


