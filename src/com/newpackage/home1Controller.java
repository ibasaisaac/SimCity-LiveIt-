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

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class home1Controller implements Initializable {

    @FXML
    private ImageView homebg;
    int count = 0;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private Label accomodation;
    @FXML
    private JFXDrawer accomodationdrawer;
    @FXML
    private Label earn;
    @FXML
    private Button earnButton;
    @FXML
    private AnchorPane anchorpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slideshow();
         if (hamburgerdrawer.isClosed()) 
        {
            //hamburgerdrawer.setVisible(true);
            hamburgerdrawer.setDisable(true);
            hamburgerdrawer.setVisible(true);
        }
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

//    @FXML
//    hamburgerdrawer.setVisible(false);
    @FXML
    private void hamburgerIsPressed(MouseEvent event) {
        try {
            //hamburgerdrawer.setVisible(false);
            VBox vbox = FXMLLoader.load(getClass().getResource("hamburger.fxml"));
            hamburgerdrawer.setSidePane(vbox);
        } catch (IOException ex) {
            Logger.getLogger(home1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (hamburgerdrawer.isOpened()) 
        {
            hamburgerdrawer.close();
            hamburgerdrawer.setDisable(true);
        }
        else {
            hamburgerdrawer.open();
            hamburgerdrawer.setVisible(true);
            hamburgerdrawer.setDisable(false);
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
    
    ///ib
    
    @FXML
    private void earnIsPressed(MouseEvent event) throws IOException {
   
        hamburgerdrawer.setVisible(false);
        Parent root;
        root =FXMLLoader.load(getClass().getResource("earn.fxml"));
        Stage stage;
        stage=(Stage)earnButton.getScene().getWindow(); 
        Scene scene;
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
