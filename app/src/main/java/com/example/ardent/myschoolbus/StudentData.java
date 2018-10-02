package com.example.ardent.myschoolbus;

import java.util.HashMap;

public class StudentData {

    String name;
    String id;

    public StudentData() {
    }

    public StudentData(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public StudentData(StudentData studentData){
        this.name = studentData.name;
        this.id = studentData.id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
