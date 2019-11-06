package com.werq.patient.Utils;

import android.content.Context;
import android.os.Build;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    static String TAG="RetrofitClient";

    public static Retrofit retrofit;
    public static String baseUrl="https://patient-dev.werq.com/api/Auth/";
    private static ApiInterface apiInterface;


    final static OkHttpClient okHttpClent = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            String deviceType = Build.BRAND + " - " + Build.MODEL + " - " + Build.VERSION.RELEASE + " - " + Helper.getIPAddress(true);
            Request request = chain.request().newBuilder().addHeader("User-Agent", deviceType).build();
            return chain.proceed(request);
        }
    }).readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    public static ApiInterface getRetrofit()
    {

        if(retrofit==null)
        {
            retrofit = new retrofit2.Retrofit.Builder()

                    .baseUrl(baseUrl)
                    .client(okHttpClent)

                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
            apiInterface=retrofit.create(ApiInterface.class);


        }
        return apiInterface;
    }

    public static Retrofit getClientForRxjava(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClent)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
        }
        return retrofit;
    }


    public static   void callApi(Call<Object> call, String url, ApiResponce apiResponce, MutableLiveData<String> mToast){
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                Helper.setLog(TAG,"Responce code :- "+response.code());
                Helper.setLog(TAG,"url :- "+url);

                switch (response.code()){


                    case 200:{
                        String json =Helper.getGsonInstance().toJson(response.body());
                        Helper.setLog(TAG,"json :- "+json);
                        apiResponce.onSuccess(url,json);
                    break;
                    }
                    case 400:{
                        apiResponce.onError(url,"400");
                        mToast.setValue("Something went wrong ");
                        break;
                    }
                    case 404:{
                        mToast.setValue("Server Not Found");
                        break;
                    }
                    default:{
                        mToast.setValue("Something went wrong ");
                    }


                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }


}
