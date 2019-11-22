package com.werq.patient.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

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

    public void setBaseViewModel(BaseViewModel viewModel) {
        this.baseViewModel = viewModel;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        /*baseViewModel.getLoading().observe(this,aBoolean -> {
            if(aBoolean)
                progressDialog.show();
            else
                progressDialog.dismiss();
        });*/





    }

    @Override
    protected void onResume() {
        super.onResume();

        if(baseViewModel!=null){
            baseViewModel.getToast().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {


                    Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

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


    public void setToolbarTitle(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        Helper.setToolbar(getSupportActionBar(), title);
    }

}
