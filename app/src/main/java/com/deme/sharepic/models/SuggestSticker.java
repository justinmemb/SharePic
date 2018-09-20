package com.deme.sharepic.models;

/**
 * Created by Dima on 9/16/2018.
 */

public class SuggestSticker {
    public static final String KEY_ID = "id";
    public static final String KEY_STATUS = "status";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_SUGGESTEDWORD = "suggestedWord";

    String id;
    String status;
    String timestamp;
    String suggestedWord;

    public SuggestSticker(){

    }
    public SuggestSticker(String id, String status, String timestamp, String suggestedWord) {
        this.id = id;
        this.status = status;
        this.timestamp = timestamp;
        this.suggestedWord = suggestedWord;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getSuggestedWord() {
        return suggestedWord;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
