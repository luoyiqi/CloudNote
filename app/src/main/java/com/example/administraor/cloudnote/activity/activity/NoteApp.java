package com.example.administraor.cloudnote.activity.activity;

import android.app.Application;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.administraor.cloudnote.activity.activity.model.Note;

/**
 * Created by Administraor on 2015/12/4.
 */
public class NoteApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //在这里定义子类化，并且初始化应用
        //<NEED>这里和mainactivity的有重复，需要后期考察
        AVObject.registerSubclass(Note.class);
        AVOSCloud.initialize(this, "iGc7sguWu7lS5TWJ4mzPxPt9", "jJphrcWcJz5wO3QMAWQwyYvq");
    }
}
