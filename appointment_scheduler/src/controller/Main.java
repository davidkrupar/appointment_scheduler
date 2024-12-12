package controller;

import DBAccess.DBAppointments;
import database.DBConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointments;


import java.io.IOException;
import java.sql.SQLException;
import java.time.*;

/**
 * This class creates an app that displays messages
 */
public class Main extends Application {

    /**
     * This method loads our first fxml file.  We can adjust our first page by changing this filepath
     *
     * @param stage The first stage
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 625);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This is the main method.  This is the first method that gets called when running program.
     *
     * @param args argument for main method
     * @throws SQLException error checking
     */
    public static void main(String[] args) throws SQLException {

        DBConnection.openConnection();
        launch();


    }
}

