package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    Context mContext;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initilizevariables();
        Helper.setToolbar(getSupportActionBar(), "SignUp");
        etPin1.addTextChangedListener(new GenericTextWatcher(etPin1));
        etPin2.addTextChangedListener(new GenericTextWatcher(etPin2));
        etPin3.addTextChangedListener(new GenericTextWatcher(etPin3));
        etPin4.addTextChangedListener(new GenericTextWatcher(etPin4));
    }

    private void initilizevariables() {
        mContext = this;
    }


    @OnClick({R.id.btNext, R.id.tvLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btNext:
                startActivity(new Intent(mContext, VerifyIdentity.class));
                break;
            case R.id.tvLogin:
                startActivity(new Intent(mContext,LoginActivity.class));
                finish();
                break;
        }
    }


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
