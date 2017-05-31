package com.yonder.exercise.network;

import com.yonder.exercise.network.model.BookSearchResult;
import com.yonder.exercise.network.model.SingleBook;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by YusufMac on 29/05/17.
 */

public interface BooksApi {
    @GET("books/v1/volumes")
    Call<BookSearchResult> search(@Query("q") String search);

    @GET("books/v1/volumes/{volumeId}")
    Call<SingleBook> getItemById(@Path("volumeId") String volumeId);

}
