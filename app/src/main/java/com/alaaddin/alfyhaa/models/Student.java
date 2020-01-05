package com.alaaddin.alfyhaa.models;

public class Student {

    private int id ;
    private String name;
    private double phone;
    private String email;
    private int age;
    private String adress;
    private double idinty;
    private String username;
    private String course;

    public Student(String name, double phone, String email, int age, String adress, double idinty,
                   String username, String course) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.adress = adress;
        this.idinty = idinty;
        this.username = username;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPhone() {
        return phone;
    }

    public void setPhone(double phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getIdinty() {
        return idinty;
    }

    public void setIdinty(double idinty) {
        this.idinty = idinty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", adress='" + adress + '\'' +
                ", idinty=" + idinty +
                ", username='" + username + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
