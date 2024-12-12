package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * This class controls the interface of Reports page
 */
public class ReportsController implements Initializable {

    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> reportsAppointmentId1;

    @FXML
    private TableColumn<?, ?> reportsAppointmentId2;

    @FXML
    private TableColumn<?, ?> reportsAppointmentId3;

    @FXML
    private TableColumn<?, ?> reportsAppointmentId4;

    @FXML
    private TableColumn<?, ?> reportsAppointmentTitle1;

    @FXML
    private TableColumn<?, ?> reportsAppointmentTitle2;

    @FXML
    private TableColumn<?, ?> reportsAppointmentTitle3;

    @FXML
    private TableColumn<?, ?> reportsAppointmentTitle4;

    @FXML
    private ComboBox<Contacts> reportsByContact;

    @FXML
    private ComboBox<String> reportsByMonth;

    @FXML
    private ComboBox<Contacts> reportsByPast;

    @FXML
    private ComboBox<Object> reportsByType;

    @FXML
    private TableColumn<?, ?> reportsContactID1;

    @FXML
    private TableColumn<?, ?> reportsContactID2;

    @FXML
    private TableColumn<?, ?> reportsContactID3;

    @FXML
    private TableColumn<?, ?> reportsContactID4;

    @FXML
    private TableColumn<?, ?> reportsCustomerId1;

    @FXML
    private TableColumn<?, ?> reportsCustomerId2;

    @FXML
    private TableColumn<?, ?> reportsCustomerId3;

    @FXML
    private TableColumn<?, ?> reportsCustomerId4;

    @FXML
    private TableColumn<?, ?> reportsDescription1;

    @FXML
    private TableColumn<?, ?> reportsDescription2;

    @FXML
    private TableColumn<?, ?> reportsDescription3;

    @FXML
    private TableColumn<?, ?> reportsDescription4;

    @FXML
    private TableColumn<?, ?> reportsEnd1;

    @FXML
    private TableColumn<?, ?> reportsEnd2;

    @FXML
    private TableColumn<?, ?> reportsEnd3;

    @FXML
    private TableColumn<?, ?> reportsEnd4;

    @FXML
    private TableColumn<?, ?> reportsLocation1;

    @FXML
    private TableColumn<?, ?> reportsLocation2;

    @FXML
    private TableColumn<?, ?> reportsLocation3;

    @FXML
    private TableColumn<?, ?> reportsLocation4;

    @FXML
    private Button reportsReturnToMain;

    @FXML
    private TableColumn<?, ?> reportsStart1;

    @FXML
    private TableColumn<?, ?> reportsStart2;

    @FXML
    private TableColumn<?, ?> reportsStart3;

    @FXML
    private TableColumn<?, ?> reportsStart4;

    @FXML
    private TableView<Appointments> reportsTable1;

    @FXML
    private TableView<Appointments> reportsTable2;

    @FXML
    private TableView<Appointments> reportsTable3;

    @FXML
    private TableView<Appointments> reportsTable4;

    @FXML
    private TableColumn<?, ?> reportsType1;

    @FXML
    private TableColumn<?, ?> reportsType2;

    @FXML
    private TableColumn<?, ?> reportsType3;

    @FXML
    private TableColumn<?, ?> reportsType4;

    @FXML
    private TableColumn<?, ?> reportsUserId1;

    @FXML
    private TableColumn<?, ?> reportsUserId2;

    @FXML
    private TableColumn<?, ?> reportsUserId3;

    @FXML
    private TableColumn<?, ?> reportsUserId4;

    /**
     * This method sets table with a MYSQL query of sorting BY contactName, as required.
     *
     * @param event Triggers with toggle click
     */

    @FXML
    void onReportsByContact(ActionEvent event) {

        Contacts appointmentByContact = reportsByContact.getValue();
        String contactName = String.valueOf(appointmentByContact);
        reportsTable3.setItems(DBAppointments.getAllAppointmentsByContact(contactName));

    }

