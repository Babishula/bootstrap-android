package com.ssg.android.demo;

import android.os.Bundle;

import com.ssg.android.demo.fragment.SearchFragment;

public class SearchActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContent(new SearchFragment());

        setTitle(getResources().getString(R.string.search));
    }
}
