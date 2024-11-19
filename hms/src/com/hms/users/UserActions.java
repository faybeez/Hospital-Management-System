package com.hms.users;

import com.hms.ItemsService;

public interface UserActions {
    public void printActions();
    public boolean executeAction(int i) throws UnsupportedOperationException;
}
