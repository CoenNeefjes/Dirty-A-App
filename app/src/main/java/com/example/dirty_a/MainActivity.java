package com.example.dirty_a;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dirty_a.fragments.BalanceOverviewFragment;
import com.example.dirty_a.fragments.CookingOverviewFragment;
import com.example.dirty_a.fragments.DeviceListFragment;
import com.example.dirty_a.fragments.WelcomeFragment;
import com.example.dirty_a.settings.SharedPreferencesSettings;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the toggle action for the drawer menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set this as the itemSelectedListener for the drawer menu
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Set the email address in the navigation header of the drawer menu
        View headerView = navigationView.getHeaderView(0);
        TextView navHeaderMainEmail = headerView.findViewById(R.id.nav_header_main_email);
        navHeaderMainEmail.setText("This is an email");

        // Open the welcome fragment
        transitionFragment(new WelcomeFragment(), false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        // Close drawer when it is open
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // Check if there is an item in the back stack
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
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

    // Handle navigation view item clicks here.
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        navigateTo(id, false);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void navigateTo(int id, boolean allowedOnBackStack) {

        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new WelcomeFragment();
        } else if (id == R.id.nav_lights) {
            fragment = new DeviceListFragment();
        } else if (id == R.id.nav_cooking) {
            fragment = new CookingOverviewFragment();
        } else if (id == R.id.nav_balance) {
            fragment = new BalanceOverviewFragment();
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            logout();
        }

        transitionFragment(fragment, allowedOnBackStack);
    }

    private void transitionFragment(Fragment fragment, boolean allowedOnBackStack) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            // Add to the backStack if allowed
            if (allowedOnBackStack) {
                ft.addToBackStack(null);
            }
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void logout() {
        getSharedPreferences(SharedPreferencesSettings.PREFERENCES_KEY, MODE_PRIVATE).edit().clear().apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }


}
