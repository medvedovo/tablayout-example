package ru.medvedovo.tablayouttest.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.medvedovo.tablayouttest.data.dataclass.Response;

public interface ApiMethods {
    @GET("api.php")
    Single<Response> getData(@Query("query") String query);
}
