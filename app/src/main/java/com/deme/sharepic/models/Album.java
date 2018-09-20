package com.deme.sharepic.models;

/**
 * Created by Dima on 9/16/2018.
 */

public class Album {
    public String id;
    public String name;
    public String filePaht;
    public long coverID;
    public int count;
    public String thumbFilePath;

    public Album(){
        this.id = "";
        this.name = "";
        this.filePaht = "";
        this.coverID = 0;
        this.count = 1;
        this.thumbFilePath = "";
    }
}
