package com.company;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class User {

    @CsvBindByPosition(position = 1)
    private String username;

    @CsvBindByPosition(position = 3)
    private String comment;

    @CsvBindByPosition(position = 0)
    private int id;

    @CsvBindByPosition(position = 2)
    private String date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", id=" + id +
                ", date='" + date + '\'' +
                '}';
    }
}
