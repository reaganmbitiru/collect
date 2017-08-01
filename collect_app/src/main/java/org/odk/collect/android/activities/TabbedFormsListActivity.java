package org.odk.collect.android.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.odk.collect.android.R;
import org.odk.collect.android.adapters.ViewPagerAdapter;
import org.odk.collect.android.fragments.DownloadedFormsFragment;
import org.odk.collect.android.fragments.SavedFormsFragment;
import org.odk.collect.android.fragments.SentFormsFragment;

import java.util.ArrayList;

public class TabbedFormsListActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tabbed_forms_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getString(R.string.title_activity_forms));
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        String[] tabNames = {getString(R.string.downloaded), getString(R.string.saved), getString(R.string.sent)};
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DownloadedFormsFragment());
        fragments.add(new SavedFormsFragment());
        fragments.add(new SentFormsFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabNames, fragments);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        BottomNavigationView mBottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Toast.makeText(getApplicationContext(), String.format("Menu \"item\" %s selected", item.getTitle()), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        int currentTab = tabLayout.getSelectedTabPosition();
        Toast.makeText(getApplicationContext(), currentTab + "", Toast.LENGTH_SHORT);
        menu.clear();
        if (currentTab == 0) {
            inflater.inflate(R.menu.downloaded_forms_menu, menu);
        } else if (currentTab == 1) {
            inflater.inflate(R.menu.saved_forms_menu, menu);
        } else if (currentTab == 2)
            inflater.inflate(R.menu.sent_forms_menu, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_forms:
                Toast.makeText(getApplicationContext(), "Show forms activity Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete_form:
                Toast.makeText(getApplicationContext(), "Delete form Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.new_form:
                Toast.makeText(getApplicationContext(), "New form Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
