package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
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
     answer = answerer.answerQuestion(question, "Java is a programming language");
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

    @Test
    public void answerersReputationIncreasesBy10WhenAnswerIsUpVoted() {
        User voter = board.createUser("Voter");
     //answer is already answered
        //someone needs to upvote answer
        voter.upVote(answer);
        //vote should increase answer by 10
        int expectedReputation = 10;
        //confirm with assert method
        assertEquals(expectedReputation, answerer.getReputation());
    }

    @Test
    public void answerAcceptedGivesAnswer15PointReputation() throws Exception{
     //we have answer, now we need to verify if it is accepted
        //call acceptAnswer
        //yolis.acceptAnswer(Answer = alex.AnswerQ("));
        //
        questioner.acceptAnswer(answer);

        int expectedReputation = 15;

        assertEquals(expectedReputation, answerer.getReputation());

    }
}