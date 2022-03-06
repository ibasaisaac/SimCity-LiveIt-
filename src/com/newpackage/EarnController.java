package com.newpackage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EarnController {

    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private Button hotelButton;
    @FXML
    private Button rentalbutton;
    @FXML
    private Button flatbutton;
    @FXML
    private Button jobbutton;
    @FXML
    private ImageView backbutton;
    @FXML
    private Group logo;
    @FXML
    private Label covidbutton;
    @FXML
    private Label earnbutton;

    int service = 0;

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void hamburgerIsPressed(MouseEvent event) {
        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("hamburger.fxml"));
            hamburgerdrawer.setSidePane(vbox);
        } catch (IOException ex) {
            Logger.getLogger(home1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (hamburgerdrawer.isOpened()) {
            hamburgerdrawer.close();
            covidbutton.setVisible(true);
            earnbutton.setVisible(true);
        } else {
            hamburgerdrawer.open();
            covidbutton.setVisible(false);
            earnbutton.setVisible(false);
        }
    }

    @FXML
    public boolean hotelIsClicked(ActionEvent event) throws IOException {
        service = 1;
        if (User.LOGINTOGGLE == 0) {
            Stage loginwindow = new Stage();
            loginwindow.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            loginwindow.setScene(scene);
            loginwindow.show();
//            loginController lc = new loginController();
//            if (lc.SigninOnClick(event)) {
//                System.out.println("k");
//                User u = new User();
//                u.getUser(User.LOGINTOGGLE);
//                Stage stage = (Stage) hotelButton.getScene().getWindow();
//                Scene newscene = new Scene(FXMLLoader.load(getClass().getResource("detailsAccom.fxml")));
//                stage.setScene(newscene);
//                stage.show();
//            }
            
        } else if (User.LOGINTOGGLE > 0) {
            User u = new User();
            u.getUser(User.LOGINTOGGLE);
            Stage stage = (Stage) hotelButton.getScene().getWindow();
            Scene newscene = new Scene(FXMLLoader.load(getClass().getResource("detailsAccom.fxml")));
            stage.setScene(newscene);
            stage.show();
        }
    
    return true;}

    @FXML
    public void check() throws IOException {
        try{
        System.out.println("k");
//        EarnController ec = new EarnController();
//        if (service==1) {
            Stage stage = (Stage) hotelButton.getScene().getWindow();
            Scene newscene = new Scene(FXMLLoader.load(getClass().getResource("detailsAccom.fxml")));
            stage.setScene(newscene);
            stage.show();
        }
        catch(IOException ex) {
            ex.printStackTrace();
}
    }
    @FXML
    private void rentalIsClicked(ActionEvent event) throws IOException {
        if (User.LOGINTOGGLE == 0) {
            Stage loginwindow = new Stage();
            loginwindow.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            loginwindow.setScene(scene);
            loginwindow.show();
        }
    }

    @FXML
    private void flatIsClicked(ActionEvent event) throws IOException {
        if (User.LOGINTOGGLE == 0) {
            Stage loginwindow = new Stage();
            loginwindow.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            loginwindow.setScene(scene);
            loginwindow.show();
        }
    }

    @FXML
    private void jobIsClicked(ActionEvent event) throws IOException {
        if (User.LOGINTOGGLE == 0) {
            Stage loginwindow = new Stage();
            loginwindow.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            loginwindow.setScene(scene);
            loginwindow.show();
        }
    }

    @FXML
    private void backIsPressed(MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) backbutton.getScene().getWindow();
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logoIsPressed(MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) logo.getScene().getWindow();
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void covidIsPressed(MouseEvent event) throws IOException {

    }
}
