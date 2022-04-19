package com.newpackage;

import javafx.scene.control.*;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javafx.scene.control.TabPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * FXML Controller class
 *
 * @author ISABA
 */

public class hotelformController implements Initializable  {

    @FXML
    private Label accomodation;

    @FXML
    private JFXDrawer accomodationdrawer;

    @FXML
    private ImageView backbutton;

    @FXML
    private Label earn;

    @FXML
    private TextField emailfield;

    @FXML
    private Group logo;
    @FXML
    private ImageView homebg;
    int count = 0;
    @FXML
    private Label thingsbutton;
    @FXML
    private Label covidbutton;
    @FXML
    private Label earnbutton;
    
    @FXML
    private JFXHamburger hamburgerbutton;

    @FXML
    private JFXDrawer hamburgerdrawer;

    @FXML
    private TextField namefield;

    @FXML
    private TextField phonefield;

    @FXML
    private TextField cityfield;

    @FXML
    private TextField rolefield;

    @FXML
    private TextField streetfield;

    @FXML
    private TextField ratingfield;

    @FXML
    private TextField tradefield;
    
    @FXML
    private TextField roomnofield;

    @FXML
    private TextField roompricefield;

    @FXML
    private ComboBox<String> roomtypeCombo;
    
    @FXML
    private ComboBox<String> advancecancelCombo;
    
    @FXML
    private ComboBox<String> elsepayCombo;
    
    @FXML
    private ComboBox<String> inFrom;

    @FXML
    private ComboBox<String> inTo;
    
    @FXML
    private ComboBox<String> outFrom;

    @FXML
    private ComboBox<String> outTo;
    
    @FXML
    private ComboBox<String> breakfastCombo;
    
    @FXML
    private TextField invoicenamefield;
    
    @FXML
    private ComboBox<String> parkingCombo;
    
    @FXML
    private TextField guestnofield;
    
    @FXML
    private TabPane tabpane;
    
    @FXML
    private Tab basicTab;
    
    @FXML
    private Tab layoutTab;
    
    @FXML
    private Tab facilitiesTab;
    
    @FXML
    private Tab policiesTab;
    
    @FXML
    private Tab paymentTab;
    
    @FXML
    private Button continueToLayoutButton;
    
    @FXML
    private Button continueToFacilitiesButton;
    
    @FXML
    private Button continueToPoliciesButton;
    
    @FXML
    private Button continueToPaymentButton;
    
    @FXML
    private CheckBox frontdesk;
    
    @FXML
    private CheckBox gym;
    
    @FXML
    private CheckBox pool;
    
    @FXML
    private CheckBox roomservice;
    
    @FXML
    private CheckBox wifi;
    
    @FXML
    private CheckBox restaurant;
    
    @FXML
    private CheckBox ac;
    
    @FXML
    private Button completebutton;
    
    @FXML
    private RadioButton cardradio;

    @FXML
    private RadioButton cashradio;
    
    @FXML
    private RadioButton mobilebankradio;

    @FXML
    private ToggleGroup tgpay;
    
    @FXML
    private CheckBox certify;
    
    @FXML
    private ImageView uploadedimage;
    
    //static byte[] photo = null;
    static ArrayList<byte[]> photos = new ArrayList<byte[]>();
    
    @FXML
    private Label alerthotelform;
    

    
    Set<String> possibleCities = new HashSet<>();
    Set<String> possibleAreas = new HashSet<>();
    private AutoCompletionBinding<String> autoCompletionBinding;
    
    String roles;
    String name;
    float rating;
    String city;
    String street;
    long phone;
    String email;
    int trade;
    String roomtype;
    int roomno;
    int guestno;
    int roomprice;
    String parking;
    String breakfast;
    String amenities="";
    String advancecancel;
    String elsepay;
    String checkinfrom;
    String checkinto;
    String checkoutfrom;
    String checkoutto;
    String checkin;
    String checkout;
    String paymentoption;
    String invoicename;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      roomtypeCombo.getItems().add("Single");
      roomtypeCombo.getItems().add("Double");
      roomtypeCombo.getItems().add("Twin");
      roomtypeCombo.getItems().add("Suite");
      roomtypeCombo.getItems().add("Family");
      
