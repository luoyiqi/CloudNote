package com.example.administraor.cloudnote.activity.activity.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 这是主要的note类
 *
 * Created by Administraor on 2015/12/4.
 */

@AVClassName("Note")
public class Note extends AVObject implements Serializable{
    //以下为我想用数据库处理时的设置,先实现文本的存储，实现这个之后再实现图片的存储
    private String title;
    private String contents;
    private String noteBook;

    public Note(String title,String contents){
        this.title=title;
        this.contents=contents;
        this.noteBook=noteBook;
    }

    public String getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(String noteBook) {
        this.noteBook = noteBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    //以下为我想用云端操作时的处理

   /*
    private String title;
    private ArrayList<String> contents=new ArrayList<>();
    private ArrayList<AVFile> photos=new ArrayList<>();

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<AVFile> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<AVFile> photos) {
        this.photos = photos;
    }*/
}
