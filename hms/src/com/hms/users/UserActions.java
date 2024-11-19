package com.hms.users;

/** 
 * UserActions.java
 * 
 * interface to interact with each of the user's unique functions, hides complexity
 */
public interface UserActions {
    public void printActions();
    public boolean executeAction(int i) throws UnsupportedOperationException;
}
