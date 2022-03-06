package com.newpackage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class home1Controller implements Initializable {

    @FXML
    private Group logo;
    @FXML
    private ImageView homebg;
    int count = 0;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private Label accomodationbutton;
    @FXML
    private JFXDrawer accomodationdrawer;
    @FXML
    private Label jobbutton;
    @FXML
    private Label librarybutton;
    @FXML
    private Label thingsbutton;
    @FXML
    private Label covidbutton;
    @FXML
    private Label earnbutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slideshow();
    }

    public void slideshow() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("resources/hatirjheel.jpg"));
        images.add(new Image("resources/lalbagh.jpg"));
        images.add(new Image("resources/tsc.jpg"));
        images.add(new Image("resources/buriganga.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            homebg.setImage(images.get(count));
            count++;
            if (count == 4) {
                count = 0;
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
    private void accomodationIsPressed(MouseEvent event) {
        try {
            VBox vbox = FXMLLoader.load(getClass().getResource("accomdropdown.fxml"));
            accomodationdrawer.setSidePane(vbox);
        } catch (IOException ex) {
            Logger.getLogger(home1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (accomodationdrawer.isOpened()) {
            accomodationdrawer.close();
        } else {
            accomodationdrawer.open();
        }
    }

    @FXML
    private void jobIsPressed(MouseEvent event) {
    }

    @FXML
    private void libraryIsPressed(MouseEvent event) {
    }

    @FXML
    private void thingsIsPressed(MouseEvent event) {
    }

    @FXML
    private void earnIsPressed(MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) logo.getScene().getWindow();
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("earn.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void covidIsPressed(MouseEvent event) {
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
}
