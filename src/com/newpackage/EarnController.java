package com.newpackage;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class EarnController {

    @FXML
    private JFXHamburger hamburgerbutton;

    @FXML
    private JFXDrawer hamburgerdrawer;

    @FXML
    private ImageView homebg;

    @FXML
    private Button hotelButton;

    @FXML
    void hamburgerIsPressed(MouseEvent event) {

    }

    @FXML
    void hotelButtonClicked(MouseEvent event) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage=new Stage(); //creates new window
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
