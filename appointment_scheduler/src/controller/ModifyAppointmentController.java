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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import resources.RootInputException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * This class controls the interface of Modify Appointment page
 */
public class ModifyAppointmentController implements Initializable {

    Stage stage;

    Parent scene;

    @FXML
    private Button modifyAppointmentCancel;

    @FXML
    private Label modifyAppointmentIdLabel;

    @FXML
    private ComboBox<Contacts> modifyAppointmentContactCombo;

    @FXML
    private Label modifyAppointmentContactLabel;

    @FXML
    private ComboBox<Object> modifyAppointmentCustomerID;

    @FXML
    private Label modifyAppointmentCustomerIdLabel;

    @FXML
    private TextField modifyAppointmentDescription;

    @FXML
    private DatePicker modifyAppointmentEnd;

    @FXML
    private ChoiceBox<Object> modifyAppointmentEndHH;

    @FXML
    private Label modifyAppointmentEndLabel;

    @FXML
    private ChoiceBox<Object> modifyAppointmentEndMM;

    @FXML
    private ChoiceBox<Object> modifyAppointmentEndSS;

    @FXML
    private TextField modifyAppointmentLocation;

    @FXML
    private Button modifyAppointmentSave;

    @FXML
    private DatePicker modifyAppointmentStart;

    @FXML
    private ChoiceBox<Object> modifyAppointmentStartHH;

    @FXML
    private Label modifyAppointmentStartLabel;

    @FXML
    private ChoiceBox<Object> modifyAppointmentStartMM;

    @FXML
    private ChoiceBox<Object> modifyAppointmentStartSS;

    @FXML
    private TextField modifyAppointmentTitle;

    @FXML
    private TextField modifyAppointmentType;

    @FXML
    private ComboBox<Object> modifyAppointmentUserID;

    @FXML
    private Label modifyAppointmentUserIdLabel;

    @FXML
    void modifyAppointmentLocation(ActionEvent event) {

    }

    /**
     * This method returns user to Overview page.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onModifyAppointmentCancel(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onModifyAppointmentContactCombo(ActionEvent event) {

    }

    @FXML
    void onModifyAppointmentCustomerID(ActionEvent event) {

    }

    @FXML
    void onModifyAppointmentDescription(ActionEvent event) {

    }

    @FXML
    void onModifyAppointmentEnd(ActionEvent event) {

    }

    @FXML
    void onModifyAppointmentEndHH(MouseEvent event) {

    }

    @FXML
    void onModifyAppointmentEndMM(MouseEvent event) {

    }

    @FXML
    void onModifyAppointmentEndSS(MouseEvent event) {

    }

    /**
     * This method returns user to Overview page after submitting update MYSQL.
     *
     * @param event Triggers with button click
     */

