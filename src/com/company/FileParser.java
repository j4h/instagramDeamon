package com.company;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileParser {

    private File file = new File("/Users/lifeinlags/IdeaProjects/instagramDeamon/src/com/company/csv/comments.csv");
    private File fileSushi = new File("/Users/lifeinlags/IdeaProjects/instagramDeamon/src/com/company/csv/commentsSusi.csv");
    private File followersList = new File("/Users/lifeinlags/IdeaProjects/instagramDeamon/src/com/company/csv/followers.csv");
    private File updFollowersList = new File("/Users/lifeinlags/IdeaProjects/instagramDeamon/src/com/company/csv/updFollowers.csv");


    List<Comment> parseComments(File file) throws IOException {

        return (List<Comment>) new CsvToBeanBuilder(new FileReader(file))
                .withType(Comment.class)
                .withSkipLines(1)
                .withSeparator(';')
                .build()
                .parse();

    }

    List<Follower> parseFollowers (File file) throws IOException {

        return (List<Follower>) new CsvToBeanBuilder(new FileReader(file))
                .withType(Follower.class)
                .withSkipLines(1)
                .withSeparator(',')
                .build()
                .parse();

    }

    File getFile() {
        return file;
    }

    File getFileSushi() {
        return fileSushi;
    }

    File getFollowersList() {
        return followersList;
    }

    File getUpdFollowersList() {
        return updFollowersList;
    }
}





