package com.backend.school.dto;

import javax.validation.constraints.NotBlank;

public class SchoolDto {

    @NotBlank
    private String name;
    @NotBlank
    private String location;

    public SchoolDto() {}

    public SchoolDto(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
