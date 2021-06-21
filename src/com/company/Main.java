package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        InteriorParser interiorParser = new InteriorParser();

        try {
            interiorParser.parseUsernames();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
