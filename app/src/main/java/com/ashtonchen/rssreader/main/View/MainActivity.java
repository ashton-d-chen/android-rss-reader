package com.ashtonchen.rssreader.main.view;

import android.os.Bundle;

import com.ashtonchen.rssreader.base.DrawerActivity;

public class MainActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("RSS Reader");
    }
}
