package com.newpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

    public static long LOGINTOGGLE = 0;
    public static int USERTYPE = 0;
    private long nid;
    private Integer type;
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

    public Integer gettype() {
        return this.type;
    }

    
    public String getname() {
        return this.name;
    }

    public String getusername() {
        return this.username;
    }

    public Long getmobile() {
        return this.mobile;
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
                this.type = rs.getInt(2);
                this.name = rs.getString(3);
                this.username = rs.getString(4);
                this.password = rs.getString(5);
                this.mobile = rs.getLong(6);
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
                this.type = rs.getInt(2);
                this.name = rs.getString(3);
                this.mobile = rs.getLong(6);
                flag = true;
            }
            dbmsconnect.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean signup(long nid, int type, String name, long mobile) {

        boolean flag = false;
        try {
            dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/simcity", "root", "");
            Connection con = dbmsconnect.getConnection();
            String sql = "insert into user values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, nid);
            stmt.setInt(2, type);
            stmt.setString(3, name);
            stmt.setString(4, username);
            stmt.setString(5, password);
            stmt.setLong(6, mobile);
            int i = stmt.executeUpdate();

            if (i > 0) {
                this.nid = nid;
                this.type = type;
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
