package com.example.administraor.cloudnote.activity.activity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administraor.cloudnote.activity.activity.model.Note;

import java.util.ArrayList;

/**
 *
 * 这是定义的笔记的数据库处理类，在这个类中会处理笔记的增加，删除，更改，和查询
 * Created by Administraor on 2015/12/8.
 */
public class NoteDB extends SQLiteOpenHelper{

    public static NoteDB mInstance=null;
    SQLiteDatabase mDB=null;
    private Context mContext=null;
    public static final String DB_NAME="NoteStore.db";
    public static final int DB_VERSION=3;

    public static final String CREATE_NOTE="create table Note("
            + "id integer primary key autoincrement,"
            +"notebook text,"
            +"title text,"
            +"contents text)";

    public static final String CREATE_BOOK="create table NoteBook("
            + "id integer primary key autoincrement,"
            +"title text)";


    //在新建这个类的时候就已经获得了sqldatabase
    private  NoteDB(Context context) throws SQLiteException{
        super(context, DB_NAME, null, DB_VERSION);
        try{
            mDB=getWritableDatabase();
        }catch (Exception e){
            mDB=getReadableDatabase();
        }
        this.mContext=context;
    }


    public static synchronized NoteDB getInstance(Context context){
        if(mInstance==null){
            mInstance=new NoteDB(context);
        }
        return mInstance;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE);
        db.execSQL(CREATE_BOOK);
        db.execSQL("insert into Note(notebook,title,contents) values(?,?,?)",new String[]{"第一个笔记本","titletest","contenttest"});
        db.execSQL("insert into NoteBook(title) values(?)",new String[]{"第一个笔记本"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Note");
        onCreate(db);
    }


    public long deleteAllData(String tableName){
        return mDB.delete(tableName,null,null);
    }


}
