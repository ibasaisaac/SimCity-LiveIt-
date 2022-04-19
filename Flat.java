package com.newpackage;

public class Flat {

    private int fid;
    private String name;
    private int bed;
    private int bath;
    private double rent;
    private String street;
    private String area;
    private String description;

    public Flat(int id, String name, int bed, int bath, double rent) {
        this.fid = id;
        this.name = name;
        this.bed = bed;
        this.bath = bath;
        this.rent = rent;
    }

    public Flat(int id, String name, String street, String area, int bed, int bath, String description, double rent) {
        this.fid = id;
        this.name = name;
        this.street = street;
        this.area = area;
        this.bed = bed;
        this.bath = bath;
        this.description = description;
        this.rent = rent;
    }

    public int getFlatId() {
        return fid;
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

    public int getBed() {
        return bed;
    }

    public int getBath() {
        return bath;
    }

    public double getRent() {
        return rent;
    }

    public String getDescription() {
        return description;
    }
}
