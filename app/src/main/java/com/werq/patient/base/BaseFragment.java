package com.werq.patient.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    BaseViewModel baseViewModel;
    Context mContext;


    public void setViewModel(BaseViewModel viewModel){
        this.baseViewModel=viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=getContext();

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

}
