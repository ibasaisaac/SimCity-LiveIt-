package com.newpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Reservation {

    //public static int rentalid = 0;
    private int reserveid;
    private String servicename;
    private String date;
    private String checkin;
    private String checkout;
    private double amount;
    private String paymentstatus;
    private String firstname;
    private String lastname;
    private String email;
    private long phone;
    private int noofrooms;

    public Reservation() {
    }

    public Reservation(int id, String date, String service, String checkin, String checkout, double amount, String status) {
        this.reserveid = id;
        this.servicename = service;
        this.amount = amount;
        this.paymentstatus = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.date = date;
    }

    public Reservation(int id, String date, String service, String checkin, String checkout, double amount, String status, String firstname, String lastname, String email, long phone) {
        this.reserveid = id;
        this.servicename = service;
        this.amount = amount;
        this.paymentstatus = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public Reservation(int id, String date, String service, int noofrooms, String checkin, String checkout, double amount, String status, String firstname, String lastname, String email, long phone) {
        this.reserveid = id;
        this.servicename = service;
        this.noofrooms = noofrooms;
        this.amount = amount;
        this.paymentstatus = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public int getReserveId() {
        return reserveid;
    }

    public String getService() {
        return servicename;
    }

    public String getDate() {
        return date;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return paymentstatus;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
    public int getNoofrooms() {
        return noofrooms;
    }

    public Long getPhone() {
        return phone;
    }

}
