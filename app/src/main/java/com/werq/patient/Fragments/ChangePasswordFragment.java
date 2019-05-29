package com.werq.patient.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.werq.patient.R;
import com.werq.patient.Utils.EditTextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChangePasswordFragment extends Fragment {

    @BindView(R.id.etpassword)
    EditText etpassword;
    @BindView(R.id.btNext)
    Button btNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String action) {/* Do something */};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this,view);
        return view;
    }


    @OnClick({R.id.etpassword, R.id.btNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.etpassword:
                break;
            case R.id.btNext:
                if(!validation())
                EventBus.getDefault().post("Change Password");
                break;
        }
    }

    private boolean validation() {
        boolean isInValid=false;
        isInValid=EditTextUtils.isEmpty(etpassword,getActivity().getResources().getString(R.string.error_password));
    return isInValid;
    }
}
