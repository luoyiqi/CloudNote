package com.example.administraor.cloudnote.activity.activity.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.administraor.cloudnote.R;


/**
 * Created by Administraor on 2015/12/2.
 */
public class SplashScreen {
    //移动方向
    public final static int SLIDE_LEFT = 1;
    public final static int SLIDE_UP = 2;
    public final static int FADE_OUT = 3;

    private Dialog splashDialog;

    private Activity activity;

    public SplashScreen(Activity activity){
        this.activity = activity;
    }

    /*
    *这是引导页的显示。
    * @param imageResource 图片资源
    * @param animation 消失时的动画效果，取值可以是：SplashScreen.SLIDE_LEFT, SplashScreen.SLIDE_UP, SplashScreen.FADE
    * 分别对应相应的style
    *
    * */
    public void show(final int imageResource,final int animation){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                DisplayMetrics metrics = new DisplayMetrics();
                LinearLayout root = new LinearLayout(activity);
                root.setMinimumHeight(metrics.heightPixels);
                root.setMinimumWidth(metrics.widthPixels);
                root.setOrientation(LinearLayout.VERTICAL);
                root.setBackgroundColor(Color.BLACK);
                root.setBackgroundResource(imageResource);
                //create and show the dialog
                splashDialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
                //check the see if the splashScreen is full of screen
                if ((activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                        == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
                    splashDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                //set the animation of the dialog
                Window window = splashDialog.getWindow();
                switch(animation){
                    case SLIDE_LEFT:
                        window.setWindowAnimations(R.style.dialog_anim_slide_left);
                        break;
                    case SLIDE_UP:
                        window.setWindowAnimations(R.style.dialog_anim_slide_up);
                        break;
                    case FADE_OUT:
                        window.setWindowAnimations(R.style.dialog_anim_fade_out);
                        break;
                }

                splashDialog.setContentView(root);
                splashDialog.setCancelable(false);
                splashDialog.show();
            }
        };
        activity.runOnUiThread(runnable);

    }

    public void removeSplashScreen(){
        if (splashDialog != null && splashDialog.isShowing()) {
            splashDialog.dismiss();
            splashDialog = null;
        }
    }
}



























