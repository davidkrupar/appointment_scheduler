package controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
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
import model.Appointments;
import model.Contacts;
import model.Customers;
import resources.RootInputException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the interface of Add Appointment page
 */

public class AddAppointmentController implements Initializable {


    Stage stage;

    Parent scene;


    @FXML
    private Button addAppointmentCancel;
    @FXML
    private TextField addAppointmentType;

    @FXML
    private TextField addAppointmentLocation;

    @FXML
    private ComboBox<Contacts> addAppointmentContact;

    @FXML
    private ComboBox<Object> addAppointmentUserId;

    @FXML
    private ComboBox<Object> addAppointmentCustomerId;

    @FXML
    private TextField addAppointmentDescription;

    @FXML
    private DatePicker addAppointmentEnd;

    @FXML
    private ChoiceBox<Object> addAppointmentEndHH;

    @FXML
    private ChoiceBox<Object> addAppointmentEndMM;

    @FXML
    private ChoiceBox<Object> addAppointmentEndSS;


    @FXML
    private Button addAppointmentSave;

    @FXML
    private DatePicker addAppointmentStart;

    @FXML
    private ChoiceBox<Object> addAppointmentStartHH;

    @FXML
    private ChoiceBox<Object> addAppointmentStartMM;

    @FXML
    private ChoiceBox<Object> addAppointmentStartSS;

    @FXML
    private TextField addAppointmentTitle;


    @FXML
    private TextField addAppointmentUserID;

    @FXML
    private TextField addPartId1;

