/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newpackage;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class flatformController implements Initializable {

    Set<String> possibleCities = new HashSet<>();
    Set<String> possibleAreas = new HashSet<>();
    private AutoCompletionBinding<String> autoCompletionBinding;
    static private boolean insertion = false;

    @FXML
    private TextField cityfield;
    @FXML
    private TextField areafield;
    @FXML
    private TextField streetfield;
    @FXML
    private WebView mapview;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private Label earnbutton;
    @FXML
    private Label covidbutton;
    @FXML
    private CheckBox eulacheckbox;
    @FXML
    private TextField propertynamefield;
    @FXML
    private TextField descriptionfield;
    @FXML
    private TextField tiinfield;
    @FXML
    private TextField landlordnamefield;
    @FXML
    private TextField phonefield;
    @FXML
    private TextField bedfield;
    @FXML
    private TextField bathfield;
    @FXML
    private TextField rentfield;
    @FXML
    private Label alertFlatListing;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine webEngine = mapview.getEngine();
        String u = "https://www.google.com.bd/maps/@23.9470419,90.3776928,16z?hl=en";
        webEngine.load(u);
    }

    @FXML
    private void hamburgerIsPressed(MouseEvent event) {
    }

    @FXML
    private void earnIsPressed(MouseEvent event) {
    }

    @FXML
    private void covidIsPressed(MouseEvent event) {
    }

    @FXML
    private void loadcitiesforautocomplete(MouseEvent event) {
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select city from region";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                possibleCities.add(rs.getString("city"));
            }
            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        autoCompletionBinding = TextFields.bindAutoCompletion(cityfield, possibleCities);
        cityfield.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    possibleCities.add(cityfield.getText());
                    if (autoCompletionBinding != null) {
                        autoCompletionBinding.dispose();
                    }
                    autoCompletionBinding = TextFields.bindAutoCompletion(cityfield, possibleCities);
                    break;
                default:
                    break;
            }
        });
    }

    @FXML
    private void loadareasforautocomplete(MouseEvent event) {
        if (!cityfield.getText().trim().equals("")) {
            try {
                dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
                Connection con = dbmsconnect.getConnection();
                String sql = "select area from region where city = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, cityfield.getText());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    possibleAreas.add(rs.getString("area"));
                }
                dbmsconnect.closeConnection(con, stmt);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            autoCompletionBinding = TextFields.bindAutoCompletion(areafield, possibleAreas);
            areafield.setOnKeyPressed((KeyEvent e) -> {
                switch (e.getCode()) {
                    case ENTER:
                        possibleAreas.add(areafield.getText());
                        if (autoCompletionBinding != null) {
                            autoCompletionBinding.dispose();
                        }
                        autoCompletionBinding = TextFields.bindAutoCompletion(areafield, possibleAreas);
                        break;
                    default:
                        break;
                }
            });
        }
    }

    @FXML
    private void completeFlatFormIsPressed(MouseEvent event) {
        if (insertion == false) {
            try {
                dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
                Connection con = dbmsconnect.getConnection();

                String sql = "select area from region";
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                int flag = 0;
                while (rs.next()) {
                    if (rs.getString(1).equals(areafield.getText())) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    sql = "insert into region values (?, ?)";
                    stmt = con.prepareStatement(sql);
                    stmt.setString(1, areafield.getText());
                    stmt.setString(2, cityfield.getText());
                    stmt.executeUpdate();

                    User u = new User();
                    u.getUser(User.LOGINTOGGLE);

                    sql = "insert into flat (vendorid, name, area, street, bed, bath, tiin, landlordname, phone, description, rent) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    stmt = con.prepareStatement(sql);
                    stmt.setLong(1, User.LOGINTOGGLE);
                    stmt.setString(2, propertynamefield.getText());
                    stmt.setString(3, areafield.getText());
                    stmt.setString(4, streetfield.getText());
                    stmt.setInt(5, Integer.parseInt(bedfield.getText()));
                    stmt.setInt(6, Integer.parseInt(bathfield.getText()));
                    stmt.setInt(7, Integer.parseInt(tiinfield.getText()));
                    stmt.setString(8, landlordnamefield.getText());
                    stmt.setLong(9, Long.parseLong(phonefield.getText()));
                    stmt.setString(10, descriptionfield.getText());
                    stmt.setDouble(11, Double.parseDouble(rentfield.getText()));
                    stmt.executeUpdate();

                    dbmsconnect.closeConnection(con, stmt);
                    insertion = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        hamburgerdrawer.setDisable(true);
        if (eulacheckbox.isSelected()) {
            alertFlatListing.setVisible(true);
            alertFlatListing.setText(" Listing successful! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertFlatListing);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                try {
                    alertFlatListing.setText(null);
                    alertFlatListing.setVisible(false);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("earn.fxml")));
                    hamburgerdrawer.setDisable(false);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
            fade.play();
        } else {
            alertFlatListing.setVisible(true);
            alertFlatListing.setText(" Please accept User Agreement! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertFlatListing);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                alertFlatListing.setText(null);
                alertFlatListing.setVisible(false);
            });
            fade.play();
        }
    }

}
