
package com.ibm.mobileappbuilder.earthquakes20160930142129.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.earthquakes20160930142129.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "EaDSService" REST Service implementation
 */
public class EaDSService extends RestService<EaDSServiceRest>{

    public static EaDSService getInstance(){
          return new EaDSService();
    }

    private EaDSService() {
        super(EaDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "O0xm0IoB";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57ee795f57acb00300063f61",
                path,
                "apikey=O0xm0IoB");
    }

}