    @FXML
    void onModifyAppointmentSave(ActionEvent event) throws SQLException, IOException {

        int appointmentId = Integer.parseInt(modifyAppointmentIdLabel.getText());
        String title = modifyAppointmentTitle.getText();
        String description = modifyAppointmentDescription.getText();
        String location = modifyAppointmentLocation.getText();
        String type = modifyAppointmentType.getText();
        ObservableList<Contacts> temp;
        LocalDateTime start;
        LocalDateTime end;
        int customerId;
        int contact;
        int userId;


        try {

            // Checking for empty values

            if (modifyAppointmentCustomerID.getValue() == null) {
                customerId = Integer.parseInt(modifyAppointmentCustomerIdLabel.getText());
            } else {

                customerId = (Integer) modifyAppointmentCustomerID.getValue();
            }

            if (modifyAppointmentUserID.getValue() == null) {
                userId = Integer.parseInt(modifyAppointmentUserIdLabel.getText());
            } else {

                userId = (Integer) modifyAppointmentUserID.getValue();
            }

            if (modifyAppointmentContactCombo.getValue() == null) {
                temp = DBContacts.getContactsIdByName(String.valueOf(modifyAppointmentContactLabel.getText()));
                String temp1 = String.valueOf(temp.get(0).getId());
                contact = Integer.parseInt(temp1);
            } else {

                Contacts contactUpdate = modifyAppointmentContactCombo.getValue();
                contact = contactUpdate.getId();

            }

            //  Logic check, IF ANY SINGLE DATE OR TIME BOX IS EMPTY, PAGE WILL REFER TO ORIGINAL DATE TIME

            if (modifyAppointmentStart.getValue() == null || modifyAppointmentStartHH.getValue() == null || modifyAppointmentStartMM.getValue() == null ||
                    modifyAppointmentStartSS.getValue() == null) {

                LocalDateTime startLocalMod = LocalDateTime.parse(modifyAppointmentStartLabel.getText());
                start = DBAppointments.convertToUtc(startLocalMod);
            } else {

                String startHH = (String) modifyAppointmentStartHH.getValue();
                String startMM = (String) modifyAppointmentStartMM.getValue();
                String startSS = (String) modifyAppointmentStartSS.getValue();
                LocalDate calenderStart = modifyAppointmentStart.getValue();
                String calenderResultStart = String.valueOf(calenderStart);
                String year = calenderResultStart.substring(0, 4);
                String month = calenderResultStart.substring(5, 7);
                String day = calenderResultStart.substring(8, 10);
                String formattedStart = year + "-" + month + "-" + day + "T" + startHH + ":"
                        + startMM + ":" + startSS;
                LocalDateTime startLocalMod = LocalDateTime.parse(formattedStart);
                start = DBAppointments.convertToUtc(startLocalMod);

            }

            //  Logic check, IF ANY SINGLE DATE OR TIME BOX IS EMPTY, PAGE WILL REFER TO ORIGINAL DATE TIME

            if (modifyAppointmentEnd.getValue() == null || modifyAppointmentEndHH.getValue() == null || modifyAppointmentEndMM.getValue() == null ||
                    modifyAppointmentEndSS.getValue() == null) {

                LocalDateTime endLocalMod = LocalDateTime.parse(modifyAppointmentEndLabel.getText());
                end = DBAppointments.convertToUtc(endLocalMod);
            } else {

                String endHH = (String) modifyAppointmentEndHH.getValue();
                String endMM = (String) modifyAppointmentEndMM.getValue();
                String endSS = (String) modifyAppointmentEndSS.getValue();
                LocalDate calenderEnd = modifyAppointmentEnd.getValue();
                String calenderResultEnd = String.valueOf(calenderEnd);
                String yearEnd = calenderResultEnd.substring(0, 4);
                String monthEnd = calenderResultEnd.substring(5, 7);
                String dayEnd = calenderResultEnd.substring(8, 10);
                String formattedEnd = yearEnd + "-" + monthEnd + "-" + dayEnd + "T" + endHH + ":"
                        + endMM + ":" + endSS;
                LocalDateTime endLocalMod = LocalDateTime.parse(formattedEnd);
                end = DBAppointments.convertToUtc(endLocalMod);
            }

            // Logic for office hours, start time before end time, and overlap times

            ObservableList<Appointments> officeLogic = DBAppointments.appointmentHoursLogic(start, end);
            ObservableList<Appointments> overlapLogic = DBAppointments.appointmentOverlapLogic(start, end, customerId);

            if (officeLogic.isEmpty()) {
                throw new RootInputException("Start and End must be between MON - FRI / 0800 - 2200 EST ");
            }

            if (start.isAfter(end)) {
                throw new RootInputException("Start and End must be in correct order ");
            }

            if (!overlapLogic.isEmpty()) {
                throw new RootInputException("Overlap time based on Customer ID");
            }

            DBAppointments.update(title, description, location, contact, type, start, end, customerId, userId, appointmentId);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment");
            alert.setHeaderText("Update Appointment");
            alert.setContentText("Appointment Updated");
            Optional<ButtonType> result = alert.showAndWait();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (RootInputException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText(ex.toString());
            alert.showAndWait();
        }
    }

    @FXML
    void onModifyAppointmentStart(ActionEvent event) {

    }

    @FXML
    void onModifyAppointmentStartHH(MouseEvent event) {

    }

    @FXML
    void onModifyAppointmentStartMM(MouseEvent event) {

    }

    @FXML
    void onModifyAppointmentStartSS(MouseEvent event) {

    }

    @FXML
    void onModifyAppointmentTitle(ActionEvent event) {

    }

    @FXML
    void onModifyAppointmentType(ActionEvent event) {

    }

    @FXML
    void onModifyAppointmentUserID(ActionEvent event) {

    }

    /**
     * This method is used for sending selected appointment from overview, allows data to be loaded into this page.
     *
     * @param appointment Instance of model.Appointments used to hold selected customer to modify.
     * @throws SQLException error checking
     */

    public void sendAppointment(Appointments appointment) throws SQLException {

        modifyAppointmentCustomerIdLabel.setText(Integer.toString(appointment.getCustomer()));
        modifyAppointmentUserIdLabel.setText(String.valueOf(appointment.getUser()));
        modifyAppointmentTitle.setText(String.valueOf(appointment.getTitle()));
        modifyAppointmentDescription.setText(String.valueOf(appointment.getDescription()));
        modifyAppointmentLocation.setText(String.valueOf(appointment.getLocation()));
        modifyAppointmentContactLabel.setText(String.valueOf(appointment.getContactText()));
        modifyAppointmentType.setText(String.valueOf(appointment.getType()));
        modifyAppointmentStartLabel.setText(String.valueOf(appointment.getStart()));
        modifyAppointmentEndLabel.setText(String.valueOf(appointment.getEnd()));
        modifyAppointmentIdLabel.setText(Integer.toString(appointment.getId()));

    }

    /**
     * This method initializes the Modify Appointment screen.
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
        modifyAppointmentStartHH.setItems(inputHours);
        modifyAppointmentEndHH.setItems(inputHours);

        // Setting minute and second comboboxes

        String minSec[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
                "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35",
                "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
                "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};

        ObservableList<Object> inputMinutes = FXCollections.observableArrayList(minSec);
        modifyAppointmentStartMM.setItems(inputMinutes);
        modifyAppointmentEndMM.setItems(inputMinutes);

        ObservableList<Object> inputSeconds = FXCollections.observableArrayList(minSec);
        modifyAppointmentStartSS.setItems(inputSeconds);
        modifyAppointmentEndSS.setItems(inputSeconds);

        // Setting comboboxes with contact name, customer id, and user id

        modifyAppointmentCustomerID.setItems(DBCustomers.selectCustomers());
        modifyAppointmentUserID.setItems(DBUsers.getAllUsersId());
        modifyAppointmentContactCombo.setItems(DBContacts.getAllContacts());


    }
}


