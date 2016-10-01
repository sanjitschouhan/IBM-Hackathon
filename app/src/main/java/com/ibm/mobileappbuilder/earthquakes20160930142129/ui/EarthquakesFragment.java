package com.ibm.mobileappbuilder.earthquakes20160930142129.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.earthquakes20160930142129.R;
import ibmmobileappbuilder.behaviors.SearchBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.FilterUtils;
import ibmmobileappbuilder.util.ViewHolder;
import java.util.Comparator;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.earthquakes20160930142129.ds.EaDSItem;
import com.ibm.mobileappbuilder.earthquakes20160930142129.ds.EaDS;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "EarthquakesFragment" listing
 */
public class EarthquakesFragment extends ListGridFragment<EaDSItem>  {

    private Datasource<EaDSItem> datasource;


    public static EarthquakesFragment newInstance(Bundle args) {
        EarthquakesFragment fr = new EarthquakesFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBehavior(new SearchBehavior(this));
    }

    protected SearchOptions getSearchOptions() {
      SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
      searchOptionsBuilder
          .withSortAscending(true)
          .withSortColumn("place");
      return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.earthquakes_item;
    }

    @Override
    protected Datasource<EaDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = EaDS.getInstance(getSearchOptions());
      return datasource;
    }

    @Override
    protected void bindView(EaDSItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.place != null){
            title.setText(item.place);
            
        }
    }


    @Override
    public void showDetail(EaDSItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), EarthquakesDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

}

