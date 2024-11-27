package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;

import static org.junit.Assert.*;

public class UserTest {
    //declare a field for shared use
    public User user;
    private Board board;

    //initialize Objs (class) User
 @Before
    public void setUp() throws Exception{
        Board board = new Board("Random Topic");
        user = new User(board, "Yolis");
    }
}