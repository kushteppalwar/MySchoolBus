package com.example.ardent.myschoolbus;

//import java.nio.file.attribute.UserDefinedFileAttributeView;

import java.util.HashMap;

public class UserData {

    public String name;
    public String contact;
    public String email;
    public String password;
    HashMap<Integer,StudentData> children;

    public UserData(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }



    public UserData(String name, String contact, String email, String password) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }
    public HashMap<Integer, StudentData> getChildren(){ return children;}

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}