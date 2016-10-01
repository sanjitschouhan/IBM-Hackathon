
package com.ibm.mobileappbuilder.earthquakes20160930142129.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;

public interface EaDSServiceRest{

	@GET("/app/57ee795f57acb00300063f61/r/eaDS")
	void queryEaDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<EaDSItem>> cb);

	@GET("/app/57ee795f57acb00300063f61/r/eaDS/{id}")
	void getEaDSItemById(@Path("id") String id, Callback<EaDSItem> cb);

	@DELETE("/app/57ee795f57acb00300063f61/r/eaDS/{id}")
  void deleteEaDSItemById(@Path("id") String id, Callback<EaDSItem> cb);

  @POST("/app/57ee795f57acb00300063f61/r/eaDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<EaDSItem>> cb);

  @POST("/app/57ee795f57acb00300063f61/r/eaDS")
  void createEaDSItem(@Body EaDSItem item, Callback<EaDSItem> cb);

  @PUT("/app/57ee795f57acb00300063f61/r/eaDS/{id}")
  void updateEaDSItem(@Path("id") String id, @Body EaDSItem item, Callback<EaDSItem> cb);

  @GET("/app/57ee795f57acb00300063f61/r/eaDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}

