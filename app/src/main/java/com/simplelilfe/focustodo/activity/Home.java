package com.simplelilfe.focustodo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.simplelilfe.focustodo.R;
import com.simplelilfe.focustodo.fragment.CreateTaskFragment;
import com.simplelilfe.focustodo.fragment.SettingsFragment;
import com.simplelilfe.focustodo.fragment.TasksFragment;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TasksFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, CreateTaskFragment.OnFragmentInteractionListener, View.OnClickListener {

    private static final String TAG = "Home";

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

        // Set default values for all the Settings/Preferences of the app
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // Show the first fragment as Tasks Fragment
        replaceContentFragment(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tasks) {
            replaceContentFragment(0); // Tasks
        } else if (id == R.id.nav_settings) {
            replaceContentFragment(1); // Settings
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onFragmentInteraction(String action) {

        if ("add".equalsIgnoreCase(action)) {

            /*CreateTaskFragment createTaskFragment = CreateTaskFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, createTaskFragment).commit();*/
            startActivity(new Intent(Home.this, CreateTask.class));
        }
    }

    private void replaceContentFragment(int fragmentID) {
        switch (fragmentID) {
            case 0:
                // Tasks
                TasksFragment tasksFragment = TasksFragment.newInstance(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, tasksFragment).commit();
                navigationView.setCheckedItem(R.id.nav_tasks);
                break;

            case 1:
                // Settings
                SettingsFragment settingsFragment = SettingsFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, settingsFragment).commit();
                navigationView.setCheckedItem(R.id.nav_settings);
                break;

            default:
                // TODO: 06-01-2016 handle later
        }
    }


    @Override
    public void onClick(View v) {

    }
}
