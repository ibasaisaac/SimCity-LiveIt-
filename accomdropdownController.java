/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newpackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class accomdropdownController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hotelIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("hotel.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void rentalIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("rental.fxml")));
        stage.setScene(scene);
        stage.show();
    }    

    @FXML
    private void flatIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("flat.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    
}
