package com.deme.sharepic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.deme.sharepic.models.Sticker;

import static com.deme.sharepic.database.SharePicDBHandler.RECENT_TABLE;
import static com.deme.sharepic.database.SharePicDBHandler.TIME_STUMP;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SharePic";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STICKER = "CREATE TABLE IF NOT EXISTS " + Sticker.TABLE + "("
                + Sticker.KEY_ID  + " TEXT ,"
                + Sticker.KEY_TITLE + " TEXT, "
                + Sticker.KEY_PATH + " TEXT )";

        db.execSQL(CREATE_TABLE_STICKER);

        String CREATE_TABLE_RECENT_STICKER = "CREATE TABLE IF NOT EXISTS " + RECENT_TABLE + "("
                + Sticker.KEY_ID  + " TEXT ,"
                + Sticker.KEY_TITLE + " TEXT, "
                + Sticker.KEY_PATH + " TEXT ,"
                + TIME_STUMP + " INTEGER )";

        db.execSQL(CREATE_TABLE_RECENT_STICKER);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + Sticker.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RECENT_TABLE);
        // Create tables again
        onCreate(db);

    }

}