    @FXML
    void hello(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentCustomerId(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentUserId(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentLocation(ActionEvent event) {

    }

    /**
     * This method returns user to Overview page.
     *
     * @param event Triggers with button click
     */
    @FXML
    void onAddAppointmentCancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onAddAppointmentContact(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentDescription(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentEnd(ActionEvent event) {

    }

    /**
     * This method returns user to Overview page after submitting insert MYSQL.
     *
     * @param event Triggers with button click
     */
    @FXML
    void onAddAppointmentSave(ActionEvent event) throws SQLException, IOException {


        try {

            // Checking for empty values

            if (addAppointmentCustomerId.getValue() == null) {
                throw new RootInputException("Please select Customer ID");
            }

            if (addAppointmentUserId.getValue() == null) {
                throw new RootInputException("Please select User ID");
            }

            if (addAppointmentTitle.getText().equals("")) {
                throw new RootInputException("Please enter value for Title");
            }

            if (addAppointmentDescription.getText().equals("")) {
                throw new RootInputException("Please enter value for Description");
            }
            if (addAppointmentLocation.getText().equals("")) {
                throw new RootInputException("Please enter value for Location");
            }
            if (addAppointmentContact.getValue() == null) {
                throw new RootInputException("Please select Contact");
            }
            if (addAppointmentType.getText().equals("")) {
                throw new RootInputException("Please enter value for Type");
            }
            if (addAppointmentStart.getValue() == null) {
                throw new RootInputException("Please select Start Date");
            }
            if (addAppointmentStartHH.getValue() == null) {
                throw new RootInputException("Please select Start Hours");
            }
            if (addAppointmentStartMM.getValue() == null) {
                throw new RootInputException("Please select Start Minutes");
            }
            if (addAppointmentStartSS.getValue() == null) {
                throw new RootInputException("Please select Start Seconds");
            }
            if (addAppointmentEnd.getValue() == null) {
                throw new RootInputException("Please select End Date");
            }
            if (addAppointmentEndHH.getValue() == null) {
                throw new RootInputException("Please select End Hours");
            }
            if (addAppointmentEndMM.getValue() == null) {
                throw new RootInputException("Please select End Minutes");
            }
            if (addAppointmentEndSS.getValue() == null) {
                throw new RootInputException("Please select End Seconds");
            }


            Contacts contactAdd = addAppointmentContact.getValue();
            int customerId = (Integer) addAppointmentCustomerId.getValue();
            int userId = (Integer) addAppointmentUserId.getValue();


            String startHH = (String) addAppointmentStartHH.getValue();
            String startMM = (String) addAppointmentStartMM.getValue();
            String startSS = (String) addAppointmentStartSS.getValue();
            LocalDate calenderStart = addAppointmentStart.getValue();
            String calenderResultStart = String.valueOf(calenderStart);
            String year = calenderResultStart.substring(0, 4);
            String month = calenderResultStart.substring(5, 7);
            String day = calenderResultStart.substring(8, 10);
            String formattedStart = year + "-" + month + "-" + day + "T" + startHH + ":"
                    + startMM + ":" + startSS;


            String endHH = (String) addAppointmentEndHH.getValue();
            String endMM = (String) addAppointmentEndMM.getValue();
            String endSS = (String) addAppointmentEndSS.getValue();
            LocalDate calenderEnd = addAppointmentEnd.getValue();
            String calenderResultEnd = String.valueOf(calenderEnd);
            String yearEnd = calenderResultEnd.substring(0, 4);
            String monthEnd = calenderResultEnd.substring(5, 7);
            String dayEnd = calenderResultEnd.substring(8, 10);
            String formattedEnd = yearEnd + "-" + monthEnd + "-" + dayEnd + "T" + endHH + ":"
                    + endMM + ":" + endSS;


            String title = addAppointmentTitle.getText();
            String description = addAppointmentDescription.getText();
            String location = addAppointmentLocation.getText();
            int contact = contactAdd.getId();
            String type = addAppointmentType.getText();
            LocalDateTime startLocal = LocalDateTime.parse(formattedStart);
            LocalDateTime start = DBAppointments.convertToUtc(startLocal);
            System.out.println(startLocal);
            System.out.println(start);
            LocalDateTime endLocal = LocalDateTime.parse(formattedEnd);
            LocalDateTime end = DBAppointments.convertToUtc(endLocal);


            // Logic for office hours, start time before end time, and overlap times

            ObservableList<Appointments> officeLogic = DBAppointments.appointmentHoursLogic(start, end);
            ObservableList<Appointments> overlapLogic = DBAppointments.appointmentOverlapLogic(start, end, customerId);
            //if (officeLogic.isEmpty()) {
               // throw new RootInputException("Start and End must be between MON - FRI / 0800 - 2200 EST ");
          //  }


            if (start.isAfter(end)) {
                throw new RootInputException("Start and End must be in correct order ");
            }

            if (!overlapLogic.isEmpty()) {
                throw new RootInputException("Overlap time based on Customer ID");
            }


            DBAppointments.insert(title, description, location, contact, type, start, end, customerId, userId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment");
            alert.setHeaderText("Add Appointment");
            alert.setContentText("Appointment Added");
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

    @FXML
    void onAddAppointmentStart(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentTitle(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentType(ActionEvent event) {

    }

    @FXML
    void onAddAppointmentUserID(ActionEvent event) {

    }

    /**
     * This method initializes the Add Appointment screen.
     *
     * @param url input
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Setting hour comboboxes

        String hours[] = {"00", "01", "02", "03", "04", "05", "06", "07",
                "08", "09", "10", "11", "12", "13", "14", "15", "16",
                "17", "18", "19", "20", "21", "22", "23"};

        ObservableList<Object> inputHours = FXCollections.observableArrayList(hours);
        addAppointmentStartHH.setItems(inputHours);
        addAppointmentEndHH.setItems(inputHours);

        // Setting minute and second comboboxes

        String minSec[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
                "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35",
                "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
                "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        ObservableList<Object> inputMinutes = FXCollections.observableArrayList(minSec);
        addAppointmentStartMM.setItems(inputMinutes);
        addAppointmentEndMM.setItems(inputMinutes);


        ObservableList<Object> inputSeconds = FXCollections.observableArrayList(minSec);
        addAppointmentStartSS.setItems(inputSeconds);
        addAppointmentEndSS.setItems(inputSeconds);

        // Setting comboboxes with contact name, customer id, and user id

        addAppointmentContact.setItems(DBContacts.getAllContacts());
        addAppointmentCustomerId.setItems(DBCustomers.selectCustomers());
        addAppointmentUserId.setItems(DBUsers.getAllUsersId());

    }
}