      breakfastCombo.getItems().add("Complimentary Breakfast");
      breakfastCombo.getItems().add("Extra charge for Breakfast");
      breakfastCombo.getItems().add("Breakfast Not available");
      
      parkingCombo.getItems().add("Own parking garage");
      parkingCombo.getItems().add("Rented parking garage");
      parkingCombo.getItems().add("Parking Not available");
      
      advancecancelCombo.getItems().add("Day of arrival");
      advancecancelCombo.getItems().add("1 day");
      advancecancelCombo.getItems().add("2 days");
      advancecancelCombo.getItems().add("7 days");
      advancecancelCombo.getItems().add("14 days");
      
      elsepayCombo.getItems().add("of the first night");
      elsepayCombo.getItems().add("of the full stay");
      
      inFrom.getItems().add("07:00 AM");
      inFrom.getItems().add("08:00 AM");
      inFrom.getItems().add("09:00 AM");
      inFrom.getItems().add("10:00 AM");
      inFrom.getItems().add("11:00 AM");
      inFrom.getItems().add("12:00 PM");
      inFrom.getItems().add("01:00 PM");
      inFrom.getItems().add("02:00 PM");
      inFrom.getItems().add("03:00 PM");
      inFrom.getItems().add("04:00 PM");
      inFrom.getItems().add("05:00 PM");
      inFrom.getItems().add("06:00 PM");
      inFrom.getItems().add("07:00 PM");
      inFrom.getItems().add("08:00 PM");
      
      inTo.getItems().add("12:00 PM");
      inTo.getItems().add("01:00 PM");
      inTo.getItems().add("02:00 PM");
      inTo.getItems().add("03:00 PM");
      inTo.getItems().add("04:00 PM");
      inTo.getItems().add("05:00 PM");
      inTo.getItems().add("06:00 PM");
      inTo.getItems().add("07:00 PM");
      inTo.getItems().add("08:00 PM");
      inTo.getItems().add("09:00 PM");
      inTo.getItems().add("10:00 PM");
      inTo.getItems().add("11:00 PM");
      inTo.getItems().add("12:00 AM");
      
      outFrom.getItems().add("12:00 AM");
      outFrom.getItems().add("01:00 AM");
      outFrom.getItems().add("02:00 AM");
      outFrom.getItems().add("03:00 AM");
      outFrom.getItems().add("04:00 AM");
      outFrom.getItems().add("05:00 AM");
      outFrom.getItems().add("06:00 AM");
      outFrom.getItems().add("07:00 AM");
      outFrom.getItems().add("08:00 AM");
      outFrom.getItems().add("09:00 AM");
      outFrom.getItems().add("10:00 AM");
      outFrom.getItems().add("11:00 AM");
      outFrom.getItems().add("12:00 PM");
      outFrom.getItems().add("01:00 PM");
      outFrom.getItems().add("02:00 PM");
      
      outTo.getItems().add("07:00 AM");
      outTo.getItems().add("08:00 AM");
      outTo.getItems().add("09:00 AM");
      outTo.getItems().add("10:00 AM");
      outTo.getItems().add("11:00 AM");
      outTo.getItems().add("12:00 PM");
      outTo.getItems().add("01:00 PM");
      outTo.getItems().add("02:00 PM");
      outTo.getItems().add("03:00 PM");
      outTo.getItems().add("04:00 PM");
      outTo.getItems().add("05:00 PM");
      outTo.getItems().add("06:00 PM");
      outTo.getItems().add("07:00 PM");
      outTo.getItems().add("08:00 PM");
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

