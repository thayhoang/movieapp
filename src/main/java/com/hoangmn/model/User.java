package com.hoangmn.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    private String role;
    private String fullName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    public User() {

    }

    public User(String username, String password, String role, String fullName, Date dob) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.dob = dob;
    }

    public User(int id, String username, String password, String role, String fullName, Date dob) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.dob = dob;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
