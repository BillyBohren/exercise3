package com.backend.student.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.validation.constraints.NotNull;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private int idschool;

    public Student() {

    }

    public Student(String name, String lastname, int idSchool) {
        this.name = name;
        this.lastname = lastname;
        this.idschool = idSchool;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getIdSchool() {
        return idschool;
    }

    public void setIdSchool(int idSchool) {
        this.idschool = idSchool;
    }


}

