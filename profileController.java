/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newpackage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tasnim
 */
public class profileController implements Initializable {

    @FXML
    private ImageView backbutton;
    @FXML
    private Group logo;
    @FXML
    private JFXDrawer hamburgerdrawer;
    @FXML
    private VBox detailsbox;
    @FXML
    private Text name;
    @FXML
    private JFXHamburger hamburgerbutton;
    @FXML
    private Label earnbutton;
    @FXML
    private Label covidbutton;
    @FXML
    private Text nid;
    @FXML
    private Text username;
    @FXML
    private Text mobile;
    @FXML
    private Label profileheading;
    @FXML
    private TableView<Reservation> customerreservationtable;
    @FXML
    private TableView<Reservation> rentalreservationtable;
    @FXML
    private TableView<Reservation> hotelreservationtable;
    @FXML
    private TableView<Job> joblistingtable;
    @FXML
    private TableView<Flat> flatlistingtable;
    @FXML
    private TableView<Rental> rentallistingtable;
    @FXML
   // private TableView<Hotel> hotellistingtable;
    private Text checklistinglabel;
    User u = new User();
    @FXML
    private TableColumn<Reservation, Integer> colSerial;
    @FXML
    private TableColumn<Reservation, String> colDate;
    @FXML
    private TableColumn<Reservation, String> colService;
    @FXML
    private TableColumn<Reservation, String> colCheckin;
    @FXML
    private TableColumn<Reservation, String> colCheckout;
    @FXML
    private TableColumn<Reservation, Double> colAmount;
    @FXML
    private TableColumn<Reservation, String> colStatus;
    @FXML
    private Text checkjoblistinglabel;
    @FXML
    private Text checkflatlistinglabel;
    @FXML
    private Text checkrentallistinglabel;
    @FXML
    private Text checkhotellistinglabel;
    @FXML
    private TableColumn<Rental, String> colRentalname;
    @FXML
    private TableColumn<Rental, String> colRentalStreet;
    @FXML
    private TableColumn<Rental, String> colRentalArea;
    @FXML
    private TableColumn<Rental, String> colRentalType;
    @FXML
    private TableColumn<Rental, Integer> colRentalBed;
    @FXML
    private TableColumn<Rental, Integer> colRentalBath;
    @FXML
    private TableColumn<Rental, Integer> colRentalOccupancy;
    @FXML
    private TableColumn<Rental, Double> colRentalPrice;
    @FXML
    private TableColumn<Rental, Double> colRentalRating;
    @FXML
    private TableColumn<Rental, String> colRentalDescription;
    @FXML
    private TableColumn<Flat, String> colFlatname;
    @FXML
    private TableColumn<Flat, String> colFlatStreet;
    @FXML
    private TableColumn<Flat, String> colFlatArea;
    @FXML
    private TableColumn<Flat, Integer> colFlatBed;
    @FXML
    private TableColumn<Flat, Integer> colFlatBath;
    @FXML
    private TableColumn<Flat, Double> colFlatRent;
    @FXML
    private TableColumn<Flat, String> colFlatDescription;
    @FXML
    private TableColumn<Job, String> colJobcategory;
    @FXML
    private TableColumn<Job, String> colJobposition;
    @FXML
    private TableColumn<Job, Integer> colJobvacancy;
    @FXML
    private TableColumn<Job, String> colJobstatus;
    @FXML
    private TableColumn<Job, String> colJobqual;
    @FXML
    private TableColumn<Job, String> colJobexpe;
    @FXML
    private TableColumn<Job, String> colJoblocation;
    @FXML
    private TableColumn<Job, Double> colJobsalary;
    @FXML
    private TableColumn<Job, String> colJobdeadline;
    @FXML
    private TableColumn<Reservation, Integer> colRRentalsl;
    @FXML
    private TableColumn<Reservation, String> colRRentaldate;
    @FXML
    private TableColumn<Reservation, String> colRRentalname;
    @FXML
    private TableColumn<Reservation, String> colRRentalcheckin;
    @FXML
    private TableColumn<Reservation, String> colRRentalcheckout;
    @FXML
    private TableColumn<Reservation, Double> colRRentalamount;
    @FXML
    private TableColumn<Reservation, String> colRRentalstatus;
    @FXML
    private TableColumn<Reservation, String> colRRentalfname;
    @FXML
    private TableColumn<Reservation, String> colRRentallname;
    @FXML
    private TableColumn<Reservation, String> colRRentalemail;
    @FXML
    private TableColumn<Reservation, Long> colRRentalphone;
    @FXML
    private TableColumn<Reservation, Integer> colRHotelsl;
    @FXML
    private TableColumn<Reservation, String> colRHoteldate;
    @FXML
    private TableColumn<Reservation, String> colRHotelname;
    @FXML
    private TableColumn<Reservation, Integer> colRHotelrooms;
    @FXML
    private TableColumn<Reservation, String> colRHotelcheckin;
    @FXML
    private TableColumn<Reservation, String> colRHotelcheckout;
    @FXML
    private TableColumn<Reservation, Double> colRHotelamount;
    @FXML
    private TableColumn<Reservation, String> colRHotelstatus;
    @FXML
    private TableColumn<Reservation, String> colRHotelfname;
    @FXML
    private TableColumn<Reservation, String> colRHotellname;
    @FXML
    private TableColumn<Reservation, String> colRHotelemail;
    @FXML
    private TableColumn<Reservation, Long> colRHotelphone;
    @FXML
    private Text checkrentalreservationlabel;
    @FXML
    private Text checkhotelreservationlabel;
    @FXML
    private Text checkreservationlabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        u.getUser(User.LOGINTOGGLE);
        if (u.gettype() == 1) {
            profileheading.setText("Vendor Details");
            checkreservationlabel.setVisible(false);
            checkrentalreservationlabel.setVisible(true);
            checkhotelreservationlabel.setVisible(true);
            checkjoblistinglabel.setVisible(true);
            checkflatlistinglabel.setVisible(true);
            checkrentallistinglabel.setVisible(true);
            checkhotellistinglabel.setVisible(true);
        }
        nid.setText("ID:  " + String.valueOf(User.LOGINTOGGLE));
        name.setText("Name:  " + u.getname());
        username.setText("Username:  " + u.getusername());
        mobile.setText("Phone:  " + String.valueOf(u.getmobile()));
    }

    @FXML
    private void backIsPressed(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home1.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void earnIsPressed(MouseEvent event) throws IOException {
        Stage stage;
        stage = (Stage) logo.getScene().getWindow();
        Scene scene;
        scene = new Scene(FXMLLoader.load(getClass().getResource("earn.fxml")));
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
    private void covidIsPressed(MouseEvent event) {
    }

    @FXML
    private void checkReservationsIsPressed(MouseEvent event) {
        customerreservationtable.setDisable(false);
        customerreservationtable.setVisible(true);
        showReservationList();
    }

    @FXML
    private void checkRentalReservationsIsPressed(MouseEvent event) {
        hotelreservationtable.setVisible(false);
        joblistingtable.setVisible(false);
        flatlistingtable.setVisible(false);
        rentallistingtable.setVisible(false);
        //hotellistingtable.setVisible(false);
        rentalreservationtable.setDisable(false);
        rentalreservationtable.setVisible(true);
        showRentalReservationList();
    }

    @FXML
    private void checkHotelReservationsIsPressed(MouseEvent event) {
        rentalreservationtable.setVisible(false);
        joblistingtable.setVisible(false);
        flatlistingtable.setVisible(false);
        rentallistingtable.setVisible(false);
        //hotellistingtable.setVisible(false);
        hotelreservationtable.setDisable(false);
        hotelreservationtable.setVisible(true);
        showHotelReservationList();
    }

    @FXML
    private void checkJobListingsIsPressed(MouseEvent event) {
        customerreservationtable.setVisible(false);
        rentalreservationtable.setVisible(false);
        hotelreservationtable.setVisible(false);
        flatlistingtable.setVisible(false);
        rentallistingtable.setVisible(false);
        //hotellistingtable.setVisible(false);
        joblistingtable.setDisable(false);
        joblistingtable.setVisible(true);
        showJobList();
    }

    @FXML
    private void checkFlatListingsIsPressed(MouseEvent event) {
        customerreservationtable.setVisible(false);
        rentalreservationtable.setVisible(false);
        hotelreservationtable.setVisible(false);
        joblistingtable.setVisible(false);
        rentallistingtable.setVisible(false);
        //hotellistingtable.setVisible(false);
        flatlistingtable.setDisable(false);
        flatlistingtable.setVisible(true);
        showFlatList();
    }

    @FXML
    private void checkRentalListingsIsPressed(MouseEvent event) {
        customerreservationtable.setVisible(false);
        rentalreservationtable.setVisible(false);
        hotelreservationtable.setVisible(false);
        joblistingtable.setVisible(false);
        flatlistingtable.setVisible(false);
        //hotellistingtable.setVisible(false);
        rentallistingtable.setDisable(false);
        rentallistingtable.setVisible(true);
        showRentalList();
    }

    @FXML
    private void checkHotelListingsIsPressed(MouseEvent event) {
        customerreservationtable.setVisible(false);
        rentalreservationtable.setVisible(false);
        hotelreservationtable.setVisible(false);
        joblistingtable.setVisible(false);
        flatlistingtable.setVisible(false);
        rentallistingtable.setVisible(false);
        //hotellistingtable.setDisable(false);
        //hotellistingtable.setVisible(true);
        // showHotelList();
    }

    public ObservableList<Reservation> loadReservationList() {

        ObservableList<Reservation> reservationList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select reserveid, date, hotelroomid, rentalid, checkin, checkout, amount, payment_status from reservation where customerid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, u.getNID());
            ResultSet rs = stmt.executeQuery();
            Reservation reservation;
            String service = null, status = null;

            while (rs.next()) {
                int hr = rs.getInt(3);
                if (hr != 0) {
                    String sql2 = "select name from hotel where hid=(select hotelid from hotel_room where roomid=?)";
                    PreparedStatement stmt2 = con.prepareStatement(sql2);
                    stmt2.setInt(1, hr);
                    ResultSet rs2 = stmt2.executeQuery();
                    while (rs2.next()) {
                        service = rs2.getString(1);
                    }
                } else {
                    int r = rs.getInt(4);
                    if (r != 0) {
                        String sql2 = "select title from rental where rid=?";
                        PreparedStatement stmt2 = con.prepareStatement(sql2);
                        stmt2.setInt(1, r);
                        ResultSet rs2 = stmt2.executeQuery();

                        while (rs2.next()) {
                            service = rs2.getString(1);
                        }
                    }
                }

                if (rs.getInt(8) == 0) {
                    status = "Pending";
                } else if (rs.getInt(8) == 2) {
                    status = "Declined";
                } else {
                    status = "Completed";
                }
                reservation = new Reservation(rs.getInt("reserveid"), rs.getDate("date").toString(), service, rs.getDate("checkin").toString(), rs.getDate("checkout").toString(), rs.getDouble("amount"), status);
                reservationList.add(reservation);
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return reservationList;
    }

    private void showReservationList() {
        ObservableList<Reservation> list = loadReservationList();
        colSerial.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("reserveId"));
        colDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        colService.setCellValueFactory(new PropertyValueFactory<Reservation, String>("service"));
        colCheckin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkin"));
        colCheckout.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkout"));
        colAmount.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Reservation, String>("status"));
        customerreservationtable.setItems(list);
    }

    public ObservableList<Reservation> loadRentalReservationList() {

        ObservableList<Reservation> reservationList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();

            String sql = "select reserveid, date, rentalid, checkin, checkout, amount, payment_status, firstname, lastname, email, phone from reservation where rentalid in (select rid from rental where vendorid=?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, u.getNID());
            ResultSet rs = stmt.executeQuery();
            Reservation reservation;
            String service = null, status = null;

            while (rs.next()) {
                String sql2 = "select title from rental where rid=?";
                PreparedStatement stmt2 = con.prepareStatement(sql2);
                stmt2.setInt(1, rs.getInt(3));
                ResultSet rs2 = stmt2.executeQuery();

                while (rs2.next()) {
                    service = rs2.getString(1);
                }

                if (rs.getInt(7) == 0) {
                    status = "Pending";
                } else if (rs.getInt(7) == 2) {
                    status = "Declined";
                } else {
                    status = "Completed";
                }
                reservation = new Reservation(rs.getInt("reserveid"), rs.getDate("date").toString(), service, rs.getDate("checkin").toString(), rs.getDate("checkout").toString(), rs.getDouble("amount"), status, rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getLong("phone"));
                reservationList.add(reservation);
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return reservationList;
    }

    private void showRentalReservationList() {

        ObservableList<Reservation> list = loadRentalReservationList();

        colRRentalsl.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("reserveId"));
        colRRentaldate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        colRRentalname.setCellValueFactory(new PropertyValueFactory<Reservation, String>("service"));
        colRRentalcheckin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkin"));
        colRRentalcheckout.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkout"));
        colRRentalamount.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("amount"));
        colRRentalstatus.setCellValueFactory(new PropertyValueFactory<Reservation, String>("status"));
        colRRentalfname.setCellValueFactory(new PropertyValueFactory<Reservation, String>("firstname"));
        colRRentallname.setCellValueFactory(new PropertyValueFactory<Reservation, String>("lastname"));
        colRRentalemail.setCellValueFactory(new PropertyValueFactory<Reservation, String>("email"));
        colRRentalphone.setCellValueFactory(new PropertyValueFactory<Reservation, Long>("phone"));
        rentalreservationtable.setItems(list);
    }

    public ObservableList<Reservation> loadHotelReservationList() {

        ObservableList<Reservation> reservationList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();

            String sql = "select reserveid, date, hotelroomid, noofrooms, checkin, checkout, amount, payment_status, firstname, lastname, email, phone from reservation where hotelroomid in (select roomid from hotel_room where hotelid in (select hid from hotel where vendorid=?))";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, u.getNID());
            ResultSet rs = stmt.executeQuery();
            Reservation reservation;
            String service = null, status = null;

            while (rs.next()) {
                String sql2 = "select name from hotel where hid=(select hotelid from hotel_room where roomid=?)";
                PreparedStatement stmt2 = con.prepareStatement(sql2);
                stmt2.setInt(1, rs.getInt(3));
                ResultSet rs2 = stmt2.executeQuery();

                while (rs2.next()) {
                    service = rs2.getString(1);
                }

                if (rs.getInt(8) == 0) {
                    status = "Pending";
                } else if (rs.getInt(8) == 2) {
                    status = "Declined";
                } else {
                    status = "Completed";
                }
                reservation = new Reservation(rs.getInt("reserveid"), rs.getDate("date").toString(), service, rs.getInt("noofrooms"), rs.getDate("checkin").toString(), rs.getDate("checkout").toString(), rs.getDouble("amount"), status, rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getLong("phone"));
                reservationList.add(reservation);
            }

            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return reservationList;
    }

    private void showHotelReservationList() {

        ObservableList<Reservation> list = loadHotelReservationList();
        colRHotelsl.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("reserveId"));
        colRRentaldate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        colRRentalname.setCellValueFactory(new PropertyValueFactory<Reservation, String>("service"));
        colRHotelrooms.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("noofrooms"));
        colRRentalcheckin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkin"));
        colRRentalcheckout.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkout"));
        colRRentalamount.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("amount"));
        colRRentalstatus.setCellValueFactory(new PropertyValueFactory<Reservation, String>("status"));
        colRRentalfname.setCellValueFactory(new PropertyValueFactory<Reservation, String>("firstname"));
        colRRentallname.setCellValueFactory(new PropertyValueFactory<Reservation, String>("lastname"));
        colRRentalemail.setCellValueFactory(new PropertyValueFactory<Reservation, String>("email"));
        colRRentalphone.setCellValueFactory(new PropertyValueFactory<Reservation, Long>("phone"));
        rentalreservationtable.setItems(list);
        hotelreservationtable.setItems(list);
    }

    public ObservableList<Job> loadJobList() {

        ObservableList<Job> jobList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select jid, category, position, vacancy, status, qualification, experience, location, salary, deadline from job where vendorid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, u.getNID());
            ResultSet rs = stmt.executeQuery();
            Job job;

            String service = null, status = null;
            while (rs.next()) {

                String loc = rs.getString(8);
                if (rs.wasNull()) {
                    loc = "Work from home";
                    job = new Job(rs.getInt("jid"), rs.getString("category"), rs.getString("position"), rs.getInt("vacancy"), rs.getString("status"), rs.getString("qualification"), rs.getString("experience"), loc, rs.getDouble("salary"), rs.getDate("deadline").toString());
                    jobList.add(job);
                }
            }
            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return jobList;
    }

    private void showJobList() {

        ObservableList<Job> list = loadJobList();
        colJobcategory.setCellValueFactory(new PropertyValueFactory<Job, String>("category"));
        colJobposition.setCellValueFactory(new PropertyValueFactory<Job, String>("position"));
        colJobvacancy.setCellValueFactory(new PropertyValueFactory<Job, Integer>("vacancy"));
        colJobstatus.setCellValueFactory(new PropertyValueFactory<Job, String>("status"));
        colJobqual.setCellValueFactory(new PropertyValueFactory<Job, String>("qualification"));
        colJobexpe.setCellValueFactory(new PropertyValueFactory<Job, String>("experience"));
        colJoblocation.setCellValueFactory(new PropertyValueFactory<Job, String>("location"));
        colJobsalary.setCellValueFactory(new PropertyValueFactory<Job, Double>("salary"));
        colJobdeadline.setCellValueFactory(new PropertyValueFactory<Job, String>("deadline"));
        joblistingtable.setItems(list);
    }

    public ObservableList<Flat> loadFlatList() {

        ObservableList<Flat> flatList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select fid, name, street, area, bed, bath, description, rent from flat where vendorid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, u.getNID());
            ResultSet rs = stmt.executeQuery();
            Flat flat;

            while (rs.next()) {
                flat = new Flat(rs.getInt("fid"), rs.getString("name"), rs.getString("street"), rs.getString("area"), rs.getInt("bed"), rs.getInt("bath"), rs.getString("description"), rs.getDouble("rent"));
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
        colFlatname.setCellValueFactory(new PropertyValueFactory<Flat, String>("name"));
        colFlatStreet.setCellValueFactory(new PropertyValueFactory<Flat, String>("street"));
        colFlatArea.setCellValueFactory(new PropertyValueFactory<Flat, String>("area"));
        colFlatBed.setCellValueFactory(new PropertyValueFactory<Flat, Integer>("bed"));
        colFlatBath.setCellValueFactory(new PropertyValueFactory<Flat, Integer>("bath"));
        colFlatRent.setCellValueFactory(new PropertyValueFactory<Flat, Double>("rent"));
        colFlatDescription.setCellValueFactory(new PropertyValueFactory<Flat, String>("description"));
        flatlistingtable.setItems(list);
    }

    public ObservableList<Rental> loadRentalList() {

        ObservableList<Rental> rentalList = FXCollections.observableArrayList();

        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();

            String sql = "select rid, title, area, street, type, bed, bath, occupancy, price, rating, description from rental where vendorid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, u.getNID());
            ResultSet rs = stmt.executeQuery();
            Rental rental;

            while (rs.next()) {
                rental = new Rental(rs.getInt("RID"), rs.getString("title"), rs.getString("area"), rs.getString("street"), rs.getString("type"), rs.getInt("bed"), rs.getInt("bath"), rs.getInt("occupancy"), rs.getDouble("price"), rs.getDouble("rating"), rs.getString("description"));
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
        colRentalname.setCellValueFactory(new PropertyValueFactory<Rental, String>("name"));
        colRentalStreet.setCellValueFactory(new PropertyValueFactory<Rental, String>("street"));
        colRentalArea.setCellValueFactory(new PropertyValueFactory<Rental, String>("area"));
        colRentalType.setCellValueFactory(new PropertyValueFactory<Rental, String>("type"));
        colRentalBed.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("bed"));
        colRentalBath.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("bath"));
        colRentalOccupancy.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("guest"));
        colRentalPrice.setCellValueFactory(new PropertyValueFactory<Rental, Double>("price"));
        colRentalRating.setCellValueFactory(new PropertyValueFactory<Rental, Double>("rating"));
        colRentalDescription.setCellValueFactory(new PropertyValueFactory<Rental, String>("description"));
        rentallistingtable.setItems(list);
    }

