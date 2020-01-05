package com.alaaddin.alfyhaa.models;

public class User {

    private int _id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String adress;
    private String idintity;
    //private String userName;
    private String password;
    private String init_date;
    private String finish_date;

    public User() {
    }

    public User(String first_name, String last_name, String phone, String email, String adress, String idintity, String password, String init_date, String finish_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.adress = adress;
        this.idintity = idintity;
        this.password = password;
        this.init_date = init_date;
        this.finish_date = finish_date;
    }

    public User(int _id, String first_name, String last_name, String phone, String email, String adress, String idintity, String password, String init_date, String finish_date) {
        this._id = _id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.adress = adress;
        this.idintity = idintity;
        this.password = password;
        this.init_date = init_date;
        this.finish_date = finish_date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getIdintity() {
        return idintity;
    }

    public void setIdintity(String idintity) {
        this.idintity = idintity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", idintity='" + idintity + '\'' +
                ", password='" + password + '\'' +
                ", init_date='" + init_date + '\'' +
                ", finish_date='" + finish_date + '\'' +
                '}';
    }
}

