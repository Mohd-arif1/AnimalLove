package com.dev.arif.api;

import com.dev.arif.ModelResponse.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mohdarif on 12/04/18.
 */

public interface ApiCalls {

    @GET("/v3/search/images")
    Call<ImageResponse> getImageResponse(@Query("phrase") String phrase,@Query("page") int page);

}


