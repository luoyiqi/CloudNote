package com.example.administraor.cloudnote.activity.activity.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.example.administraor.cloudnote.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        Toolbar.OnMenuItemClickListener {
    final String TAG = "MainActivity";
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //the next is aim to initial this app for LeanCloud
        AVOSCloud.initialize(this, "Rl007aftctMKF5Pqa7N2CBLb", "gwrmJRWIpPyUBocSqGYkv8Oh");
        AVAnalytics.trackAppOpened(getIntent());
        //设置toolbar
        mToolbar.setTitle("CloudNote");
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(this);
        //设置drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addNoteBtn:
                break;
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.note_search:
                //这里加上搜索代码
                break;

            case R.id.note_notify:
                //这里加上提醒代码
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_note:
                        //<NEED>这里要写笔记本的界面，即为默认的界面
                        break;
                    case R.id.drawer_book:
                        //这里写笔记本的界面
                        break;
                    case R.id.drawer_waste:
                        //这里写废纸篓的界面
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }


    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }
}