    /**
     * This method sets table with a MYSQL query of sorting BY Month, as required.
     *
     * @param event Triggers with toggle click
     */

    @FXML
    String onReportsByMonth(ActionEvent event) {

        String input = reportsByMonth.getValue();
        int result;

        switch (input) {
            case "January":
                result = 1;
                break;
            case "February":
                result = 2;
                break;
            case "March":
                result = 3;
                break;
            case "April":
                result = 4;
                break;
            case "May":
                result = 5;
                break;
            case "June":
                result = 6;
                break;
            case "July":
                result = 7;
                break;
            case "August":
                result = 8;
                break;
            case "September":
                result = 9;
                break;
            case "October":
                result = 10;
                break;
            case "November":
                result = 11;
                break;
            case "December":
                result = 12;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + input);
        }

        reportsTable2.setItems(DBAppointments.getAllAppointmentsByMonth(result));
        return null;
    }

    /**
     * This method sets table with a MYSQL query of sorting BY contactName.  Takes now() method and requires less than.
     *
     * @param event Triggers with toggle click
     */

    @FXML
    void onReportsByPast(ActionEvent event) {

        Contacts appointmentByContactPast = reportsByPast.getValue();
        String contactNamePast = String.valueOf(appointmentByContactPast);
        reportsTable4.setItems(DBAppointments.getAllAppointmentsByContactPast(contactNamePast));

    }

    /**
     * This method sets table with a MYSQL query of select by type.
     *
     * @param event Triggers with toggle click
     */

    @FXML
    void onReportsByType(ActionEvent event) {

        Object appointment = reportsByType.getValue();
        reportsTable1.setItems(DBAppointments.getAllAppointmentsByType(String.valueOf(appointment)));

    }

    /**
     * This method sends user from current Reports page to Overview page.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onReportsReturnToMain(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method initializes the Overview screen.
     *
     * @param url input
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //  Sets comboboxes with values to select for tables

        reportsByMonth.setItems(months);
        reportsByType.setItems(DBAppointments.allAppointmentType());
        reportsByContact.setItems(DBContacts.getAllContacts());
        reportsByPast.setItems(DBContacts.getAllContacts());

        //  Sets cell formatting for table 1

        reportsAppointmentId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        reportsAppointmentTitle1.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportsDescription1.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportsLocation1.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportsType1.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportsStart1.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportsEnd1.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportsCustomerId1.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reportsUserId1.setCellValueFactory(new PropertyValueFactory<>("user"));
        reportsContactID1.setCellValueFactory(new PropertyValueFactory<>("contactText"));

        //  Sets cell formatting for table 2

        reportsAppointmentId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        reportsAppointmentTitle2.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportsDescription2.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportsLocation2.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportsType2.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportsStart2.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportsEnd2.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportsCustomerId2.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reportsUserId2.setCellValueFactory(new PropertyValueFactory<>("user"));
        reportsContactID2.setCellValueFactory(new PropertyValueFactory<>("contactText"));

        //  Sets cell formatting for table 3

        reportsAppointmentId3.setCellValueFactory(new PropertyValueFactory<>("id"));
        reportsAppointmentTitle3.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportsDescription3.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportsLocation3.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportsType3.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportsStart3.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportsEnd3.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportsCustomerId3.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reportsUserId3.setCellValueFactory(new PropertyValueFactory<>("user"));
        reportsContactID3.setCellValueFactory(new PropertyValueFactory<>("contactText"));

        //  Sets cell formatting for table 4

        reportsAppointmentId4.setCellValueFactory(new PropertyValueFactory<>("id"));
        reportsAppointmentTitle4.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportsDescription4.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportsLocation4.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportsType4.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportsStart4.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportsEnd4.setCellValueFactory(new PropertyValueFactory<>("end"));
        reportsCustomerId4.setCellValueFactory(new PropertyValueFactory<>("customer"));
        reportsUserId4.setCellValueFactory(new PropertyValueFactory<>("user"));
        reportsContactID4.setCellValueFactory(new PropertyValueFactory<>("contactText"));

    }
}



