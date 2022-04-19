package com.newpackage;

public class Job {

    private int jid;
    private String position;
    private String company;
    private String experience;
    private String qualification;
    private double salary;
    private String category;
    private int vacancy;
    private String status;
    private String location;
    private String deadline;

    public Job(int id, String position, String company, String experience, String qualification, double salary) {
        this.jid = id;
        this.position = position;
        this.company = company;
        this.experience = experience;
        this.qualification = qualification;
        this.salary = salary;
    }

    public Job(int id, String category, String position, int vacancy, String status, String qualification, String experience, String location, Double salary, String deadline) {
        this.jid = id;
        this.position = position;
        this.experience = experience;
        this.qualification = qualification;
        this.salary = salary;
        this.category = category;
        this.vacancy = vacancy;
        this.status = status;
        this.location = location;
        this.deadline = deadline;
    }

    public int getJobId() {
        return jid;
    }

    public String getPosition() {
        return position;
    }

    public String getCategory() {
        return category;
    }

    public int getVacancy() {
        return vacancy;
    }

    public String getCompany() {
        return company;
    }

    public String getExperience() {
        return experience;
    }

    public String getQualification() {
        return qualification;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getDeadline() {
        return deadline;
    }

    public double getSalary() {
        return salary;
    }
}
