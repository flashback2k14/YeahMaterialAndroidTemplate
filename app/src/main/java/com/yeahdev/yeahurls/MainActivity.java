package com.yeahdev.yeahurls;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.content.res.Configuration;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.*;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDrawerLayout();
        setNavigationView();
        setupToolbar();
        setupFab();
    }

    /**
     * Init Layout
     */
    private void setDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void setNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.navigation_item_login:
                        drawerLayout.closeDrawers();
                        buildSnackbar("From NavItem Login");
                        break;
                    case R.id.navigation_item_overview:
                        drawerLayout.closeDrawers();
                        buildSnackbar("From NavItem Overview");
                        break;
                }
                return false;
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupFab() {
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSnackbar("From FAB");
            }
        });
    }



    /**
     * Util Methods
     */
    private void buildSnackbar(String text) {
        Snackbar
            .make(findViewById(R.id.coordinatorLayout), text, Snackbar.LENGTH_LONG)
            .show();
    }

    /**
     * Default Activity Methods
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                buildSnackbar("From Settings");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
