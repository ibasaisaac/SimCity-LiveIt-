package com.newpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

    public static long LOGINTOGGLE = 0;
    private long nid;
    private String username;
    private String password;
    private String name;
    private long mobile;

    public User() {
    }

    public User(String a, String b) {
        this.username = a;
        this.password = b;
    }

    public Long getNID() {
        return this.nid;
    }
    
    public String getname() {
        return this.name;
    }

    public User getUser(long id) {
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select * from user where nid=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.nid = rs.getLong(1);
                this.name = rs.getString(2);
                this.username = rs.getString(3);
                this.password = rs.getString(4);
                this.mobile = rs.getLong(5);
            }
            dbmsconnect.closeConnection(con, stmt);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public boolean login() {

        boolean flag = false;
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "select * from user where username=? && password=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                this.nid = rs.getLong(1);
                this.name = rs.getString(2);
                this.mobile = rs.getLong(5);
                flag = true;
            }
            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean signup(long nid, String name, long mobile) {

        boolean flag = false;
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "insert into user values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, nid);
            stmt.setString(2, name);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.setLong(5, mobile);
            int i = stmt.executeUpdate();

            if (i > 0) {
                this.nid = nid;
                this.name = name;
                this.mobile = mobile;
                flag = true;
            }
            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
