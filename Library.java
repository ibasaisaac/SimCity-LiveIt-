package com.newpackage;

import java.awt.image.BufferedImage;


public class Library {

    private int lid;
    private String name;
    private String area;
    private String hours;

    public Library(int id, String name, String area, String hours) {
        this.lid = id;
        this.name = name;
        this.area = area;
        this.hours = hours;
    }

    public int getLibraryId() {
        return lid;
    }

    public String getName() {
        return name;
    }
    
    public String getArea() {
        return area;
    }
    
    public String getHours() {
        return hours;
    }
}
