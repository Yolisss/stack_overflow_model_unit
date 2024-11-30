package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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


    @Rule
    public ExpectedException thrown = ExpectedException.none();

        //initialize Objs (class) User
     @Before
        public void setUp() throws Exception {
         board = new Board("Coding");
         questioner = board.createUser("Yolis");
         answerer = board.createUser("Alex");
         question = questioner.askQuestion("What is Java?");
         answer = answerer.answerQuestion(question, "Java is a programming language");
     }

 //-------------------------------Q 1 --------------------------------------
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

    //--------------------------------End of Q1/ Start of Q2-----------------------------------------------

//voting up or down is not allowed on qs or answers by orig author
    @Test (expected = VotingException.class)
    public void userUpVotingTheirOwnQuestion() {
     //since we have users and q/answer
        //we can jump straight to grabbing the upVote
        //q: yolis.upVote(Yolis ask q)
        questioner.upVote(question);
    }

    @Test
    public void differentUserCanUpVote() {
        User differentUser = board.createUser("Alex");
        int initialUpvotes = question.getUpVotes();
        differentUser.upVote(question);
        assertEquals(initialUpvotes + 1, question.getUpVotes());
    }

//    Attempt to have the user downvote their own question.

    //QUESTION FOR TH: what do we do here?
    //method does not verify or give us an exception err
    @Test //(expected = VotingException.class)
    public void userDownVoteTheirOwnQuestion() {
        //questioner = yolis
        //arg question = yolis.askQ(What is java);
        questioner.downVote(question);
    }

    //    Attempt to have the user upvote their own answer.
    @Test (expected = VotingException.class)
    public void userUpVoteTheirOwnAnswer() {

        //questioner = yolis
        //arg question = yolis.askQ(What is java);
        answerer.upVote(answer);
    }

    //ANOTHER Q FOR TH
    //METHOD DOES NOT PROVIDE EXCEPTION ERR
    //    Attempt to have the user downvote their own answer.
    @Test (expected = VotingException.class)
    public void userDownVoteTheirOwnAnswer() {

        //questioner = yolis
        //arg question = yolis.askQ(What is java);
        answerer.downVote(answer);
    }

    //--------------------End of Q2/Start of Q3 ----------------------------//

    //Write a test to make sure that only the original questioner can accept an answer.
    // Ensure the intended messaging is being sent back to the caller.
    //
    //Attempt to have a non-author user accept an answer to the question.

    @Test(expected = AnswerAcceptanceException.class)
    public void nonAuthorAcceptingAnAnswerToQuestion() throws Exception{
        answerer.acceptAnswer(answer);
    }
}