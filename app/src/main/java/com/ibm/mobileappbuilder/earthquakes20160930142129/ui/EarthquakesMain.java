package com.ibm.mobileappbuilder.earthquakes20160930142129.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import ibmmobileappbuilder.ui.DrawerActivity;

import com.ibm.mobileappbuilder.earthquakes20160930142129.ds.EaDSItem;
import com.ibm.mobileappbuilder.earthquakes20160930142129.R;

public class EarthquakesMain extends DrawerActivity {

    private final SparseArray<Class<? extends Fragment>> sectionFragments = new SparseArray<>();
    {
                sectionFragments.append(R.id.entry0, EarthquakesFragment.class);
    }

    @Override
    public SparseArray<Class<? extends Fragment>> getSectionFragmentClasses() {
      return sectionFragments;
    }

}

