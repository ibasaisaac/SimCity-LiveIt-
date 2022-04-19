package com.newpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Rental {

    public static int rentalid = 0;
    private int rid;
    private String name;
    private int bed;
    private int bath;
    private int occupancy;
    private double price;
    private String area;
    private String street;
    private String type;
    private double rating;
    private String description;

    public Rental() {
    }

    public Rental(int id, String name, int bed, int bath, int occupancy, double price) {
        this.rid = id;
        this.name = name;
        this.bed = bed;
        this.bath = bath;
        this.occupancy = occupancy;
        this.price = price;
    }

    public Rental(int id, String title, String area, String street, String type, int bed, int bath, int occupancy, double price, double rating, String description) {
        this.rid = id;
        this.name = title;
        this.area = area;
        this.street = street;
        this.type = type;
        this.bed = bed;
        this.bath = bath;
        this.occupancy = occupancy;
        this.price = price;
        this.rating = rating;
        this.description = description;
    }

    public int getRentalId() {
        return rid;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getArea() {
        return area;
    }

    public String getType() {
        return type;
    }

    public int getBed() {
        return bed;
    }

    public int getBath() {
        return bath;
    }

    public int getGuest() {
        return occupancy;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public Rental getRental(long id) {
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select rid, price from rental where rid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.rid = rs.getInt(1);
                this.price = rs.getDouble(2);
            }
            dbmsconnect.closeConnection(con, stmt);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
