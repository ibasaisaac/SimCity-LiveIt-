/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newpackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class hamburgerController implements Initializable {

    @FXML
    private Button loginbutton;
    @FXML
    private Button profilebutton;
    @FXML
    private Button logoutbutton;
    @FXML
    private Button exitbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (User.LOGINTOGGLE > 0) {
            User u = new User();
            u.getUser(User.LOGINTOGGLE);
            loginbutton.setText(u.getname());
            profilebutton.setVisible(true);
            logoutbutton.setVisible(true);
        }
    }

    @FXML
    private void loginbuttonIsPressed(MouseEvent event) throws IOException {
        if (User.LOGINTOGGLE == 0) {
            Stage loginwindow = new Stage();
            loginwindow.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            loginwindow.setScene(scene);
            loginwindow.show();
        }
    }

    @FXML
    private void profilebuttonIsPressed(MouseEvent event) throws IOException {
        if (User.LOGINTOGGLE == 0) {
            Stage loginwindow = new Stage();
            loginwindow.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            loginwindow.setScene(scene);
            loginwindow.show();
        } else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("profile.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void logoutbuttonIsPressed(MouseEvent event) {
        User.LOGINTOGGLE = 0;
        loginbutton.setText("Login/Signup");
        profilebutton.setVisible(false);
        logoutbutton.setVisible(false);
    }

    @FXML
    private void ExitOnClick(ActionEvent event) {
        javafx.application.Platform.exit();
    }

}
