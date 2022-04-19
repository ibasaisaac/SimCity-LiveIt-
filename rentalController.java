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
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Tasnim
 */
public class rentalController implements Initializable {

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

    static ArrayList<Blob> photos;
    static Integer photonumber = 0;
    @FXML
    private ImageView photo;
    @FXML
    private Text name;
    @FXML
    private Text type;
    @FXML
    private Text bed;
    @FXML
    private Text bath;
    @FXML
    private Text guest;
    @FXML
    private Text price;
    @FXML
    private Text rating;

    @FXML
    private TableView<Rental> table;
    @FXML
    private TableColumn<Rental, String> colName;
    @FXML
    private TableColumn<Rental, Integer> colBed;
    @FXML
    private TableColumn<Rental, Integer> colBath;
    @FXML
    private TableColumn<Rental, Integer> colGuest;
    @FXML
    private TableColumn<Rental, Double> colPrice;
    @FXML
    private Button bookbutton;
    @FXML
    private Text location;
    @FXML
    private VBox detailsbox;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRentalList();
        if (User.USERTYPE == 1) {
            bookbutton.setVisible(false);
        }
        detailsbox.setVisible(false);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rental>() {
            @Override
            public void changed(ObservableValue<? extends Rental> observableValue, Rental oldValue, Rental newValue) {
                detailsbox.setVisible(true);
                Rental.rentalid = newValue.getRentalId();
                loadRentalDetails(Rental.rentalid);

            }
        });

    }

    public ObservableList<Rental> loadRentalList() {

        ObservableList<Rental> rentalList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            int rid = 0;
            String sql = "select rid, title, bed, bath, occupancy, price from rental";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Rental rental;

            while (rs.next()) {
                rid = rs.getInt("RID");
                rental = new Rental(rs.getInt("RID"), rs.getString("title"), rs.getInt("bed"), rs.getInt("bath"), rs.getInt("occupancy"), rs.getDouble("price"));
                rentalList.add(rental);
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return rentalList;
    }

    private void showRentalList() {

        ObservableList<Rental> list = loadRentalList();
        colName.setCellValueFactory(new PropertyValueFactory<Rental, String>("name"));
        colBed.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("bed"));
        colBath.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("bath"));
        colGuest.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("guest"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Rental, Double>("price"));
        table.setItems(list);
    }

    private void loadRentalDetails(int id) {

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select rid, title, area, street, type, bed, bath, occupancy, rating, price from rental where rid=?";
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

                type.setText(rs.getString(5));
                if (rs.getInt(6) > 1) {
                    bed.setText(rs.getInt(6) + " beds");
                } else {
                    bed.setText(rs.getInt(6) + " bed");
                }

                if (rs.getInt(7) > 1) {
                    bath.setText(rs.getInt(7) + " baths");
                } else {
                    bath.setText(rs.getInt(7) + " bath");
                }

                if (rs.getInt(8) > 1) {
                    guest.setText(rs.getInt(8) + " guests");
                } else {
                    guest.setText(rs.getInt(8) + " guest");
                }

                rating.setText(String.format("%1.1f", rs.getDouble(9)));
                price.setText(String.format("%.2f", rs.getDouble(10)) + " BDT/night");
            }

            sql = "select image from photo where rental=?";
            photonumber = 0;
            photos = new ArrayList<Blob>();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                photos.add((Blob) rs.getBlob(1));
            }

            InputStream in = photos.get(photonumber).getBinaryStream();
            BufferedImage bufferedImage = ImageIO.read(in);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            photo.setImage(image);

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void previousImageIsPressed(MouseEvent event) throws IOException, SQLException {
        int i = (photonumber--) % photos.size();
        InputStream in = photos.get(i).getBinaryStream();
        BufferedImage bufferedImage = ImageIO.read(in);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        photo.setImage(image);
    }

    @FXML
    private void nextImageIsPressed(MouseEvent event) throws SQLException, IOException {
        int i = (photonumber++) % photos.size();
        InputStream in = photos.get(i).getBinaryStream();
        BufferedImage bufferedImage = ImageIO.read(in);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        photo.setImage(image);
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
            earnbutton.setVisible(true);
        } else {
            hamburgerdrawer.open();
            covidbutton.setVisible(false);
            earnbutton.setVisible(false);
        }
    }

    @FXML
    private void earnIsPressed(MouseEvent event) {
    }

    @FXML
    private void covidIsPressed(MouseEvent event) {
    }

    @FXML
    private void bookRentalIsPressed(MouseEvent event) throws IOException {
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
