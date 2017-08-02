package org.odk.collect.android.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.fragments.TabbedFormsFragment;
import org.odk.collect.android.preferences.AboutPreferencesFragment;

public class TabbedFormsListActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabbed_forms_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getString(R.string.main_menu));
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_frame, new TabbedFormsFragment())
                    .commit();
        }

        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(), String.format("Menu item \"%s\" selected", item.getTitle()), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.bottom_navigation_action_about:
                Collect.getInstance()
                        .getActivityLogger()
                        .logAction(this, "onOptionsItemSelected",
                        "MENU_PREFERENCES");
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_frame, new AboutPreferencesFragment())
                        .commit();
                return true;

            case R.id.bottom_navigation_action_forms:
                Collect.getInstance()
                        .getActivityLogger()
                        .logAction(this, "onOptionsItemSelected",
                                "MENU_PREFERENCES");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_frame, new TabbedFormsFragment())
                        .commit();
        }
        return false;
    }
}