            autoCompletionBinding = TextFields.bindAutoCompletion(streetfield, possibleAreas);
            streetfield.setOnKeyPressed((KeyEvent e) -> {
                switch (e.getCode()) {
                    case ENTER:
                        possibleAreas.add(streetfield.getText());
                        if (autoCompletionBinding != null) {
                            autoCompletionBinding.dispose();
                        }
                        autoCompletionBinding = TextFields.bindAutoCompletion(streetfield, possibleAreas);
                        break;
                    default:
                        break;
                }
            });
        }
    }

    
    
    @FXML
    void accomodationIsPressed(MouseEvent event) {

    }

    @FXML
    private void backIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void earnIsPressed(MouseEvent event) {

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
            hamburgerdrawer.setDisable(true);
        } else {
            hamburgerdrawer.open();
            covidbutton.setVisible(false);
            earnbutton.setVisible(false);
            hamburgerdrawer.setDisable(false);
        }
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
    
    @FXML
    private void continueToLayout(ActionEvent event)
    {
        roles = rolefield.getText();
        name = namefield.getText();
        rating = Float.parseFloat(ratingfield.getText());
        city = cityfield.getText();
        street = streetfield.getText();
        phone = Long.parseLong(phonefield.getText());
        email = emailfield.getText();
        trade = Integer.parseInt(tradefield.getText());
        
//        SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
//        selectionModel.select(layoutTab);
        
        tabpane.getSelectionModel().select(layoutTab);

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
    private void continueToFacilities(ActionEvent event)
    {
        roomtype = roomtypeCombo.getValue();
        //System.out.println(roomtype);
        roomno = Integer.parseInt(roomnofield.getText());
        guestno = Integer.parseInt(guestnofield.getText());
        roomprice = Integer.parseInt(roompricefield.getText());
        
        tabpane.getSelectionModel().select(facilitiesTab);
    }
    
    @FXML
    private void continueToPolicies(ActionEvent event)
    {
        breakfast = breakfastCombo.getValue();
        parking = parkingCombo.getValue();
        
        if(wifi.isSelected())
        {
            //if(amenities!=" ")
                amenities += "Free WiFi  ";
            //else
            //{
                //amenities = "Free WiFi ";
            //}
        }
        if(pool.isSelected())
        {
            //amenities += "Swimming Pool ";
            //if(amenities!=" ")
                amenities += "Swimming Pool  ";
            //else
            //{
               // amenities = "Swimming Pool ";
            //}
        }
        if(ac.isSelected())
        {
            amenities += "Air Conditioned  ";
            //amenities.add("Air Conditioned");
        }
        if(gym.isSelected())
        {
            amenities += "Gym  ";
            //amenities.add("Gym");
        }
        if(roomservice.isSelected())
        {
            amenities += "Room Service  ";
            //amenities.add("Room Service");
        }
        if(frontdesk.isSelected())
        {
            amenities += "24 hr Front Desk Service  ";
            //amenities.add("24 hour Front Desk Service");
        }
        if(restaurant.isSelected())
        {
            amenities += "Restaurant  ";
            //amenities.add("Restaurant");
        }
        
        tabpane.getSelectionModel().select(policiesTab);
//        for(int i=0; i<amenities.size(); i++)
//            System.out.println(amenities.get(i)+" ");
    }
    
    @FXML
    private void continueToPayment(ActionEvent event)
    {
        advancecancel = advancecancelCombo.getValue();
        elsepay = elsepayCombo.getValue();
        
        checkinfrom = inFrom.getValue();
        checkinto = inTo.getValue();
        checkoutfrom = outFrom.getValue();
        checkoutto = outTo.getValue();
        
        checkin = String.join(" - ",checkinfrom,checkinto);
        checkout = String.join(" - ",checkoutfrom,checkoutto);
        
        tabpane.getSelectionModel().select(paymentTab);
        
    }
    
    @FXML
    private void completeButtonIsPressed(ActionEvent event) throws IOException
    {
        if(cashradio.isSelected())
        {
             paymentoption = cashradio.getText();
        }
        else if(cardradio.isSelected())
        {
             paymentoption = cardradio.getText();
        }
        else if(mobilebankradio.isSelected())
        {
             paymentoption = mobilebankradio.getText();
        }
        
        //System.out.println(paymentoption);
        
        invoicename = invoicenamefield.getText();
        
//        if(!certify.isSelected())
//        {
//            completebutton.setDisable(false);
//            return;
//        }
            
        //completebutton.setDisable(certify.isSelected()==false);
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            
            con.setAutoCommit(false);
            PreparedStatement stmt;
            
            int hotelID = 0,roomID=0, hotelfacility=0, photoID=0;
            String sql = "select count(HID) from hotel";
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hotelID = rs.getInt(1);
            }
            hotelID++;
            sql = "select count(RoomID) from room";
            stmt = con.prepareStatement(sql);
            ResultSet rs1 = stmt.executeQuery();
            while (rs1.next()) {
                roomID = rs1.getInt(1);
            }
            roomID++;
            sql = "select count(HFID) from hotelfacility";
            stmt = con.prepareStatement(sql);
            ResultSet rs2 = stmt.executeQuery();
            while (rs2.next()) {
                hotelfacility = rs2.getInt(1);
            }
            hotelfacility++;
            sql = "select count(PID) from photo";
            stmt = con.prepareStatement(sql);
            ResultSet rs3 = stmt.executeQuery();
            while (rs3.next()) {
                photoID = rs3.getInt(1);
            }
            photoID++;
            
            sql = "insert into hotel (HID,name,rating,city,street,phone,email,trade,checkintime,checkouttime,paymentoption,invoicename) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, hotelID);
            stmt.setString(2, name);
            stmt.setFloat(3, rating);
            stmt.setString(4, city);
            stmt.setString(5, street);
            stmt.setLong(6, phone);
            stmt.setString(7, email);
            stmt.setInt(8, trade);
            stmt.setString(9, checkin);
            stmt.setString(10, checkout);
            stmt.setString(11, paymentoption);
            stmt.setString(12, invoicename);
            
            int status=0;
            status = stmt.executeUpdate();
            
            if(status>0)
                System.out.println("inserted hotel");
            
            sql = "insert into room(RoomID, HID, roomtype, noOfroom, guestno, roomprice) values (?,?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, roomID);
            stmt.setInt(2, hotelID);
            stmt.setString(3, roomtype);
            stmt.setInt(4, roomno);
            stmt.setInt(5, guestno);
            stmt.setInt(6, roomprice);
            
            int status2 = stmt.executeUpdate();
            if(status2>0)
                System.out.println("inserted room");
            
            sql = "insert into hotelfacility(HFID, HID, parking, breakfast, amenity) values (?,?,?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, hotelfacility);
            stmt.setInt(2, hotelID);
            stmt.setString(3, parking);
            stmt.setString(4, breakfast);
            stmt.setString(5, amenities);
            
            int status3 = stmt.executeUpdate();
            if(status3>0)
                System.out.println("inserted facility");
            
            sql = "insert into photo (image, HID) values (?, ?)";
            stmt = con.prepareStatement(sql);
            for (int i = 0; i < photos.size(); i++) {
                stmt.setBlob(1, new javax.sql.rowset.serial.SerialBlob(photos.get(i)));
                stmt.setInt(2, hotelID);
                stmt.executeUpdate();
            }
            
            int status4 = stmt.executeUpdate();
            if(status4>0)
                System.out.println("inserted photo");
            
            con.commit();
            dbmsconnect.closeConnection(con, stmt);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        hamburgerdrawer.setDisable(true);
        if (certify.isSelected()) {
            alerthotelform.setText(" Hotel Listing successful! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alerthotelform);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                try {
                    alerthotelform.setText(null);
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
        } else if(!certify.isSelected()) {
            alerthotelform.setText(" Please accept User Agreement! ");
            FadeTransition fade = new FadeTransition(Duration.seconds(2), alerthotelform);
            fade.setFromValue(10.0);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setOnFinished(e -> {
                alerthotelform.setText(null);
            });
            fade.play();
        }

//        else if(!certify.isSelected())
//        {
//            completebutton.setDisable(true);
       // }
    }
    
}
