package com.deme.sharepic.models;

/**
 * Created by Dima on 9/16/2018.
 */

public class UseSticker {
    public final static String USE_KEY_ID = "id";
    public final static String USE_KEY_TIMESTAMP = "timestamp";
    public final static String USE_KEY_USERID = "userID";
    public String stickerId;
    public String timeStamp;
    public String userId;

    public UseSticker () {

    }
    public UseSticker (String id, String timeStamp, String userId) {
        this.stickerId = id;
        this.timeStamp = timeStamp;
        this.userId = userId;
    }


}
