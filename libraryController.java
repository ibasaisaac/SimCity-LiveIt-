package com.newpackage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
public class libraryController implements Initializable {

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

    @FXML
    private Text name;

    @FXML
    private TableView<Library> table;
    @FXML
    private TableColumn<Library, String> colName;
    @FXML
    private TableColumn<Library, String> colArea;
    @FXML
    private TableColumn<Library, String> colHours;
    @FXML
    private Text location;
    @FXML
    private VBox detailsbox;

    @FXML
    private Text hours;
    @FXML
    private Text phone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showLibraryList();
        detailsbox.setVisible(false);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Library>() {
            @Override
            public void changed(ObservableValue<? extends Library> observableValue, Library oldValue, Library newValue) {
                detailsbox.setVisible(true);
                loadLibraryDetails(newValue.getLibraryId());

            }
        });

    }

    public ObservableList<Library> loadLibraryList() {

        ObservableList<Library> libraryList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            int lid = 0;
            String sql = "select lid, name, area, hours from library";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Library library;

            while (rs.next()) {
                lid = rs.getInt("LID");
                library = new Library(rs.getInt("LID"), rs.getString("name"), rs.getString("area"), rs.getString("hours"));
                libraryList.add(library);
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return libraryList;
    }

    private void showLibraryList() {

        ObservableList<Library> list = loadLibraryList();
        colName.setCellValueFactory(new PropertyValueFactory<Library, String>("name"));
        colArea.setCellValueFactory(new PropertyValueFactory<Library, String>("area"));
        colHours.setCellValueFactory(new PropertyValueFactory<Library, String>("hours"));
        table.setItems(list);
    }

    private void loadLibraryDetails(int id) {

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select lid, name, area, street, hours, phone from library where lid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                name.setText(rs.getString(2));

                String street, area, city = null;
                street = rs.getString(4);
                area = rs.getString(3);
//                sql = "select city from region where area=?";
//                PreparedStatement stmt2 = con.prepareStatement(sql);
//                stmt2.setString(1, area);
//                ResultSet rs2 = stmt2.executeQuery();
//                while (rs2.next()) {
//                    city = rs2.getString(1);
//                }
                location.setText(street + ", " + area);

                hours.setText(rs.getString(5));
                phone.setText(String.valueOf(rs.getLong(6)));
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    }

    @FXML
    private void earnIsPressed(MouseEvent event) {
    }

    @FXML
    private void covidIsPressed(MouseEvent event) {
    }

}
