package org.odk.collect.android.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.odk.collect.android.R;
import org.odk.collect.android.adapters.ViewPagerAdapter;
import org.odk.collect.android.application.Collect;

import java.util.ArrayList;
import java.util.Locale;

public class TabbedFormsFragment extends Fragment {

    private View rootView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(R.layout.tabbed_forms_layout, container, false);
        return populateTabbedFormsFragment(rootView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.downloaded_forms_menu, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        final int NEW_FORM_OPTION = 0;
        final int DELETE_FORM_OPTION = 1;
        final int REFRESH_FORM_OPTION = 2;
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        int currentTab = tabLayout.getSelectedTabPosition();
        Toast.makeText(getActivity(),
                String.format(Locale.ENGLISH, "onPrepareOptionsMenu selected, tab %d", currentTab),
                Toast.LENGTH_SHORT).show();
        if (currentTab == NEW_FORM_OPTION) {
            menu.findItem(R.id.new_form).setVisible(true);
            menu.findItem(R.id.delete_form).setVisible(true);
            menu.findItem(R.id.refresh_forms).setVisible(false);
        } else if (currentTab == DELETE_FORM_OPTION) {
            menu.findItem(R.id.new_form).setVisible(false);
            menu.findItem(R.id.delete_form).setVisible(true);
            menu.findItem(R.id.refresh_forms).setVisible(false);
        } else if (currentTab == REFRESH_FORM_OPTION) {
            menu.findItem(R.id.new_form).setVisible(false);
            menu.findItem(R.id.delete_form).setVisible(false);
            menu.findItem(R.id.refresh_forms).setVisible(true);
        }
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_navigation_action_forms:
                Toast.makeText(getActivity(), "Show forms activity Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete_form:
                Toast.makeText(getActivity(), "Delete form Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.new_form:
                Toast.makeText(getActivity(), "New form Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View populateTabbedFormsFragment(View view) {
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);

        String[] tabNames = {getString(R.string.downloaded), getString(R.string.saved), getString(R.string.sent)};
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DownloadedFormsFragment());
        fragments.add(new SavedFormsFragment());
        fragments.add(new SentFormsFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(), tabNames, fragments);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        return view;
    }
}