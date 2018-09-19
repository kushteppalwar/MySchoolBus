package com.example.ardent.myschoolbus;

//import java.nio.file.attribute.UserDefinedFileAttributeView;

public class UserData {

    String name;
    String contact;
    String email;
    String password;

    public UserData(){
   /*     this.name = "";
        this.contact = "";
        this.email = "";
        this.password = "";
    */}

    public UserData(UserData usr){

        this.name = usr.name;
        this.email = usr.email;
        this.contact = usr.contact;
        this.password = usr.password;
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