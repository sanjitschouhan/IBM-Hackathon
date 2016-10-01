

package com.ibm.mobileappbuilder.earthquakes20160930142129.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "EaDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class EaDS extends AppNowDatasource<EaDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private EaDSService service;

    public static EaDS getInstance(SearchOptions searchOptions){
        return new EaDS(searchOptions);
    }

    private EaDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = EaDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<EaDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<EaDSItem>>() {
                @Override
                public void onSuccess(List<EaDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new EaDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getEaDSItemById(id, new Callback<EaDSItem>() {
                @Override
                public void success(EaDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<EaDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<EaDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryEaDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<EaDSItem>>() {
            @Override
            public void success(List<EaDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"place", "url"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(EaDSItem item, Listener<EaDSItem> listener) {
                          service.getServiceProxy().createEaDSItem(item, callbackFor(listener));
          }

    private Callback<EaDSItem> callbackFor(final Listener<EaDSItem> listener) {
      return new Callback<EaDSItem>() {
          @Override
          public void success(EaDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(EaDSItem item, Listener<EaDSItem> listener) {
                          service.getServiceProxy().updateEaDSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(EaDSItem item, final Listener<EaDSItem> listener) {
                service.getServiceProxy().deleteEaDSItemById(item.getIdentifiableId(), new Callback<EaDSItem>() {
            @Override
            public void success(EaDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<EaDSItem> items, final Listener<EaDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<EaDSItem>>() {
            @Override
            public void success(List<EaDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<EaDSItem> items){
        List<String> ids = new ArrayList<>();
        for(EaDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

