package com.ssg.android.demo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ssg.android.demo.config.AppPrefs;
import com.ssg.android.demo.misc.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main activity
 */
public class HomeActivity extends AppCompatActivity {

    /** Left menu. */
    @BindView(R.id.nav)
    NavigationView navigationView;

    /** Tool bar. */
    @BindView(R.id.header)
    Toolbar header;

    /** Drawer layout. */
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // Check authentication
        if (Utils.isNullOrEmpty(AppPrefs.get(this).getToken())) {
            redirectToLogin();
        }

        initNavView();

        // Init toolbar
        setSupportActionBar(header);

        // Add menu shortcut on header
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, drawer, header, R.string.open, R.string.close);
        drawer.addDrawerListener(abdt);
        abdt.syncState();
    }

    /**
     * Redirect to login activity for authentication.
     */
    private void redirectToLogin() {
        Intent login = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(login);
        // Force finish to avoid- back navigation
        finish();
    }

    /**
     * Initialize navigation view behaviour.
     */
    public void initNavView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_logout :
                        AppPrefs.get(HomeActivity.this).removeToken();
                        redirectToLogin();
                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.search_menu)
    public void initSearch(View v) {
        Intent search = new Intent(HomeActivity.this, SearchActivity.class);
        startActivity(search);
    }
}
