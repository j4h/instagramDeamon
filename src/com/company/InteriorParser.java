package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InteriorParser {

private List <User> userArrayList;
private List <String> uniqueUserLeavedComment;


public List<User> parseUsernames () throws IOException {

    FileParser fileParser = new FileParser();
    userArrayList = fileParser.parseFromFile(fileParser.getFileSushi());

    uniqueUserLeavedComment = new ArrayList<>();


    for ( int i = 0; i < userArrayList.size() - 1; i++ ) {

        String comment = userArrayList.get(i).getComment();

        for (int j = i+1; j<userArrayList.size(); j++) {
            if (comment.contains(userArrayList.get(j).getUsername())) {

                List<User> sublistUsers = userArrayList.subList(0, userArrayList.indexOf(userArrayList.get(j)));
                List<String> sublistUsernames = new ArrayList<>();
                for (User user : sublistUsers) {
                    sublistUsernames.add(user.getUsername());
                }

                if (! sublistUsernames.contains(userArrayList.get(j).getUsername())) {
                    uniqueUserLeavedComment.add(userArrayList.get(j).getUsername());
                }
            }
        }

    }

    printInfo();

    return userArrayList;
}

    public List<User> parseTaggedUsernamesComments() {

        List<User> taggedUsersComments = new ArrayList<>();

        for ( String aParsedUserList : uniqueUserLeavedComment ) {

            for (User user : userArrayList) {
                if (user.getUsername().equals(aParsedUserList)) {
                    taggedUsersComments.add(user);
                }
            }
        }

        return taggedUsersComments;
    }


    public List<String> parseUniqueUsernamesLeavedComment() {

        List<String> usersLeavedComment = new ArrayList<>();

        for ( String aParsedUserList : uniqueUserLeavedComment ) {

            if (!usersLeavedComment.contains(aParsedUserList)) {

                usersLeavedComment.add(aParsedUserList);
            }
        }

        System.out.println(usersLeavedComment);

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
    List<String> uniqueUsersLeavedComment = parseUniqueUsernamesLeavedComment();
    List<User> taggedUsernamesComments = parseTaggedUsernamesComments();

    float uniqueCommentPercent = ((float)uniqueUsersLeavedComment.size()/(float)uniqueUsersList.size()) * 100;
    float taggedUserCommentsPercent = (float)taggedUsernamesComments.size() / (float)userArrayList.size() * 100;

    System.out.println("Всего написано: " + userArrayList.size() + " комментариев");
    System.out.println("Из них написанных после тэга в комменте: " + taggedUsernamesComments.size() + " комментариев, что составляет " +
        taggedUserCommentsPercent + "% от общего количества комментариев "    );
    System.out.println("Всего уникальных участников: " + uniqueUsersList.size() + " человек");
    System.out.println("Из которых пришло по тэгу в комменте: " + uniqueUsersLeavedComment.size() + " человек, что составляет " +
    uniqueCommentPercent + "% от общего количества уникальных участников");


    }




}
