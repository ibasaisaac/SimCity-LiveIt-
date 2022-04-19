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
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class jobformController implements Initializable {

    Set<String> possibleCities = new HashSet<>();
    Set<String> possibleAreas = new HashSet<>();
    Set<String> possibleCategories = new HashSet<>();
    private AutoCompletionBinding<String> autoCompletionBinding;
    static private boolean insertion = false;

    @FXML
    private TextField cityfield;
    @FXML
    private TextField areafield;
    @FXML
    private TextField descriptionfield;
    @FXML
    private TextField emailfield;
    @FXML
    private CheckBox eulacheckbox;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private Label earnbutton;
    @FXML
    private Label covidbutton;
    @FXML
    private TextField categoryfield;
    @FXML
    private TextField providerfield;
    @FXML
    private TextField positionfield;
    @FXML
    private ChoiceBox<String> statuschoicebox;
    @FXML
    private TextField qualificationfield;
    @FXML
    private TextField experiencefield;
    @FXML
    private CheckBox workfromhomecheckbox;
    @FXML
    private TextField salaryfield;
    @FXML
    private DatePicker deadlinepicker;
    @FXML
    private Label alertJobListing;
    @FXML
    private Spinner<Integer> vacancyspinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vacancyspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));
        statuschoicebox.getItems().add("Full time");
        statuschoicebox.getItems().add("Part time");
        areafield.setEditable(workfromhomecheckbox.isSelected() == false);
        cityfield.setEditable(workfromhomecheckbox.isSelected() == false);
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
    private void loadcategoriesforautocomplete(MouseEvent event) {
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select category from job";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                possibleCategories.add(rs.getString("category"));
            }
            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        autoCompletionBinding = TextFields.bindAutoCompletion(categoryfield, possibleCategories);
        categoryfield.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    possibleCategories.add(categoryfield.getText());
                    if (autoCompletionBinding != null) {
                        autoCompletionBinding.dispose();
                    }
                    autoCompletionBinding = TextFields.bindAutoCompletion(categoryfield, possibleCategories);
                    break;
                default:
                    break;
            }
        });
    }

    @FXML
    private void statuschoiceIsPressed(MouseEvent event) {
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
    private void completeJobFormIsPressed(MouseEvent event) throws SQLException {
        if (insertion == false) {
            try {
                dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
                Connection con = dbmsconnect.getConnection();

                String sql = "select area from region";
                PreparedStatement stmt = con.prepareStatement(sql);
                if (workfromhomecheckbox.isSelected() == false) {
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
                    }
                }

                User u = new User();
                u.getUser(User.LOGINTOGGLE);
                sql = "insert into job (vendorid, category, company, vacancy, position, status, qualification, experience, description, location, salary, email, deadline) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stmt = con.prepareStatement(sql);

                stmt.setLong(1, User.LOGINTOGGLE);
                stmt.setString(2, categoryfield.getText());
                stmt.setString(3, providerfield.getText());
                stmt.setInt(4, Integer.parseInt(vacancyspinner.getEditor().getText()));
                stmt.setString(5, positionfield.getText());
                stmt.setString(6, statuschoicebox.getValue().toString());
                stmt.setString(7, qualificationfield.getText());
                stmt.setString(8, experiencefield.getText());
                stmt.setString(9, descriptionfield.getText());
                if (!workfromhomecheckbox.isSelected()) {
                    stmt.setString(10, areafield.getText());
                } else {
                    stmt.setNull(10, Types.NULL);
                }
                stmt.setDouble(11, Double.parseDouble(salaryfield.getText()));
                stmt.setString(12, emailfield.getText());
                stmt.setDate(13, java.sql.Date.valueOf(deadlinepicker.getValue()));
                stmt.executeUpdate();

                dbmsconnect.closeConnection(con, stmt);
                insertion = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        hamburgerdrawer.setDisable(true);
        if (eulacheckbox.isSelected()) {
            alertJobListing.setVisible(true);
            alertJobListing.setText(" Listing successful! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertJobListing);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                try {
                    alertJobListing.setText(null);
                    alertJobListing.setVisible(false);
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
            alertJobListing.setVisible(true);
            alertJobListing.setText(" Please accept User Agreement! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertJobListing);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                alertJobListing.setText(null);
                alertJobListing.setVisible(false);
            });
            fade.play();
        }
    }

}
