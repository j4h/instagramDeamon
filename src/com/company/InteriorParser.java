package com.company;

import java.util.*;

public class InteriorParser {


private Helper helper = new Helper();

private List <Comment> commentArrayList = new ArrayList<>();
private Set<String> uniqueUserComeFromTag = new HashSet<>();
private List <Comment> taggedUsernameComments = new ArrayList<>();
private List <String> uniqueUsernames = new ArrayList<>();
private List <String> newFollowersFromTag = new ArrayList<>();
private List <Follower> fullNewFollowersList = new ArrayList<>();


    void countAndShowResults() {

    //convert data from .csv file to list of objects
    parse();

    //make all counting and apply them to vars
    countDataAndFulfillVariables();

    //show results in console
    printInfo();

    }


    //parse and fulfill List with data from .csv file
    private void parse () {
        commentArrayList = helper.parseUsersFromFile();
    }


    //apply all counting functions to vars
    private void countDataAndFulfillVariables() {

    uniqueUserComeFromTag = helper.getUniqueUserComeFromTag(commentArrayList);
    uniqueUsernames = helper.getUniqueUsernamesFullList(commentArrayList);
    taggedUsernameComments = helper.getTaggedUsernamesComments(commentArrayList, uniqueUserComeFromTag);

    //fullNewFollowersList = helper.getNewFollowersList();
    //newFollowersFromTag = helper.getNewFollowersFromTag(uniqueUserComeFromTag, fullNewFollowersList);

    }


    //print info in console
    private void printInfo () {

        helper.printInfo(uniqueUserComeFromTag.size(), uniqueUsernames.size(), taggedUsernameComments.size(),
                commentArrayList.size());
    }



}
