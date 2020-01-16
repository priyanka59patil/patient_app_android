package com.werq.patient.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    BaseViewModel baseViewModel;
    Context mContext;
    public static Sprite fadingcircle;
    private String authToken;

    public void setBaseViewModel(BaseViewModel viewModel){
        this.baseViewModel=viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=getContext();
        if(TextUtils.isEmpty(authToken)){
            SessionManager sessionManager=SessionManager.getSessionManager(mContext);
            authToken=sessionManager.getAuthToken();
           // Helper.setLog("BaseFragment",authToken);
        }
        if(fadingcircle==null){
            fadingcircle=new Circle();
        }
        /*baseViewModel.getToast().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();

            }
        });*/

        /*baseViewModel.getActivity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s){

                }




            }
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();

        if(baseViewModel!=null){
            baseViewModel.getToast().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {


                    Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

                }
            });

            /*baseViewModel.getLoading().observe(this,aBoolean -> {
                if(aBoolean){
                    *//*  progressBar.setVisibility(View.VISIBLE);*//*
                    progressDialog.show();
                }
                else {
                    *//*  progressBar.setVisibility(View.GONE);*//*
                    progressDialog.hide();
                }

            });*/

        }

    }

    public void showProgressBar(ProgressBar progressBar){

        if(progressBar!=null){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar(ProgressBar progressBar){
        if(progressBar!=null){
            progressBar.setVisibility(View.GONE);
        }
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
