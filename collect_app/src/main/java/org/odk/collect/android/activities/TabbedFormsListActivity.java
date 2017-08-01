package org.odk.collect.android.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.odk.collect.android.R;
import org.odk.collect.android.adapters.ViewPagerAdapter;

import java.util.ArrayList;

public class TabbedFormsListActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tabbed_forms_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getString(R.string.title_activity_forms));
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        String[] tabNames = {getString(R.string.downloaded), getString(R.string.saved), getString(R.string.sent)};
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DownloadedFormsFragment());
        fragments.add(new SavedFormsFragment());
        fragments.add(new SentFormsFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabNames, fragments);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

    }

}
