package com.example.kbaldor.rxcontactstatus.adapters;

/**
 * Created by Prasanthi on 8/6/2016.
 */
//class to check the status of user whether online or offline
public class UserInfo {
    String userName;
    boolean isOnline;

    public UserInfo(String userName, boolean isOnline) {
        this.userName = userName;
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

}
