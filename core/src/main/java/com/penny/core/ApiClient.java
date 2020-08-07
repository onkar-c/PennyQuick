package com.penny.core;

import com.penny.database.CoreSharedHelper;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

  private static Retrofit retrofit;

  public static Retrofit getClient() {
    if (retrofit == null
        || !retrofit.baseUrl().toString().equals(CoreSharedHelper.getInstance().getBaseURL())) {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

      // Log okHttp
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
      httpClient
          .readTimeout(6, TimeUnit.MINUTES)
          .connectTimeout(30, TimeUnit.SECONDS)
          .writeTimeout(6, TimeUnit.MINUTES);
      // 2 Minutes for time out duration
      httpClient.addInterceptor(
          chain -> {
            Request original = chain.request();

            // Headers
            Request.Builder requestBuilder = original.newBuilder();
            if (CoreSharedHelper.getInstance().getToken() != null) {
              requestBuilder.header(
                  "Authorization", "Bearer " + CoreSharedHelper.getInstance().getToken());
            }
            requestBuilder.header("Accept", "application/json");
            Request request = requestBuilder.method(original.method(), original.body()).build();
            return chain.proceed(request);
          });

      OkHttpClient client = httpClient.addInterceptor(interceptor).build();
      retrofit =
          new Retrofit.Builder()
              .baseUrl(CoreSharedHelper.getInstance().getBaseURL())
              .client(client)
              .addConverterFactory(
                  GsonConverterFactory.create(GSONUtil.acceptGSONWithoutExposeAnnotationFields()))
              .build();
    }
    return retrofit;
  }
}
