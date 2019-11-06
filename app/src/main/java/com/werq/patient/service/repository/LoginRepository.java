package com.werq.patient.service.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RetrofitClient;
import com.werq.patient.service.model.RequestJsonPojo.UserCredential;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.model.ResponeError.ErrorData;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class LoginRepository {


    private String TAG="LoginRepository";

    public void signIn(UserCredential signInJson, MutableLiveData<String> toast, ApiResponce apiResponce, String url){
        Call<LoginResponce>  call= RetrofitClient.getRetrofit().signIn(Helper.ContentType,signInJson);

        call.enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                Helper.setLog(TAG,"Responce code :- "+response.code());
                String errorMessage = null;
                if (response.errorBody() != null) {

                    Converter<ResponseBody, ErrorData> errorConvertor =
                            RetrofitClient.getClientOnly().responseBodyConverter(ErrorData.class, new Annotation[0]);
                    try {
                        ErrorData errorData = errorConvertor.convert(response.errorBody());
                        Log.e(TAG, "jObjError: "+errorData.toString() );
                        errorMessage=errorData.getError().getMessage();
                    }
                    catch (IOException e) {
                        Log.e(TAG, "IOException: "+e.getMessage() );
                    }
                    catch (Exception e){
                        Log.e(TAG, "Exception: "+e.getMessage() );
                    }


                }

                switch (response.code())
                {
                    case 200:
                        apiResponce.onSuccess(url,response.body());
                        break;

                    case 400:
                        if(response.errorBody()!=null && errorMessage!=null)
                        {
                            Helper.setLog(TAG,"json error :- "+errorMessage);
                            toast.setValue(errorMessage);
                        }
                        apiResponce.onError(url,"400");
                        break;

                    case 404:
                        toast.setValue("Server Not Found");
                        break;

                    default:
                        toast.setValue("Something went wrong ");
                        break;
                }

            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {

            }
        });

      //  RetrofitClient.callApi(call,url,apiResponce,toast);


    }

}
