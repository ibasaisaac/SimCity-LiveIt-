/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.newpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ISABA
 */
public class Room {

    public static int hotelroomid = 0;
    private int roomid;
    private String roomtype;
    private int noofroom;
    private int guestno;
    private int roomprice;

    private int hid;
    private String name;
    private float rating;
    private String city;
    private String street;
    private long phone;

    public Room() {
    }

    public Room(int roomid, int hid, String roomtype, int noofroom, int guestno, int roomprice, String name, float rating, String city, String street, long phone) {
        this.roomid = roomid;
        this.roomtype = roomtype;
        this.noofroom = noofroom;
        this.guestno = guestno;
        this.roomprice = roomprice;

        this.hid = hid;
        this.name = name;
        this.rating = rating;
        this.city = city;
        this.street = street;
        this.phone = phone;
    }

    public int getRoomID() {
        return roomid;
    }

    public String getRoomType() {
        return roomtype;
    }

    public void setRoomType(String rt) {
        rt = roomtype;
    }

    public int getRoomNo() {
        return noofroom;
    }

    public int getGuestNo() {
        return guestno;
    }

    public int getPrice() {
        return roomprice;
    }

    public int getHotelID() {
        return hid;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public long getPhone() {
        return phone;
    }

    public Room getRoom(long id) {
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select roomid, noofroom, roomprice from room where roomid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.roomid = rs.getInt(1);
                this.noofroom = rs.getInt(2);
                this.roomprice = rs.getInt(3);
            }
            dbmsconnect.closeConnection(con, stmt);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
