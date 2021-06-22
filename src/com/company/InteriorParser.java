package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InteriorParser {

public List <User> userArrayList;
public List <String> parsedUserList;
public List <String> uniqueUsernameList;


public List<User> parseUsernames () throws IOException {

    FileParser fileParser = new FileParser();
    userArrayList = fileParser.parseFromFile(fileParser.getFileSushi());

    parsedUserList = new ArrayList<>();
    uniqueUsernameList = new ArrayList<>();


    for ( int i = 0; i < userArrayList.size() - 1; i++ ) {

        String comment = userArrayList.get(i).getComment();
        int id = userArrayList.get(i).getId();

        for (int j = i+1; j<userArrayList.size() - 1; j++) {
            if (comment.contains(userArrayList.get(j).getUsername()) && id < userArrayList.get(j).getId()) {

                parsedUserList.add(userArrayList.get(j).getUsername());
            }
        }


    }

    printInfo();

    return userArrayList;
}


    public List<String> parseUniqueUsernamesLeavedAComment () {

    List<String> usersLeavedComment = new ArrayList<>();

        for ( String aParsedUserList : parsedUserList ) {
            if (!usersLeavedComment.contains(aParsedUserList)) {

                usersLeavedComment.add(aParsedUserList);
            }
        }

        return usersLeavedComment;
    }


    public List<String> parseUniqueUsernamesFullList () {

        List<String> uniqueUserList = new ArrayList<>();

        for ( User anUserArrayList : userArrayList ) {

            if (!uniqueUserList.contains(anUserArrayList.getUsername())) {

                uniqueUserList.add(anUserArrayList.getUsername());
            }

        }

        return uniqueUserList;

    }


    public void printInfo () {

    List<String> uniqueUsersList = parseUniqueUsernamesFullList();
    List<String> uniqueUsersLeavedComment = parseUniqueUsernamesLeavedAComment();

    float uniqueCommentPercent = ((float)uniqueUsersLeavedComment.size()/(float)uniqueUsersList.size()) * 100;
    float taggedUserCommentsPercent = (float)parsedUserList.size() / (float)userArrayList.size() * 100;

    System.out.println("Всего написано: " + userArrayList.size() + " комментариев");
    System.out.println("Из них написанных после тэга в комменте: " + parsedUserList.size() + " комментариев, что составляет " +
        taggedUserCommentsPercent + "% от общего количества комментариев "    );
    System.out.println("Всего уникальных участников: " + uniqueUsersList.size() + " человек");
    System.out.println("Из которых пришло по тэгу в комменте: " + uniqueUsersLeavedComment.size() + " человек, что составляет " +
    uniqueCommentPercent + "% от общего количества уникальных участников");


    }




}
