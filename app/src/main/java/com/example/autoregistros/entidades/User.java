package com.example.autoregistros.entidades;

public class User {

    private Integer id_usuario;
    private String user_name;
    private String password;
    private String email_address;
    private String date_of_birth;

    public User() {
    }

    public User(Integer id_usuario, String user_name, String password, String email_address, String date_of_birth) {
        this.id_usuario = id_usuario;
        this.user_name = user_name;
        this.password = password;
        this.email_address = email_address;
        this.date_of_birth = date_of_birth;
    }

    public User(String user_name, String password, String email_address, String date_of_birth) {
        this.user_name = user_name;
        this.password = password;
        this.email_address = email_address;
        this.date_of_birth = date_of_birth;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_adress() {
        return email_address;
    }

    public void setEmail_adress(String email_adress) {
        this.email_address = email_adress;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_usuario=" + id_usuario +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", email_address='" + email_address + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
    }
}
