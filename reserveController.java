/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newpackage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class reserveController implements Initializable {

    static private boolean insertion = false;
    @FXML
    private ImageView backbutton;
    @FXML
    private Group logo;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private TextField firstnamefield;
    @FXML
    private TextField lastnamefield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField countryfield;
    @FXML
    private TextField phonefield;
    @FXML
    private ToggleGroup bookingtoggle;
    @FXML
    private ToggleGroup paymenttoggle;
    @FXML
    private Button bookingbutton;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private Label covidbutton;
    @FXML
    private Label filterbutton;
    @FXML
    private DatePicker checkinpicker;
    @FXML
    private DatePicker checkoutpicker;
    @FXML
    private Label alertReservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void completeBookingIsPressed(MouseEvent event) throws IOException {

        boolean flag = false;
        User u = new User();
        u.getUser(User.LOGINTOGGLE);
        if (insertion == false) {
            try {
                dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
                Connection con = dbmsconnect.getConnection();

                String sql = "insert into reservation (customerid, hotelroomid, noofrooms, rentalid, amount, payment_status, firstname, lastname, email, phone, country, checkin, checkout) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt = con.prepareStatement(sql);
                stmt.setLong(1, User.LOGINTOGGLE);

                if (Room.hotelroomid != 0) {
                    stmt.setInt(2, Room.hotelroomid);
                    Room h = new Room();
                    h.getRoom(Room.hotelroomid);
                    stmt.setInt(3, h.getRoomNo());
                    stmt.setNull(4, Types.NULL);
                    stmt.setDouble(5, h.getPrice());
                } else {
                    stmt.setNull(2, Types.NULL);
                    stmt.setNull(3, Types.NULL);
                    stmt.setInt(4, Rental.rentalid);
                    Rental r = new Rental();
                    r.getRental(Rental.rentalid);
                    stmt.setDouble(5, r.getPrice());
                }

                RadioButton selectedRadioButton = (RadioButton) paymenttoggle.getSelectedToggle();
                if ("Pay later".equals(selectedRadioButton.getText())) {
                    stmt.setInt(6, 0);
                } else {
                    Stage loginwindow = new Stage();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("payment.fxml")));
                    loginwindow.setScene(scene);
                    loginwindow.show();
                    stmt.setInt(6, paymentController.payment);
                    if (paymentController.payment == 2) {
                        flag = true;
                    }
                }

                stmt.setString(7, firstnamefield.getText());
                stmt.setString(8, lastnamefield.getText());
                stmt.setString(9, emailfield.getText());
                stmt.setLong(10, Long.parseLong(phonefield.getText()));
                stmt.setString(11, countryfield.getText());
                stmt.setDate(12, java.sql.Date.valueOf(checkinpicker.getValue()));
                stmt.setDate(13, java.sql.Date.valueOf(checkoutpicker.getValue()));
                stmt.executeUpdate();
                insertion = true;
                dbmsconnect.closeConnection(con, stmt);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        hamburgerdrawer.setDisable(true);
        if (flag) {
            alertReservation.setVisible(true);
            alertReservation.setText(" Reservation failed! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertReservation);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                alertReservation.setText(null);
                alertReservation.setVisible(false);
            });
            fade.play();
        } else {
            alertReservation.setVisible(true);
            alertReservation.setText(" Reservation complete! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertReservation);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                try {
                    alertReservation.setText(null);
                    alertReservation.setVisible(false);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
                    hamburgerdrawer.setDisable(false);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            fade.play();
        }
    }

    @FXML
    private void backIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logoIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
        stage.setScene(scene);
        stage.show();
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
        } else {
            hamburgerdrawer.open();
            covidbutton.setVisible(false);
        }
    }

    @FXML
    private void covidIsPressed(MouseEvent event) {
    }

}
