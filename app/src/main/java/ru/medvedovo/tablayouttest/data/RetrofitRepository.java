package ru.medvedovo.tablayouttest.data;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public enum RetrofitRepository {
    INSTANCE;

    public static ApiMethods getApi() {
        return INSTANCE.api;
    }

    private ApiMethods api = new Retrofit.Builder()
            .baseUrl("http://kot3.com/xim/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(ApiMethods.class);
}
