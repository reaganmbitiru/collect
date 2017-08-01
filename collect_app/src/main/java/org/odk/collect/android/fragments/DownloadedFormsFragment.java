package org.odk.collect.android.fragments;

import android.support.v4.app.Fragment;;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.odk.collect.android.R;

public class DownloadedFormsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        return inflater.inflate(R.layout.downloaded_forms_fragment, container, false);
    }
}
