

package controller;

import DBAccess.DBAppointments;
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
import model.Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


import static java.util.Locale.ENGLISH;
import static java.util.Locale.FRENCH;

interface StringFunction {
    String run(String str);
}

/**
 * This class controls the interface of the Login page
 */
public class LoginController implements Initializable {
    public static String user;
    StringFunction txtFormat = (t) -> t + "\n----------------------\n";
    Stage stage;
    Parent scene;

    /**
     * This method uses LAMBDA #2, used to build string formatted for login txt file tracking
     *
     * @param str    input for login txt file based on occurrence
     * @param format Lambda #2
     * @return Returns result which is formatted string used to write to login txt file
     */
    public static String printFormatted(String str, StringFunction format) {
        String result = format.run(str);
        return result;
    }


    @FXML
    private Button loginButton;

    @FXML
    private Label loginUserZoneId;

    @FXML
    private TextField passwordLogin;

    @FXML
    private TextField usernameLogin;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label passwordLabel;


    @FXML
    private Label loginUserZoneLabel;
    private Object Timestamp;

    @FXML
    void onLoginButton(ActionEvent event) throws IOException, SQLException {

        ResourceBundle rb = ResourceBundle.getBundle("resources/Nat_fr");
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        String user = usernameLogin.getText();
        String pass = passwordLogin.getText();

        ObservableList<Users> login = DBUsers.select(user, pass);

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\Desktop\\temp portfolio git\\SBConnectionAppV1-master\\login_activity.txt", true));


        if (login.isEmpty() == false) {

            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(rb.getString("Login"));
                alert.setHeaderText(rb.getString("Login"));
                alert.setContentText(rb.getString("IsMatch"));
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("LOGIN");
                alert.setHeaderText("LOGIN");
                alert.setContentText("NAME PASSWORD MATCH");
                Optional<ButtonType> result = alert.showAndWait();

            }

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Overview.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            writer.write(printFormatted(timeStamp + " Login attempt Successful ", txtFormat));
            writer.close();

            LocalDateTime startLocal = LocalDateTime.now();
            LocalDateTime start = DBAppointments.convertToUtc(startLocal);
            Duration gap = Duration.ofMinutes(15);
            LocalDateTime test = start.plus(gap);

            ObservableList<Appointments> loginRef = DBAppointments.getAllAppointmentsByLogin(user, start, test);
            if (loginRef.size() > 0) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Meeting Within 15 Minutes");
                alert2.setHeaderText("URGENT");
                alert2.setContentText("Appointment ID : " + loginRef.get(0).getId() + "\nScheduled for : " + loginRef.get(0).getStart());
                Optional<ButtonType> result2 = alert2.showAndWait();

            } else {
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("Appointments");
                alert3.setHeaderText("Time to relax");
                alert3.setContentText("No Appointments within 15 minutes of \n\n" + startLocal);
                Optional<ButtonType> result3 = alert3.showAndWait();

            }

        } else {

            if (Locale.getDefault().getLanguage().equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(rb.getString("Login"));
                alert.setHeaderText(rb.getString("Login"));
                alert.setContentText(rb.getString("NotMatch"));
                Optional<ButtonType> result = alert.showAndWait();

                writer.write(printFormatted(timeStamp + " Login attempt Failed ", txtFormat));
                writer.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("LOGIN");
                alert.setHeaderText("LOGIN");
                alert.setContentText("NAME PASSWORD NOT A MATCH");
                Optional<ButtonType> result = alert.showAndWait();

                writer.write(printFormatted(timeStamp + " Login attempt Failed ", txtFormat));
                writer.close();

            }
        }
    }


    @FXML
    void onPasswordLogin(ActionEvent event) {

    }

    @FXML
    void onUsernameLogin(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        Locale.setDefault(FRENCH);
        ResourceBundle rb = ResourceBundle.getBundle("resources/Nat_fr");

        if (Locale.getDefault().getLanguage().equals("fr")) {
            userNameLabel.setText(rb.getString("UserName"));
            passwordLabel.setText(rb.getString("Password"));
            loginButton.setText(rb.getString("Connect"));
            loginUserZoneLabel.setText(rb.getString("ZoneID"));

        } else {
            userNameLabel.setText("UserName");
            passwordLabel.setText("Password");
            loginButton.setText("Connect");
            loginUserZoneLabel.setText("Zone ID -->");
        }

        ZoneId userLogin = ZoneId.systemDefault();
        loginUserZoneId.setText(String.valueOf(userLogin));

    }
}


