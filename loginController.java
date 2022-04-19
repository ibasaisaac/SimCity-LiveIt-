package com.newpackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private ToggleGroup UserTypeToggle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void registerIsClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerbutton.getScene().getWindow();
        Scene signupScene = new Scene(FXMLLoader.load(getClass().getResource("signup.fxml")));
        stage.setScene(signupScene);
        stage.show();
    }

    @FXML
    public void SigninOnClick(ActionEvent event) {
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
                User.LOGINTOGGLE = u.getNID();
                User.USERTYPE = u.gettype();
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
    public void SignUpOnClick(ActionEvent event) {

        RadioButton selectedRadioButton = (RadioButton) UserTypeToggle.getSelectedToggle();
        if ("Vendor".equals(selectedRadioButton.getText())) {
            User.USERTYPE = 1;
        } else {
            User.USERTYPE = 2;
        }

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
            boolean flag = u.signup(nid, u.USERTYPE, name, mobile);

            if (flag) {
                alertsignup.setText("Signup successful!");
                FadeTransition fade = new FadeTransition(Duration.seconds(2), alertsignup);
                fade.setFromValue(10.0);
                fade.setToValue(0.1);
                fade.setCycleCount(1);
                fade.setOnFinished(e -> {
                    alertsignup.setText(null);
                    User.LOGINTOGGLE = u.getNID();
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

    @FXML
    private void googleloginIsPressed(MouseEvent event) {

        String gClientId = "978302725662-vpv5j3c87s1n2vahgbj7vefmc94csseb.apps.googleusercontent.com";
        String gRedir = "http://localhost:8080/authorization-code/callback";
        String gScope = "https://www.googleapis.com/auth/userinfo.profile";
        String gSecret = "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";
        OAuthAuthenticator auth = new OAuthGoogleAuthenticator(gClientId, gRedir, gSecret, gScope);
        auth.startLogin();
    }

    @FXML
    private void fbloginIsPressed(MouseEvent event) {

        String FACEBOOK_clientID = "###############";
        String FACEBOOK_redirectUri = "http://localhost:8080/authorization-code/callback";
        String FACEBOOK_fieldsString = "name,gender,id";
        String FACEBOOK_clientSecret = "#########";

        OAuthAuthenticator authFB = new OAuthFacebookAuthenticator(FACEBOOK_clientID, FACEBOOK_redirectUri, FACEBOOK_clientSecret, FACEBOOK_fieldsString);
        authFB.startLogin();
    }

}
