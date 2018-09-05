package com.example.ardent.myschoolbus;

//import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserData {

    String name;
    String contact;
    String email;
    String password;

    public UserData(){

    }

    public UserData(String name, String contact, String email, String password) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }

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
}