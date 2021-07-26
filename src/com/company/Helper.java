package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Helper {

    private FileParser fileParser = new FileParser();


    List<User> parseUsersFromFile() {

        List<User> userArrayList = new ArrayList<>();

        try {
            userArrayList = fileParser.parseUsers(fileParser.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userArrayList;
    }

    private List<Follower> parseFollowersFromFile() {

        List<Follower> newFollowersList = new ArrayList<>();

        try {
            newFollowersList = fileParser.parseFollowers(fileParser.getFollowersList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newFollowersList;
    }

    private List<Follower> parseUpdFollowersFromFile() {

        List<Follower> updFollowersList = new ArrayList<>();

        try {
            updFollowersList = fileParser.parseFollowers(fileParser.getUpdFollowersList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updFollowersList;
    }


    //check each comment with tag to clarify if tagged user leaves comment (appears in list after tag)
    Set<String> getUniqueUserComeFromTag (List<User> userArrayList) {

        Set<String> uniqueUserComeFromTag = new HashSet<>();

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
                        uniqueUserComeFromTag.add(userArrayList.get(j).getUsername());
                    }
                }
            }
        }

        return uniqueUserComeFromTag;
    }

    List<String> getUniqueUsernamesFullList(List<User> userArrayList) {

        List<String> uniqueUserList = new ArrayList<>();

        for ( User anUserArrayList : userArrayList ) {

            if (!uniqueUserList.contains(anUserArrayList.getUsername())) {

                uniqueUserList.add(anUserArrayList.getUsername());
            }

        }

        return uniqueUserList;

    }

    //create list of comments, so we can count how many tagged users leaved comments
    List<User> getTaggedUsernamesComments(List<User> userArrayList, Set<String> uniqueUserComeFromTag) {

        List<User> taggedUsersComments = new ArrayList<>();

        for ( String aParsedUserList : uniqueUserComeFromTag ) {

            for (User user : userArrayList) {
                if (user.getUsername().equals(aParsedUserList)) {
                    taggedUsersComments.add(user);
                }
            }
        }

        return taggedUsersComments;
    }

    //create list of new followers from competition
    List<Follower> getNewFollowersList() {

        List<Follower> oldFollowers = parseFollowersFromFile();
        List<Follower> updFollowers = parseUpdFollowersFromFile();

        List<Follower> newlyFollowers = new ArrayList<>();

        for (Follower follower : updFollowers )

                if (! oldFollowers.contains(follower))
                    updFollowers.add(follower);


        return newlyFollowers;
    }

    //checking how many new followers we receive from tag mechanics
    List<String> getNewFollowersFromTag(Set<String> uniqueUserComeFromTag, List<Follower> fullNewFollowerList) {

        List<String> followerList = convertFollowersListToString(fullNewFollowerList);
        List<String> newFollowersFromTag = new ArrayList<>();

        for (String follower : uniqueUserComeFromTag ) {

            if (followerList.contains(follower))
                newFollowersFromTag.add(follower);
        }

        return newFollowersFromTag;
    }

    private List<String> convertFollowersListToString (List<Follower> followerList) {

        List<String> followersList = new ArrayList<>();

        for (Follower aFollowerList : followerList ) {

            followersList.add(aFollowerList.getUsername());
        }

        return followersList;

    }

    void printInfo (int uniqueUserComeFromTag, int uniqueUsernames, int taggedUsernameComments, int userArrayList) {


        float uniqueCommentPercent = ((float) uniqueUserComeFromTag /(float)uniqueUsernames) * 100;
        float taggedUserCommentsPercent = (float)taggedUsernameComments / (float)userArrayList * 100;
        //float newFollowersPercent = (float)newFollowersList.size() / (float)uniqueUsernames.size() * 100;
        //float newFollowersFromTagPercent = (float)newFollowersFromTag.size() / (float)newFollowersList.size() * 100;

        System.out.println("Всего написано: " + userArrayList + " комментариев");
        System.out.println("Из них написанных после отметки: " + taggedUsernameComments + " комментариев, что составляет " +
                (int)taggedUserCommentsPercent + "% от общего количества"    );
        System.out.println("Всего участников: " + uniqueUsernames + " человек");
        System.out.println("Из которых пришло по отметке: " + uniqueUserComeFromTag + " человек, что составляет " +
                (int)uniqueCommentPercent + "% от общего количества");

    /* System.out.println("За период конкурса подписалось: " + newFollowersList.size() + " человек, что составляет " +
                (int)newFollowersPercent + "% от общего количества участников");
      System.out.println("Из них пришли по отметке: " + newFollowersFromTag.size() + " человек, что составляет " +
                (int)newFollowersFromTagPercent + "% от общего количества"); */



    }
}
