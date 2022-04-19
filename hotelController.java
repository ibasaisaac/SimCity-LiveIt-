package com.newpackage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.mysql.cj.jdbc.Blob;
import static com.newpackage.rentalformController.photos;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ISABA
 */
public class hotelController implements Initializable {


    @FXML
    private JFXDrawer accomodationdrawer;

    @FXML
    private Text amenity;

    @FXML
    private ImageView backbutton;

    @FXML
    private Text breakfast;

    @FXML
    private TableColumn<Room, Integer> colguestno;

    @FXML
    private TableColumn<Room, String> colname;

    @FXML
    private TableColumn<Room, Integer> colphone;

    @FXML
    private TableColumn<Room, Integer> colprice;

    @FXML
    private TableColumn<Room, Double> colrating;

    @FXML
    private TableColumn<Room, Integer> colroomno;

    @FXML
    private TableColumn<Room, String> coltype;

    @FXML
    private Label covidbutton;

    @FXML
    private VBox detailsbox;

    @FXML
    private Label earnbutton;

    @FXML
    private JFXHamburger hamburgerbutton;

    @FXML
    private JFXDrawer hamburgerdrawer;

    @FXML
    private ImageView homebg;

    @FXML
    private Text location;

    @FXML
    private Group logo;

    @FXML
    private Text name;

    @FXML
    private Text parking;

    @FXML
    private ImageView photo;


    @FXML
    private Button reservebutton;


    @FXML
    private TableView<Room> hoteltable;

    static ArrayList<Blob> photos;
    static Integer photonumber = 0;

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
    void hamburgerIsPressed(MouseEvent event) {

    }

    @FXML
    private void logoIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showinTable();
        detailsbox.setVisible(false);
        hoteltable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Room>() {
            @Override
            public void changed(ObservableValue<? extends Room> observableValue, Room oldValue, Room newValue) {
                detailsbox.setVisible(true);
                 Room.hotelroomid = newValue.getHotelID();
                showDetails(Room.hotelroomid);
            }
        });
    }
    ObservableList<Room> hotelList = FXCollections.observableArrayList();

    public void showinTable() {

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            int hid = 0, roomid = 0;
            String sql = "select hotel.HID, room.RoomID, room.roomtype, room.noofroom, room.guestno, room.roomprice, hotel.name, hotel.rating, hotel.city, hotel.street, hotel.phone from hotel inner join room on hotel.HID=room.HID";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Room room;

            while (rs.next()) {
                hid = rs.getInt("HID");
                roomid = rs.getInt("RoomID");
                //System.out.println(roomid);

                room = new Room(rs.getInt("RoomId"), rs.getInt("HID"), rs.getString("roomtype"), rs.getInt("noofroom"), rs.getInt("guestno"), rs.getInt("roomprice"), rs.getString("name"), rs.getInt("rating"), rs.getString("city"), rs.getString("street"), rs.getLong("phone"));

                hotelList.add(room);
            }

            dbmsconnect.closeConnection(con, stmt);

            colname.setCellValueFactory(new PropertyValueFactory<Room,String>("name"));
            colrating.setCellValueFactory(new PropertyValueFactory<Room,Double>("rating"));
            colphone.setCellValueFactory(new PropertyValueFactory<Room,Integer>("phone"));
            
            //coltype.setCellValueFactory(cellData -> cellData.getValue().getRoomType());
            
            coltype.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
            colguestno.setCellValueFactory(new PropertyValueFactory<Room, Integer>("guestNo"));
            colroomno.setCellValueFactory(new PropertyValueFactory<Room, Integer>("roomNo"));
            colprice.setCellValueFactory(new PropertyValueFactory<Room, Integer>("price"));

            hoteltable.setItems(hotelList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void previousImageIsPressed(MouseEvent event) {
    }

    @FXML
    private void nextImageIsPressed(MouseEvent event) {
    }

    public void showDetails(int id) {

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select name, city, street from hotel where HID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                name.setText(rs.getString(1));
                
                String street, city = null; 
                city = rs.getString(2);
                street = rs.getString(3);
                location.setText(street + ", " + city);

            }
                sql = "select HID,HFID,parking,breakfast,amenity from hotelfacility where HID=?";
                PreparedStatement stmt2 = con.prepareStatement(sql);
                stmt2.setInt(1, id);
                ResultSet rs2 = stmt2.executeQuery();
                while (rs2.next()) {
                    parking.setText(rs2.getString(3));
                    breakfast.setText(rs2.getString(4));
                    amenity.setText(rs2.getString(5));
                }

            //}
           

            sql = "select PID, image, HID from photo where HID=?";
            photonumber = 0;
            int pid=0;
            photos = new ArrayList<Blob>();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                photos.add((Blob) rs.getBlob(2));
                pid = rs.getInt(1);
            }
            
            System.out.println(pid);
            InputStream in = photos.get(photonumber).getBinaryStream();
            BufferedImage bufferedImage = ImageIO.read(in);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            photo.setImage(image);
            
//            String SQL = "SELECT File from SomeTable WHERE ID = ?";
//            PreparedStatement pstmt = conn.prepareStatement(SQL);
//            pstmt.setInt(1, fileID);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                InputStream imageFile = rs.getBinaryStream(1);
//                Image image = new Image(imageFile);
//                ImageView iv = new ImageView(image);
//            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void reserveIsPressed(MouseEvent event) throws IOException {
        if (User.LOGINTOGGLE != 0 && User.USERTYPE == 2) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("reserve.fxml")));
            stage.setScene(scene);
            stage.show();

        } else {
            Stage loginwindow = new Stage();
            loginwindow.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            loginwindow.setScene(scene);
            loginwindow.show();
        }
    }
}
