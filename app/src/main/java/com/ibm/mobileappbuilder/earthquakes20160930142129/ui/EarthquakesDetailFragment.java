
package com.ibm.mobileappbuilder.earthquakes20160930142129.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.earthquakes20160930142129.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.actions.OpenUriAction;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.earthquakes20160930142129.ds.EaDSItem;
import com.ibm.mobileappbuilder.earthquakes20160930142129.ds.EaDS;

public class EarthquakesDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<EaDSItem> implements ShareBehavior.ShareListener  {

    private Datasource<EaDSItem> datasource;
    public static EarthquakesDetailFragment newInstance(Bundle args){
        EarthquakesDetailFragment fr = new EarthquakesDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public EarthquakesDetailFragment(){
        super();
    }

    @Override
    public Datasource<EaDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = EaDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.earthquakesdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final EaDSItem item, View view) {
        if (item.place != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.place);
            
        }
        if (item.magnitude != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(StringUtils.doubleToString(item.magnitude, true));
            
        }
        if (item.url != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.url);
            bindAction(view2, new OpenUriAction(
            new ActivityIntentLauncher()
            , item.url));
        }
        if (item.long_ != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.long_.toString());
            bindAction(view3, new MapsAction(
            new ActivityIntentLauncher()
            , "http://maps.google.com/maps?q=" + item.long_.toString()));
        }
    }

    @Override
    protected void onShow(EaDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }
    @Override
    public void onShare() {
        EaDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.place != null ? item.place : "" ) + "\n" +
                    (item.magnitude != null ? StringUtils.doubleToString(item.magnitude, true) : "" ) + "\n" +
                    (item.url != null ? item.url : "" ) + "\n" +
                    (item.long_ != null ? item.long_.toString() : "" ));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

