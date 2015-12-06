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
    }
}
