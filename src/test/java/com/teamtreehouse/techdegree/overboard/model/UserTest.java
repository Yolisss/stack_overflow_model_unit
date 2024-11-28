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
    //who answers the q
    public User answerer;
    //the q
    public Question question;
    //the one answering the q
    public Answer answer;


    //initialize Objs (class) User
 @Before
    public void setUp() throws Exception {
     board = new Board("Coding");
     questioner = board.createUser("Yolis");
     answerer = board.createUser("Alex");
     question = questioner.askQuestion("What is Java?");
     answer = questioner.answerQuestion(question, "Java is a programming language");
 }

    @Test
    public void questionerReputationIncreasesBy5WhenQuestionUpVoted() {
        // Another user upvotes the question
        User voter = board.createUser("Voter");
        voter.upVote(question);

        // Verify the questioner's reputation increased by 5 points
        int expectedReputation = 5; // Each upvote gives 5 points

        assertEquals(expectedReputation, questioner.getReputation());
    }




//    @Test
//    public void answererReputationIncreasesBy10WhenTheirAnswerIsUpvoted() {
//        //answer is upvoted by User voter
//        // Another user upvotes the question
//        User voter = board.createUser("Voter");
//        //answer is upvoted by User voter
//        voter.upVote(answerer);
//
//        // Verify the questioner's reputation increased by 5 points
//        int expectedReputation = 10; // Each upvote gives 5 points
//
//        assertEquals(expectedReputation, questioner.getReputation());
//    }
//
//    @Test
//    public void answerAcceptedGives15PointReputationBoost() {
//        questioner.acceptAnswer(answerer); //true
//
//        int expectedReputation = 15;
//
//        assertEquals(expectedReputation, questioner.getReputation());
//    }
}