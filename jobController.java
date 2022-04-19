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
public class jobController implements Initializable {

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
    private TableView<Job> table;
    @FXML
    private Text location;
    @FXML
    private VBox detailsbox;
    @FXML
    private TableColumn<Job, String> colPosition;
    @FXML
    private TableColumn<Job, String> colCompany;
    @FXML
    private TableColumn<Job, String> colExperience;
    @FXML
    private TableColumn<Job, String> colQualification;
    @FXML
    private TableColumn<Job, Double> colSalary;
    @FXML
    private Text position;
    @FXML
    private Text company;
    @FXML
    private Text vacancy;
    @FXML
    private Text qualification;
    @FXML
    private Text experience;
    @FXML
    private Text status;
    @FXML
    private Text salary;
    @FXML
    private Text email;
    @FXML
    private Text deadline;
    @FXML
    private Text description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showJobList();
        detailsbox.setVisible(false);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Job>() {
            @Override
            public void changed(ObservableValue<? extends Job> observableValue, Job oldValue, Job newValue) {
                detailsbox.setVisible(true);
                loadJobDetails(newValue.getJobId());

            }
        });

    }

    public ObservableList<Job> loadJobList() {

        ObservableList<Job> jobList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            int jid = 0;
            String sql = "select jid, position, company, experience, qualification, salary from job";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Job job;

            while (rs.next()) {
                jid = rs.getInt("JID");
                job = new Job(rs.getInt("JID"), rs.getString("position"), rs.getString("company"), rs.getString("experience"), rs.getString("qualification"), rs.getDouble("salary"));
                jobList.add(job);
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return jobList;
    }

    private void showJobList() {

        ObservableList<Job> list = loadJobList();
        colPosition.setCellValueFactory(new PropertyValueFactory<Job, String>("position"));
        colCompany.setCellValueFactory(new PropertyValueFactory<Job, String>("company"));
        colExperience.setCellValueFactory(new PropertyValueFactory<Job, String>("experience"));
        colQualification.setCellValueFactory(new PropertyValueFactory<Job, String>("qualification"));
        colSalary.setCellValueFactory(new PropertyValueFactory<Job, Double>("salary"));
        table.setItems(list);
    }

    private void loadJobDetails(int id) {

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select jid, position, company, vacancy, status, qualification, experience, description, location, salary, email, deadline from job where jid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                position.setText("Position:  "+rs.getString(2));
                company.setText("Company:  "+rs.getString(3));
                vacancy.setText("Vacancy:  "+String.valueOf(rs.getInt(4)));
                status.setText("Status:  "+rs.getString(5));
                qualification.setText("Educational requirements:  "+rs.getString(6));
                experience.setText("Experience requirements:  "+rs.getString(7));
                description.setText("Additional requirements:  "+rs.getString(8));

                String area, city = null;
                area = rs.getString(9);
                sql = "select city from region where area=?";
                PreparedStatement stmt2 = con.prepareStatement(sql);
                stmt2.setString(1, area);
                ResultSet rs2 = stmt2.executeQuery();
                while (rs2.next()) {
                    city = rs2.getString(1);
                }
                location.setText("Job location:  "+area + ", " + city);

                salary.setText("Salary:  "+String.format("%.2f", rs.getDouble(10)) + " BDT/month");
                email.setText("Email:  "+rs.getString(11));
                deadline.setText("Application deadline:  "+rs.getDate(12).toString());
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
