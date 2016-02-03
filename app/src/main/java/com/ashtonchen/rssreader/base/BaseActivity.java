package com.ashtonchen.rssreader.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.ashtonchen.rssreader.R;

/**
 * Created by Ashton Chen on 16-02-03.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            getSupportFragmentManager().popBackStack();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    protected int getContentView() {
        return R.id.content_main;
    }

    public void displayFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(getContentView(), fragment).addToBackStack(null).commit();
    }
}
