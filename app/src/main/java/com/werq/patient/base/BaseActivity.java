package com.werq.patient.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.views.ui.BottomTabActivity;
import com.werq.patient.views.ui.CreateAccountActivity;
import com.werq.patient.views.ui.FingerPrintActivity;
import com.werq.patient.views.ui.ForgotPasswordActivity;
import com.werq.patient.views.ui.LoginActivity;
import com.werq.patient.views.ui.SignUpActivity;
import com.werq.patient.views.ui.VerifyIdentity;

public class BaseActivity extends AppCompatActivity {
    BaseViewModel baseViewModel;
    Context mContext;
    String TAG="BaseActivity";
    public Sprite fadingCircle;

    public void setBaseViewModel(BaseViewModel viewModel) {
        this.baseViewModel = viewModel;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if(fadingCircle==null){
            fadingCircle= new Circle();
        }

        /*baseViewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean)
                progressDialog.show();
            else
                progressDialog.dismiss();
        });*/


        if(baseViewModel!=null){
            baseViewModel.getToast().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    Helper.showToast(mContext, s);
                    Helper.setLog("mutableLiveToast",s);

                }
            });

            baseViewModel.getActivity().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    switch (s) {

                        case "Login":
                            Helper.setLog(TAG,"Login");
                            startActivity(new Intent(mContext, LoginActivity.class));
                            finish();
                            break;
                        case "VerifyIdentity":
                            startActivity(new Intent(mContext, VerifyIdentity.class));
                            finish();
                            break;
                        case "CreateAccountActivity":
                            startActivity(new Intent(mContext, CreateAccountActivity.class));
                            break;
                        case "FingerPrintActivity":
                            startActivity(new Intent(mContext, FingerPrintActivity.class));
                            break;

                        /*from login intent*/
                        case "DashBoard": startActivity(new Intent(mContext, BottomTabActivity.class));
                            finish();
                            break;

                        case "ForgotPwd":startActivity(new Intent(mContext, ForgotPasswordActivity.class));
                            finish();
                            break;

                        case "SignUp":startActivity(new Intent(mContext, SignUpActivity.class));
                            finish();

                            break;
                    }
                }

            });


        }


    }

    @Override
    protected void onResume() {
        super.onResume();



    }


    public void setToolbarTitle(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        Helper.setToolbar(getSupportActionBar(), title);
    }

    public void setToolbarTitleWithBack(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        Helper.setToolbarwithBack(getSupportActionBar(), title);
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

}
