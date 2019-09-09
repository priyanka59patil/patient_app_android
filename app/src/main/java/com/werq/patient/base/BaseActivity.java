package com.werq.patient.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.werq.patient.Utils.Helper;

public class BaseActivity extends AppCompatActivity {
    BaseViewModel baseViewModel;
    Context mContext;


    public void setViewModel(BaseViewModel viewModel){
        this.baseViewModel=viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mContext=this;

        baseViewModel.getToast().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();

            }
        });

        baseViewModel.getActivity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s){

                }




            }
        });


    }
}
