package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    //declare a field for shared use
    //creating because all classes need access to this
    private Board board;
    //who is asking the q
    public User questioner;
    //the q
    public Question question;
    //the one answering the q
    public Answer answerer;


    //initialize Objs (class) User
 @Before
    public void setUp() throws Exception {
        board = new Board("Coding");
        questioner = board.createUser("Yolis");
        question = questioner.askQuestion("What is Java?");
        answerer = questioner.answerQuestion(question,"Java is a programming language");
 }

    @Test
    public void questionerReputationIncreasesBy5WhenQuestionUpvoted() {
        // Another user upvotes the question
        User voter = board.createUser("Voter");
        voter.upVote(question);

        // Verify the questioner's reputation increased by 5 points
        int expectedReputation = 5; // Each upvote gives 5 points

        assertEquals(expectedReputation, questioner.getReputation());
    }



}