package com.kmd.uog.mhike.database;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Observation {

    public static final String ID="id";
    public static final String HIKE_ID="hike_id";
    public static final String OBSERVATION="observation";
    public static final String DATE_TIME="date_time";
    public static final String COMMENT= "comment";
    public static final String STR1= "str1";
    public static final String STR2= "str2";
    public static final String D1= "d1";
    public static final String D2= "d2";


    private Integer id;
    private Integer hikeId;
    private String observation;
    private ZonedDateTime dateTime;
    private String comment;
    private String str1;
    private String str2;
    private Double d1; //latitude
    private Double d2; //longitude

    public Observation(){}

    public Observation(Integer hikeId, String observation, ZonedDateTime dateTime, String comment, String str1, String str2, Double d1, Double d2) {
        this.hikeId = hikeId;
        this.observation = observation;
        this.dateTime = dateTime;
        this.comment = comment;
        this.str1 = str1;
        this.str2 = str2;
        this.d1 = d1;
        this.d2 = d2;
    }


    public Observation(Integer id, Integer hikeId, String observation, ZonedDateTime dateTime, String comment, String str1, String str2, Double d1, Double d2) {
        this.id = id;
        this.hikeId = hikeId;
        this.observation = observation;
        this.dateTime = dateTime;
        this.comment = comment;
        this.str1 = str1;
        this.str2 = str2;
        this.d1 = d1;
        this.d2 = d2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHikeId() {
        return hikeId;
    }

    public void setHikeId(Integer hikeId) {
        this.hikeId = hikeId;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public Double getD1() {
        return d1;
    }

    public void setD1(Double d1) {
        this.d1 = d1;
    }

    public Double getD2() {
        return d2;
    }

    public void setD2(Double d2) {
        this.d2 = d2;
    }

    public long dateTimeToSeconds()
    {
        return this.dateTime.toEpochSecond();
    }

    public static ZonedDateTime secondsToDateTime(long seconds)
    {
        return ZonedDateTime.ofInstant(
                Instant.ofEpochSecond(seconds),
                ZoneId.systemDefault());
    }
}
