package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    private InteriorParser interiorParser = new InteriorParser();


    public List<User> users() {

        List<User> userList = new ArrayList<>();

        try {
          userList  = interiorParser.parseUsernames();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return userList;
    }


}
