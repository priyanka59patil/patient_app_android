package com.werq.patient.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.werq.patient.Utils.Helper;
import com.werq.patient.views.ui.CreateAccountActivity;
import com.werq.patient.views.ui.FingerPrintActivity;
import com.werq.patient.views.ui.LoginActivity;
import com.werq.patient.views.ui.VerifyIdentity;

public class BaseActivity extends AppCompatActivity {
    BaseViewModel baseViewModel;
    Context mContext;


    public void setViewModel(BaseViewModel viewModel) {
        this.baseViewModel = viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mContext = this;

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
                        startActivity(new Intent(mContext, LoginActivity.class));
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
                }
            }

        });


    }

    public void setToolbarTitle(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        Helper.setToolbar(getSupportActionBar(), title);
    }
}
