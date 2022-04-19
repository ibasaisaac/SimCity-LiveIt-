package com.newpackage;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.animation.FadeTransition;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class rentalformController implements Initializable {

    public static long rentalSceneTOGGLE = 0;
    static String title;
    static String area;
    static String street;
    static String roomtype;
    static int bed;
    static int bath;
    static int occupancy;
    static double price;
    static int tiin;
    static String hostname;
    static long phone;
    static String email;
    static String description;
    static ArrayList<byte[]> photos = new ArrayList<byte[]>();
    Set<String> possibleCities = new HashSet<>();
    Set<String> possibleAreas = new HashSet<>();
    private AutoCompletionBinding<String> autoCompletionBinding;
    static private boolean insertion = false;

    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private Label earnbutton;
    @FXML
    private Label covidbutton;

    @FXML
    private TextField cityfield;
    @FXML
    private TextField areafield;
    @FXML
    private TextField streetfield;
    @FXML
    private WebView mapview;

    @FXML
    private TextField propertynamefield;
    @FXML
    private TextField descriptionfield;
    @FXML
    private TextField tiinfield;
    @FXML
    private TextField hostnamefield;
    @FXML
    private TextField phonefield;
    @FXML
    private TextField emailfield;

    @FXML
    private MenuButton roomtypefield;
    @FXML
    private TextField bedfield;
    @FXML
    private TextField bathfield;
    @FXML
    private TextField occupancyfield;
    @FXML
    private TextField pricefield;

    @FXML
    private ToggleGroup BreakfastRadio;
    @FXML
    private ToggleGroup ParkingRadio;
    @FXML
    private JFXCheckBox bangla;
    @FXML
    private JFXCheckBox english;
    @FXML
    private JFXCheckBox wifi;
    @FXML
    private JFXCheckBox ac;

    @FXML
    private ImageView uploadedimage;

    @FXML
    private Label alertRentalListing;
    @FXML
    private TabPane rentalformtabpane;
    @FXML
    private Tab locationtab;
    @FXML
    private Tab infotab;
    @FXML
    private Tab layouttab;
    @FXML
    private Tab facilitytab;
    @FXML
    private Tab phototab;
    @FXML
    private Tab paymenttab;
    @FXML
    private CheckBox eulacheckbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (rentalSceneTOGGLE == 1) {
            WebEngine webEngine = mapview.getEngine();
            String u = "https://www.google.com.bd/maps/@23.9470419,90.3776928,16z?hl=en";
            webEngine.load(u);
        }
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
    private void hamburgerIsPressed(MouseEvent event) {
    }

    @FXML
    private void earnIsPressed(MouseEvent event) {
    }

    @FXML
    private void covidIsPressed(MouseEvent event) {
    }

    @FXML
    private void continueToInfoIsPressed(MouseEvent event) throws IOException {

        area = areafield.getText();
        street = streetfield.getText();

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
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        rentalformtabpane.getSelectionModel().select(infotab);
    }

    @FXML
    private void continueToLayoutIsPressed(MouseEvent event) throws IOException {

        title = propertynamefield.getText();
        description = descriptionfield.getText();
        tiin = Integer.parseInt(tiinfield.getText());
        hostname = hostnamefield.getText();
        phone = Long.parseLong(phonefield.getText());
        email = emailfield.getText();

        rentalformtabpane.getSelectionModel().select(layouttab);
    }

    @FXML
    private void continueToFacilitiesIsPressed(MouseEvent event) throws IOException {

        roomtype = roomtypefield.getText();
        bed = Integer.parseInt(bedfield.getText());
        bath = Integer.parseInt(bathfield.getText());
        occupancy = Integer.parseInt(occupancyfield.getText());
        price = Double.parseDouble(pricefield.getText());

        rentalformtabpane.getSelectionModel().select(facilitytab);
    }

    @FXML
    private void singleIsChosen(ActionEvent event) {
        roomtype = "Single";
        roomtypefield.setText("Single");
    }

    @FXML
    private void doubleIsChosen(ActionEvent event) {
        roomtype = "Double";
        roomtypefield.setText("Double");
    }

    @FXML
    private void twinIsChosen(ActionEvent event) {
        roomtype = "Twin";
        roomtypefield.setText("Twin");
    }

    @FXML
    private void continueToPhotosIsPressed(MouseEvent event) throws IOException {

        RadioButton breakfast = (RadioButton) BreakfastRadio.getSelectedToggle();
        RadioButton parking = (RadioButton) ParkingRadio.getSelectedToggle();

        ArrayList<String> language = new ArrayList<String>();
        if (bangla.isSelected()) {
            language.add("Bangla");
        }
        if (english.isSelected()) {
            language.add("English");
        }
        ArrayList<String> facility = new ArrayList<String>();
        if (wifi.isSelected()) {
            facility.add("Free WiFi");
        }
        if (wifi.isSelected()) {
            facility.add("AC");
        }

        rentalformtabpane.getSelectionModel().select(phototab);
    }

    @FXML
    private void anotherPhotoUpload(MouseEvent event) {
        uploadedimage.setImage(null);
    }

    @FXML
    private void uploadImageIsPressed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            uploadedimage.setImage(image);
            uploadedimage.setFitWidth(300);
            uploadedimage.setFitHeight(300);
            uploadedimage.scaleXProperty();
            uploadedimage.scaleYProperty();
            uploadedimage.setSmooth(true);
            uploadedimage.setCache(true);

            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }

            photos.add(bos.toByteArray());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void continueToPaymentIsPressed(MouseEvent event) throws IOException {
        rentalformtabpane.getSelectionModel().select(paymenttab);
    }

    @FXML
    private void completeRentalFormIsPressed(MouseEvent event) throws IOException {
        if (insertion == false) {
            int rid = 0;
            User u = new User();
            u.getUser(User.LOGINTOGGLE);
            try {
                dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
                Connection con = dbmsconnect.getConnection();

                String sql = "insert into rental (vendorid, title, area, street, type, bed, bath, occupancy, price, tiin, hostname, phone, email, rating, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt = con.prepareStatement(sql);
                stmt.setLong(1, User.LOGINTOGGLE);
                stmt.setString(2, title);
                stmt.setString(3, area);
                stmt.setString(4, street);
                stmt.setString(5, roomtype);
                stmt.setInt(6, bed);
                stmt.setInt(7, bath);
                stmt.setInt(8, occupancy);
                stmt.setDouble(9, price);
                stmt.setInt(10, tiin);
                stmt.setString(11, hostname);
                stmt.setLong(12, phone);
                stmt.setString(13, email);
                stmt.setDouble(14, 0.0);
                stmt.setString(15, description);
                stmt.executeUpdate();

                int rentalID = 0;
                sql = "select max(RID) from rental";
                stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    rentalID = rs.getInt(1);
                }
                sql = "insert into photo (image, rental) values (?, ?)";
                stmt = con.prepareStatement(sql);
                for (int i = 0; i < photos.size(); i++) {
                    stmt.setBlob(1, new javax.sql.rowset.serial.SerialBlob(photos.get(i)));
                    stmt.setInt(2, rentalID);
                    stmt.executeUpdate();
                }
                insertion = true;
                dbmsconnect.closeConnection(con, stmt);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        hamburgerdrawer.setDisable(true);
        if (eulacheckbox.isSelected()) {
            alertRentalListing.setVisible(true);
            alertRentalListing.setText(" Listing successful! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertRentalListing);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                try {
                    alertRentalListing.setText(null);
                    alertRentalListing.setVisible(false);
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
            alertRentalListing.setVisible(true);
            alertRentalListing.setText(" Please accept User Agreement! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alertRentalListing);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                alertRentalListing.setText(null);
                alertRentalListing.setVisible(false);
            });
            fade.play();
        }
    }
}
