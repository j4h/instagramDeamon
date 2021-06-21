package com.company;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileParser {

    private File file = new File("/Users/lifeinlags/IdeaProjects/instagramDeamon/src/com/company/comments.csv");
    private File fileSushi = new File("/Users/lifeinlags/IdeaProjects/instagramDeamon/src/com/company/commentsSusi.csv");


    public List<User> parseFromFile(File file) throws IOException {

        List<User> userList = new CsvToBeanBuilder(new FileReader(file))
                .withType(User.class)
                .withSkipLines(1)
                .withSeparator(';')
                .build()
                .parse();

        return userList;

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFileSushi() {
        return fileSushi;
    }

    public void setFileSushi(File fileSushi) {
        this.fileSushi = fileSushi;
    }
}





