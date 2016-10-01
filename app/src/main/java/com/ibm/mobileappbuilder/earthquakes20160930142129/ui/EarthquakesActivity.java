

package com.ibm.mobileappbuilder.earthquakes20160930142129.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.earthquakes20160930142129.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * EarthquakesActivity list activity
 */
public class EarthquakesActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.earthquakesActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return EarthquakesFragment.class;
    }

}

