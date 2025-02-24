package com.backend.student.dto;

import javax.validation.constraints.NotBlank;

public class StudentDto {

    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private int idschool;

    public StudentDto() {

    }

    public StudentDto(String name, String lastname, int idschool) {
        this.name = name;
        this.lastname = lastname;
        this.idschool = idschool;
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
