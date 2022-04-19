package com.newpackage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.mysql.cj.jdbc.Blob;
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
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class flatController implements Initializable {

    @FXML
    private ImageView backbutton;
    @FXML
    private Group logo;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private Label earnbutton;
    @FXML
    private Label covidbutton;
    @FXML
    private Label earnbutton1;

//    static ArrayList<Blob> photos;
//    static Integer photonumber = 0;
    @FXML
    private ImageView photo;
    @FXML
    private Text name;
    @FXML
    private Text bed;
    @FXML
    private Text bath;

    @FXML
    private TableView<Flat> table;
    @FXML
    private TableColumn<Flat, String> colName;
    @FXML
    private TableColumn<Flat, Integer> colBed;
    @FXML
    private TableColumn<Flat, Integer> colBath;
    @FXML
    private TableColumn<Flat, Double> colRent;
    @FXML
    private Text location;
    @FXML
    private VBox detailsbox;
    @FXML
    private Text rent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showFlatList();
        detailsbox.setVisible(false);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flat>() {
            @Override
            public void changed(ObservableValue<? extends Flat> observableValue, Flat oldValue, Flat newValue) {
                detailsbox.setVisible(true);
                loadFlatDetails(newValue.getFlatId());

            }
        });

    }

    public ObservableList<Flat> loadFlatList() {

        ObservableList<Flat> flatList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            int fid = 0;
            String sql = "select fid, name, bed, bath, rent from flat";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Flat flat;

            while (rs.next()) {
                fid = rs.getInt("FID");
                flat = new Flat(rs.getInt("FID"), rs.getString("name"), rs.getInt("bed"), rs.getInt("bath"), rs.getDouble("rent"));
                flatList.add(flat);
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return flatList;
    }

    private void showFlatList() {

        ObservableList<Flat> list = loadFlatList();
        colName.setCellValueFactory(new PropertyValueFactory<Flat, String>("name"));
        colBed.setCellValueFactory(new PropertyValueFactory<Flat, Integer>("bed"));
        colBath.setCellValueFactory(new PropertyValueFactory<Flat, Integer>("bath"));
        colRent.setCellValueFactory(new PropertyValueFactory<Flat, Double>("rent"));
        table.setItems(list);
    }

    private void loadFlatDetails(int id) {

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select fid, name, area, street, bed, bath, rent from flat where fid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                name.setText(rs.getString(2));

                String street, area, city = null;
                street = rs.getString(4);
                area = rs.getString(3);
                sql = "select city from region where area=?";
                PreparedStatement stmt2 = con.prepareStatement(sql);
                stmt2.setString(1, area);
                ResultSet rs2 = stmt2.executeQuery();
                while (rs2.next()) {
                    city = rs2.getString(1);
                }
                location.setText(street + ", " + area + ", " + city);

                
                if (rs.getInt(5) > 1) {
                    bed.setText(rs.getInt(5) + " beds");
                } else {
                    bed.setText(rs.getInt(5) + " bed");
                }

                if (rs.getInt(6) > 1) {
                    bath.setText(rs.getInt(6) + " baths");
                } else {
                    bath.setText(rs.getInt(6) + " bath");
                }

                rent.setText(String.format("%.2f", rs.getDouble(7)) + " BDT/month");
            }

//            sql = "select image from photo where flat=?";
//            photonumber = 0;
//            photos = new ArrayList<Blob>();
//            stmt = con.prepareStatement(sql);
//            stmt.setInt(1, id);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                photos.add((Blob) rs.getBlob(1));
//            }
//
//            InputStream in = photos.get(photonumber).getBinaryStream();
//            BufferedImage bufferedImage = ImageIO.read(in);
//            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
//            photo.setImage(image);

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void previousImageIsPressed(MouseEvent event) throws IOException, SQLException {
//        int i = (photonumber--) % photos.size();
//        InputStream in = photos.get(i).getBinaryStream();
//        BufferedImage bufferedImage = ImageIO.read(in);
//        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
//        photo.setImage(image);
    }

    @FXML
    private void nextImageIsPressed(MouseEvent event) throws SQLException, IOException {
//        int i = (photonumber++) % photos.size();
//        InputStream in = photos.get(i).getBinaryStream();
//        BufferedImage bufferedImage = ImageIO.read(in);
//        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
//        photo.setImage(image);
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
    }

    @FXML
    private void earnIsPressed(MouseEvent event) {
    }

    @FXML
    private void covidIsPressed(MouseEvent event) {
    }


}
