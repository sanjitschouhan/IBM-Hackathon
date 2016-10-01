

package com.ibm.mobileappbuilder.earthquakes20160930142129;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;
import ibmmobileappbuilder.cloudant.factory.CloudantDatabaseSyncerFactory;
import java.net.URI;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        //Syncing cloudant ds
        CloudantDatabaseSyncerFactory.instanceFor(
            "earthquakes",
            URI.create("https://62510789-1d05-449d-bb7f-df7134ac2187-bluemix:78d1ec54f423621b5e61cf5f0b9021d29a6f3bc7f4f67e6e5a59c0b067058daf@62510789-1d05-449d-bb7f-df7134ac2187-bluemix.cloudant.com/earthquakes")
        ).sync(null);
      }
}

