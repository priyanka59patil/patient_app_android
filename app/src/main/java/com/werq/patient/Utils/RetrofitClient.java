package com.werq.patient.Utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.werq.patient.Interfaces.ApiCallback;
import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Interfaces.ChatListApiCall;
import com.werq.patient.service.model.ResponcejsonPojo.ChatResponse;
import com.werq.patient.service.model.ResponeError.ErrorData;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
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
    public static Retrofit retrofit;
    public static String baseUrl = "https://patient-dev.werq.com/api/";
    public static Call<Object> callApi;
    public static String urlApi;
    static String TAG = "RetrofitClient";
    private static ApiInterface apiInterface;

    public static ApiInterface getRetrofit() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()

                    .baseUrl(baseUrl)
                    .client(okHttpClent)

                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
            apiInterface = retrofit.create(ApiInterface.class);


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

    public static Retrofit getClientOnly() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()

                    .baseUrl(baseUrl)
                    .client(okHttpClent)

                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
        }
        return retrofit;
    }

    public static void callApi(Call<Object> call, String url, ApiResponce apiResponce,
                               MutableLiveData<String> mToast, String refreshTokenId, long timestamp) {


        if (Helper.isTimestampExpired(timestamp)) {
            callApi = call;
            urlApi = url;

            Call<Object> refereshTkencall = getRetrofit().refreshAuthToken(refreshTokenId);

            enqueuecall(refereshTkencall, "refreshToken", apiResponce, mToast);
        } else {
            enqueuecall(call, url, apiResponce, mToast);

        }


    }

    private static void enqueuecall(Call<Object> call, String url, ApiResponce apiResponce,
                                    MutableLiveData<String> mToast) {

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                Helper.setLog(TAG, "Responce code :- " + response.code());

                String json = Helper.getGsonInstance().toJson(response.body());
                Helper.setLog(TAG, "json :- " + json);
                Helper.setLog(TAG, "url :- " + url);
                String errorMessage = null;
                if (response.errorBody() != null) {

                    Converter<ResponseBody, ErrorData> errorConvertor =
                            RetrofitClient.getClientOnly().responseBodyConverter(ErrorData.class, new Annotation[0]);
                    try {
                        ErrorData errorData = errorConvertor.convert(response.errorBody());
                        Log.e(TAG, "jObjError: " + errorData.toString());
                        errorMessage = errorData.getError().getMessage();
                    } catch (IOException e) {
                        Helper.setExceptionLog(TAG + "-IOException: ", e);
                    } catch (Exception e) {
                        Helper.setExceptionLog(TAG + "-Exception: ", e);
                    }


                }

                switch (response.code()) {

                    case 200:
                        if (url.equals("refreshToken")) {
                            apiResponce.onTokenRefersh(json);
                            enqueuecall(callApi, urlApi, apiResponce, mToast);

                        } else {
                            apiResponce.onSuccess(url, json);
                        }


                        break;

                    case 400:
                        if (response.errorBody() != null && errorMessage != null) {
                            Helper.setLog(TAG, "json error :- " + errorMessage);
                            mToast.setValue(errorMessage);
                        }
                        apiResponce.onError(url, "400", errorMessage);
                        break;

                    case 404:
                        mToast.setValue("Server Not Found");
                        break;

                    default:
                        mToast.setValue("Something went wrong ");
                        break;


                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });


    }

    public static <T> void dynamicApiCall(Call<T> call, String url, ApiCallback apiCallback,
                               MutableLiveData<String> mToast/*,String refreshTokenId,long timestamp*/) {

        TAG="dynamicApiCall";
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                Helper.setLog(TAG, "Responce code :- " + response.code());
                Helper.setLog(TAG, "Request Url :- " + call.request().url());
                String json = Helper.getGsonInstance().toJson(response.body());
                Helper.setLog(TAG, "json :- " + json);
                Helper.setLog(TAG, "url :- " + url);
                String errorMessage = null;
                if (response.errorBody() != null) {

                    Converter<ResponseBody, ErrorData> errorConvertor =
                            RetrofitClient.getClientOnly().responseBodyConverter(ErrorData.class, new Annotation[0]);
                    try {
                        ErrorData errorData = errorConvertor.convert(response.errorBody());
                        Log.e(TAG, "jObjError: " + errorData.toString());
                        errorMessage = errorData.getError().getMessage();
                    } catch (IOException e) {
                        Helper.setExceptionLog(TAG + "-IOException: ", e);
                    } catch (Exception e) {
                        Helper.setExceptionLog(TAG + "-Exception: ", e);
                    }


                }

                switch (response.code()) {

                    case 200:
                        apiCallback.onSuccess(url, response);
                        break;

                    case 400:
                        if (response.errorBody() != null && errorMessage != null) {
                            Helper.setLog(TAG, "json error :- " + errorMessage);
                            mToast.setValue(errorMessage);
                        }
                        apiCallback.onError(url, "400", errorMessage);
                        break;
                    case 500:
                        if (response.errorBody() != null && errorMessage != null) {
                            Helper.setLog(TAG, "json error :- " + errorMessage);
                            mToast.setValue(errorMessage);
                        }
                        apiCallback.onError(url, "500", errorMessage);
                        break;

                    case 404:
                        mToast.setValue("Server Not Found");
                        apiCallback.onError(url, "400", errorMessage);
                        break;

                    default:

                        mToast.setValue("Something went wrong ");
                        apiCallback.onError(url, "400", errorMessage);
                        break;

                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Helper.setLog(TAG, "Retrofit onFailure Url :- " + call.request().url());
                Helper.setLog(TAG, "Retrofit onFailure :- " + t.getMessage());
            }
        });

    }

    public static void callApi(Call<Object> call, String url, ApiResponce apiResponce,
                               MutableLiveData<String> mToast/*,String refreshTokenId,long timestamp*/) {

        TAG="regularApiCall";
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {

                Helper.setLog(TAG, "Responce code :- " + response.code());
                Helper.setLog(TAG, "Request Url :- " + call.request().url());
                String json = Helper.getGsonInstance().toJson(response.body());
                Helper.setLog(TAG, "json :- " + json);
                Helper.setLog(TAG, "url :- " + url);
                String errorMessage = null;
                if (response.errorBody() != null) {

                    Converter<ResponseBody, ErrorData> errorConvertor =
                            RetrofitClient.getClientOnly().responseBodyConverter(ErrorData.class, new Annotation[0]);
                    try {
                        ErrorData errorData = errorConvertor.convert(response.errorBody());
                        Log.e(TAG, "jObjError: " + errorData.toString());
                        errorMessage = errorData.getError().getMessage();
                    } catch (IOException e) {
                        Helper.setExceptionLog(TAG + "-IOException: ", e);
                    } catch (Exception e) {
                        Helper.setExceptionLog(TAG + "-Exception: ", e);
                    }


                }

                switch (response.code()) {

                    case 200:
                        apiResponce.onSuccess(url, json);
                        break;

                    case 400:
                        if (response.errorBody() != null && errorMessage != null) {
                            Helper.setLog(TAG, "json error :- " + errorMessage);
                            mToast.setValue(errorMessage);
                        }
                        apiResponce.onError(url, "400", errorMessage);
                        break;
                    case 500:
                        if (response.errorBody() != null && errorMessage != null) {
                            Helper.setLog(TAG, "json error :- " + errorMessage);
                            mToast.setValue(errorMessage);
                        }
                        apiResponce.onError(url, "500", errorMessage);
                        break;

                    case 404:
                        mToast.setValue("Server Not Found");
                        apiResponce.onError(url, "400", errorMessage);
                        break;

                    default:

                        mToast.setValue("Something went wrong ");
                        apiResponce.onError(url, "400", errorMessage);
                        break;


                }


            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Helper.setLog(TAG, "Retrofit onFailure Url :- " + call.request().url());
                Helper.setLog(TAG, "Retrofit onFailure :- " + t.getMessage());
            }
        });
    }

    public static void chatListCall(Call<ChatResponse> call, String url, ChatListApiCall apiResponce,
                                    MutableLiveData<String> mToast/*,String refreshTokenId,long timestamp*/) {


        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, retrofit2.Response<ChatResponse> response) {
                Helper.setLog(TAG, "Responce code :- " + response.code());
                Helper.setLog(TAG, "Request Url :- " + call.request().url());
                String json = Helper.getGsonInstance().toJson(response.body());
                Helper.setLog(TAG, "json :- " + json);
                Helper.setLog(TAG, "url :- " + url);
                String errorMessage = null;
                if (response.errorBody() != null) {

                    Converter<ResponseBody, ErrorData> errorConvertor =
                            RetrofitClient.getClientOnly().responseBodyConverter(ErrorData.class, new Annotation[0]);
                    try {
                        ErrorData errorData = errorConvertor.convert(response.errorBody());
                        Log.e(TAG, "jObjError: " + errorData.toString());
                        errorMessage = errorData.getError().getMessage();
                    } catch (IOException e) {
                        Helper.setExceptionLog(TAG + "-IOException: ", e);
                    } catch (Exception e) {
                        Helper.setExceptionLog(TAG + "-Exception: ", e);
                    }


                }

                switch (response.code()) {

                    case 200:
                        apiResponce.onChatListSuccess(url, response);
                        break;

                    case 400:
                        if (response.errorBody() != null && errorMessage != null) {
                            Helper.setLog(TAG, "json error :- " + errorMessage);
                            mToast.setValue(errorMessage);
                        }
                        apiResponce.onChatListError(url, "400", errorMessage);
                        break;
                    case 500:
                        if (response.errorBody() != null && errorMessage != null) {
                            Helper.setLog(TAG, "json error :- " + errorMessage);
                            mToast.setValue(errorMessage);
                        }
                        apiResponce.onChatListError(url, "500", errorMessage);
                        break;

                    case 404:
                        mToast.setValue("Server Not Found");
                        apiResponce.onChatListError(url, "400", errorMessage);
                        break;

                    default:

                        mToast.setValue("Something went wrong ");
                        apiResponce.onChatListError(url, "400", errorMessage);
                        break;


                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Helper.setLog(TAG, "Retrofit onFailure Url :- " + call.request().url());
                Helper.setLog(TAG, "Retrofit onFailure :- " + t.getMessage());
            }
        });


    }


}
