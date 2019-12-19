package com.xiayiye.honorfirst.singleinstance;

/**
 * @author xiayiye
 */
public class SinglePeople {
    private SinglePeople() {

    }

    public static SinglePeople getInstance() {
        return CreatePeople.SINGLE_PEOPLE;
    }

    private static class CreatePeople {
        private static final SinglePeople SINGLE_PEOPLE = new SinglePeople();
    }
}
