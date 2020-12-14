package com.concordusa.unittestdemo.model;

public class Student {
    private String studentId;
    private String name;
    private String major;
    private double graduationYear;

    public Student() {
    }

    public Student(String studentId, String name, String major, double graduationYear) {
        super();
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.graduationYear = graduationYear;
    }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(double graduationYear) {
        this.graduationYear = graduationYear;
    }

}
