package com.newpackage;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class loginController implements Initializable {

    @FXML
    private Label alertlogin;
    @FXML
    private TextField usernameloginfield;
    @FXML
    private PasswordField passwordloginfield;
    @FXML
    private Button signinbutton;
    @FXML
    private Hyperlink registerbutton;

    @FXML
    private Label alertsignup;
    @FXML
    private TextField nidfield;
    @FXML
    private TextField namefield;
    @FXML
    private TextField usernamefield;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private TextField mobilefield;
    @FXML
    private Button signupbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registerIsClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        Scene signupScene = new Scene(FXMLLoader.load(getClass().getResource("signup.fxml")));
        stage.setScene(signupScene);
        stage.show();
    }

    @FXML
    private void SigninOnClick(ActionEvent event) {
        String username = usernameloginfield.getText();
        String password = passwordloginfield.getText();

        User u = new User(username, password);
        boolean flag = u.login();

        if (flag) {
            alertlogin.setText("Login successful!");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertlogin);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                alertlogin.setText(null);
                System.out.println(u.name);
                Stage stage = (Stage) signinbutton.getScene().getWindow();
                stage.close();
            });
            fade.play();
        } else {
            alertlogin.setText("Wrong credentials!");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertlogin);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> alertlogin.setText(null));
            fade.play();
        }
    }

    @FXML
    private void SignUpOnClick(ActionEvent event) {
        int f = 0;

        String t = nidfield.getText();
        if (t.length() != 10) {
            f = 1;
        }
        long nid = Long.parseLong(t);
        String username = usernamefield.getText();
        String name = namefield.getText();
        String password = passwordfield.getText();
        String g = mobilefield.getText();
        if (g.length() != 11) {
            f = 2;
        }
        long mobile = Long.parseLong(g);

        if (f == 1) {
            alertsignup.setText("Enter 10 digits NID!");
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                alertsignup.setText(null);
            });
            pause.play();
        } else if (f == 2) {
            alertsignup.setText("Enter 11 digits Mobile no.!");
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                alertsignup.setText(null);
            });
            pause.play();
        } else {
            User u = new User(username, password);
            boolean flag = u.signup(nid, name, mobile);

            if (flag) {
                alertsignup.setText("Signup successful!");
                FadeTransition fade = new FadeTransition(Duration.seconds(2), alertsignup);
                fade.setFromValue(10.0);
                fade.setToValue(0.1);
                fade.setCycleCount(1);
                fade.setOnFinished(e -> {
                    alertsignup.setText(null);
                    Stage stage = (Stage) signupbutton.getScene().getWindow();
                    stage.close();
                });
                fade.play();
            } else {
                alertsignup.setText("Already exists!");
                FadeTransition fade = new FadeTransition(Duration.seconds(2), alertsignup);
                fade.setFromValue(10.0);
                fade.setToValue(0.1);
                fade.setCycleCount(1);
                fade.setOnFinished(e -> alertsignup.setText(null));
                fade.play();
            }
        }
    }
}
