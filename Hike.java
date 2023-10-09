package com.kmd.uog.mhike.database;

public class Hike {
    public static final String ID="id";
    public static final String NAME="name";
    public static final String LOCATION="location";
    public static final String DATE="hike_date";
    public static final String PARKING="parking";
    public static final String LENGTH="length";
    public static final String DIFFICULTY="difficulty";
    public static final String DESCRIPTION="description";
    public static final String ADDITIONAL1="additional1";
    public static final String ADDITIONAL2="additional2";
    public static final String ADDITIONAL_NUM1="additionalnum1";
    public static final String ADDITIONAL_NUM2="additionalnum2";

    private Integer id;
    private String name;
    private String location;
    private String date;
    private String parking;
    private double length;
    private String difficulty;
    private String description;
    private String additional1;
    private String additional2;
    private Double additonalNum1;
    private Double addtitonalNum2;


    public Hike() {    }

    public Hike(Integer id, String name, String location, String date, String parking, double length, String difficulty, String description, String additional1, String additional2, Double additonalNum1, Double addtitonalNum2) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.parking = parking;
        this.length = length;
        this.difficulty = difficulty;
        this.description = description;
        this.additional1 = additional1;
        this.additional2 = additional2;
        this.additonalNum1 = additonalNum1;
        this.addtitonalNum2 = addtitonalNum2;
    }

    public Hike(String name, String location, String date, String parking, double length, String difficulty, String description, String additional1, String additional2, Double additonalNum1, Double addtitonalNum2) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.parking = parking;
        this.length = length;
        this.difficulty = difficulty;
        this.description = description;
        this.additional1 = additional1;
        this.additional2 = additional2;
        this.additonalNum1 = additonalNum1;
        this.addtitonalNum2 = addtitonalNum2;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditional1() {
        return additional1;
    }

    public void setAdditional1(String additional1) {
        this.additional1 = additional1;
    }

    public String getAdditional2() {
        return additional2;
    }

    public void setAdditional2(String additional2) {
        this.additional2 = additional2;
    }

    public Double getAdditonalNum1() {
        return additonalNum1;
    }

    public void setAdditonalNum1(Double additonalNum1) {
        this.additonalNum1 = additonalNum1;
    }

    public Double getAddtitonalNum2() {
        return addtitonalNum2;
    }

    public void setAddtitonalNum2(Double addtitonalNum2) {
        this.addtitonalNum2 = addtitonalNum2;
    }
}
