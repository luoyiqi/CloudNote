package com.example.administraor.cloudnote.activity.activity.model;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.io.Serializable;

/**
 * Created by Administraor on 2015/12/9.
 */


@AVClassName("Note")
public class NoteBook extends AVObject implements Serializable{
    private String title;

    public NoteBook(String title){
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
