package com.werq.patient.views.ui.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.werq.patient.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterCodeFragment extends Fragment {


    @BindView(R.id.textSignUp)
    TextView textSignUp;
    @BindView(R.id.etPin1)
    EditText etPin1;
    @BindView(R.id.etPin2)
    EditText etPin2;
    @BindView(R.id.etPin3)
    EditText etPin3;
    @BindView(R.id.etPin4)
    EditText etPin4;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.btNext)
    Button btNext;
    @BindView(R.id.Tv_already_have_account)
    TextView TvAlreadyHaveAccount;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_sign_up, container, false);
        ButterKnife.bind(this, view);
        TvAlreadyHaveAccount.setVisibility(View.GONE);
        tvLogin.setVisibility(View.GONE);
        btNext.setText(getResources().getString(R.string.label_confirm));
        textSignUp.setText(getResources().getString(R.string.add_doctor_name_static));
        etPin1.addTextChangedListener(new GenericTextWatcher(etPin1));
        etPin2.addTextChangedListener(new GenericTextWatcher(etPin2));
        etPin3.addTextChangedListener(new GenericTextWatcher(etPin3));
        etPin4.addTextChangedListener(new GenericTextWatcher(etPin4));
        return view;
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
    public void onMessageEvent(String action) {

    }

    @OnClick({R.id.btNext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btNext:
              EventBus.getDefault().post("new_doctor_team");
                break;
        }
    }

    ;


    private class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.etPin1:
                    if (text.length() == 1)
                        etPin2.requestFocus();
                    break;
                case R.id.etPin2:
                    if (text.length() == 1)
                        etPin3.requestFocus();
                    else if (text.length() == 0)
                        etPin1.requestFocus();
                    break;
                case R.id.etPin3:
                    if (text.length() == 1)
                        etPin4.requestFocus();
                    else if (text.length() == 0)
                        etPin2.requestFocus();
                    break;
                case R.id.etPin4:
                    if (text.length() == 0)
                        etPin3.requestFocus();
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

}
