package com.yonder.exercise.network;

import com.yonder.exercise.network.model.BookSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by YusufMac on 29/05/17.
 */

public interface BooksApi {
    @GET("books/v1/volumes")
    Call<BookSearchResult> search(@Query("q") String search);
}
