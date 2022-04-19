/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newpackage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class paymentController implements Initializable {

    @FXML
    private Label alertpayment;
    @FXML
    private WebView gatewayview;

    public static int payment = 2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = gatewayview.getEngine();
        String u = "https://epay.sslcommerz.com/a558de4046f184084efb6b784c3ec6b305231bbd";
        webEngine.load(u);
        payment = 1;

    }

    @FXML
    private void paymentdoneispressed(MouseEvent event) {
        if (payment == 1) {
            alertpayment.setText("Payment successful!");
        } else {
            alertpayment.setText("Payment failed!");
        }
        FadeTransition fade = new FadeTransition(Duration.seconds(2), alertpayment);
        fade.setFromValue(10.0);
        fade.setToValue(0.1);
        fade.setCycleCount(1);
        fade.setOnFinished(e -> {
            alertpayment.setText(null);
            Stage stage = (Stage) gatewayview.getScene().getWindow();
            stage.close();
        });
        fade.play();
    }
}
