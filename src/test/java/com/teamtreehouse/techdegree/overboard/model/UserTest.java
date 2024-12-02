package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
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

        int expectedReputation = 5; // Each upvote gives 5 points

        assertEquals(expectedReputation, questioner.getReputation());
    }

    @Test
    public void answerersReputationIncreasesBy10WhenAnswerIsUpVoted() {
        User voter = board.createUser("Voter");
        voter.upVote(answer);
        //vote should increase answer by 10
        int expectedReputation = 10;
        //confirm with assert method
        assertEquals(expectedReputation, answerer.getReputation());
    }

    @Test
    public void answerAcceptedGivesAnswer15PointReputation() throws Exception{
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

    //QUESTION FOR TH: what do we do here?
    //method does not verify or give us an exception err
    @Test (expected = VotingException.class)
    public void userDownVoteTheirOwnQuestion() {
        questioner.downVote(question);
    }

    //    Attempt to have the user upvote their own answer.
    @Test (expected = VotingException.class)
    public void userUpVoteTheirOwnAnswer() {
        answerer.upVote(answer);
    }

    //ANOTHER Q FOR TH
    //METHOD DOES NOT PROVIDE EXCEPTION ERR
    //    Attempt to have the user downvote their own answer.
    @Test (expected = VotingException.class)
    public void userDownVoteTheirOwnAnswer() {
        answerer.downVote(answer);
    }

    //--------------------End of Q2/Start of Q3 ----------------------------//


    @Test (expected = AnswerAcceptanceException.class)
    public void nonAuthorAcceptingAnAnswerToQuestion(){
        answerer.acceptAnswer(answer);
    }

    @Test
    public void originalQuestionerAcceptsAnAnswer() {
        questioner.acceptAnswer(answer);

        assertTrue(answer.isAccepted());
    }
}