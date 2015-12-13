package com.example.administraor.cloudnote.activity.activity.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administraor.cloudnote.activity.activity.db.NoteDB;
import com.example.administraor.cloudnote.activity.activity.model.Note;
import com.example.administraor.cloudnote.activity.activity.model.NoteBook;

import java.util.ArrayList;

/**
 * Created by Administraor on 2015/12/8.
 */
public class dbUtil {

    public ArrayList<Note> getNotes(NoteDB noteDB){
        ArrayList<Note> noteList=new ArrayList<>();
        SQLiteDatabase db=noteDB.getWritableDatabase();
        Cursor cursor=db.query("Note",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String contents=cursor.getString(cursor.getColumnIndex("contents"));
                noteList.add(new Note(title, contents));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return noteList;    //这里需不需要判断notelist是否为null的情况。
    }


   public ArrayList<NoteBook> getBooks(NoteDB noteDB){
       ArrayList<NoteBook> bookList=new ArrayList<>();
       SQLiteDatabase db=noteDB.getWritableDatabase();
       Cursor cursor=db.query("NoteBook",null,null,null,null,null,null);
       if(cursor.moveToFirst()){
           do{
               String title=cursor.getString(cursor.getColumnIndex("title"));
               bookList.add(new NoteBook(title));
           }while(cursor.moveToNext());
       }
       cursor.close();
       return bookList;
   }


   public  void insertNote(NoteDB noteDB,String notebook,String title,String contents){
       SQLiteDatabase db=noteDB.getWritableDatabase();
       ContentValues values=new ContentValues();
       values.put("notebook",notebook);
       values.put("title",title);
       values.put("contents",contents);
       db.insert("Note",null,values);
       values.clear();
   }


}
