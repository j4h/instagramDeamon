package com.company;

import com.opencsv.bean.CsvBindByPosition;

public class Follower {

    @CsvBindByPosition(position = 2)
    private String username;

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }
}
