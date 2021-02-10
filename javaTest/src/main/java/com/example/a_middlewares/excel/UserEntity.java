package com.example.a_middlewares.excel;

import java.util.Date;

public class UserEntity {
    @ExcelCol(title = "ID")
    Integer id;
    @ExcelCol(title = "Name")
    String name;
    @ExcelCol(title = "Birthday", type = Date.class, format = "yyyy/MM/dd")
    String birthday;//Date
    @ExcelCol(title = "City")
    String companyCity;
    @ExcelCol(title = "Town")
    String companyTown;
    @ExcelCol(title = "City", index = 1)
    String homeCity;
    @ExcelCol(title = "Town", index = 1)
    String homeTown;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyTown() {
        return companyTown;
    }

    public void setCompanyTown(String companyTown) {
        this.companyTown = companyTown;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
