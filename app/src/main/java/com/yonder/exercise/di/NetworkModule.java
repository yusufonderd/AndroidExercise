package com.yonder.exercise.di;


import com.yonder.exercise.network.BooksApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YusufMac on 29/05/17.
 */

@Module
public class NetworkModule {
    private static final String NAME_BASE_URL = "NAME_BASE_URL";
    private static final String BASE_URL = "https://www.googleapis.com";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    BooksApi provideBooksApi(Retrofit retrofit) {
        return retrofit.create(BooksApi.class);
    }
}