//    public ObservableList<Hotel> loadHotelist() {
//
//        ObservableList<Hotel> hotelList = FXCollections.observableArrayList();
//
//        try {
//            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
//            Connection con = dbmsconnect.getConnection();
//            String sql = "select reserveid, date, hotelroomid, rentalid, checkin, checkout, amount, payment_status from reservation where customerid=?";
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setLong(1, u.getNID());
//            ResultSet rs = stmt.executeQuery();
//            Hotel hotel;
//
//            String service = null, status = null;
//            while (rs.next()) {
//
//                int hr = rs.getInt(3);
//                if (rs.wasNull() == false) {
//                    String sql2 = "select name from hotel where hid=(select hotelid from hotel_room where roomid=?)";
//                    PreparedStatement stmt2 = con.prepareStatement(sql2);
//                    stmt2.setInt(1, hr);
//                    ResultSet rs2 = stmt2.executeQuery();
//
//                    while (rs2.next()) {
//                        service = rs2.getString(1);
//                    }
//                } else {
//                    int r = rs.getInt(4);
//                    if (rs.wasNull() == false) {
//                        String sql2 = "select title from rental where rid=?";
//                        PreparedStatement stmt2 = con.prepareStatement(sql2);
//                        stmt2.setInt(1, r);
//                        ResultSet rs2 = stmt2.executeQuery();
//
//                        while (rs2.next()) {
//                            service = rs2.getString(1);
//                        }
//                    }
//                }
//
//                if (rs.getInt(8) == 0) {
//                    status = "Pending";
//                } else if (rs.getInt(8) == 2) {
//                    status = "Declined";
//                } else {
//                    status = "Completed";
//                }
//
//                hotel = new Hotel(rs.getInt("reserveid"), rs.getDate("date").toString(), service, rs.getDate("checkin").toString(), rs.getDate("checkout").toString(), rs.getDouble("amount"), status);
//                hotelList.add(hotel);
//            }
//
//            dbmsconnect.closeConnection(con, stmt);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return hotelList;
//    }
//
//    private void showHotelList() {
//
//        ObservableList<Hotel> list = loadHotelist();
//        colSerial.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("reserveid"));
//        colDate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
//        colService.setCellValueFactory(new PropertyValueFactory<Reservation, String>("service"));
//        colCheckin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkin"));
//        colCheckout.setCellValueFactory(new PropertyValueFactory<Reservation, String>("checkout"));
//        colAmount.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("amount"));
//        colStatus.setCellValueFactory(new PropertyValueFactory<Reservation, String>("status"));
//        hotellistingtable.setItems(list);
//    }
}
