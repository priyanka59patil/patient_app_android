package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.Models.viewModel.LoginViewModel;
import com.werq.patient.R;
import com.werq.patient.Utils.EditTextUtils;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityLoginBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.luongvo.widget.iosswitchview.SwitchView;

;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtInputUserName)
    TextInputLayout txtInputUserName;
    @BindView(R.id.txtInputPassword)
    TextInputLayout txtInputPassword;
    /*@BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.swRememberMe)
    SwitchView swRememberMe;
    @BindView(R.id.swTouchId)
    SwitchView swTouchId;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.tvtextSignUp)
    TextView tvtextSignUp;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;

    @BindView(R.id.tvremember)
    TextView tvremember;
    @BindView(R.id.layout_signup)
    LinearLayout layoutSignup;
*/
    //context
    Context mContext;

    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        intilizevariables();
        initBinding();
        setSupportActionBar(toolbar);
        setToolbar();


    }

    private void initBinding() {
        ActivityLoginBinding activityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityLoginBinding.setLoginViewModel(loginViewModel);






    }

    @Override
    protected void onResume() {
        super.onResume();

        loginViewModel.getOpenActivity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String open) {
                switch (open){

                    case "DashBoard": startActivity(new Intent(mContext,BottomTabActivity.class));
                        break;

                    case "ForgotPwd":startActivity(new Intent(mContext,ForgotPasswordActivity.class));
                        break;

                    case "SignUp":startActivity(new Intent(mContext,SignUpActivity.class));
                        break;

                }

            }
        });

        loginViewModel.getPasswordError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                    txtInputPassword.setError(getResources().getString(R.string.error_password));
                else
                    txtInputPassword.setError(null);

            }
        });

        loginViewModel.getUserNameError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean)
                    txtInputUserName.setError(getResources().getString(R.string.error_email));
                else
                    txtInputUserName.setError(null);
            }
        });


       /* loginViewModel.getUserName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null&& s.isEmpty())
                {
                    loginViewModel.txtInputUserName.setValue(getResources().getString(R.string.error_email));
                }
                else {
                    loginViewModel.txtInputUserName.setValue("");
                }
            }
        });

        loginViewModel.getPassword().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null&& s.isEmpty())
                {
                    loginViewModel.txtInputPassword.setValue(getResources().getString(R.string.error_email));
                }
                else {
                    loginViewModel.txtInputPassword.setValue("");
                }
            }
        });*/
    }

    private void setToolbar() {
        Helper.setToolbar(getSupportActionBar(), "Log In");
    }

    private void intilizevariables() {
        mContext = this;
    }

    /*@OnClick({R.id.tvForgotPassword, R.id.tvSignUp, R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                startActivity(new Intent(mContext, ForgotPasswordActivity.class));
                break;


            case R.id.tvSignUp:
                startActivity(new Intent(mContext, SignUpActivity.class));
                break;


            case R.id.btLogin:
                if (!validation()) {
                    startActivity(new Intent(mContext, BottomTabActivity.class));
                    finish();
                }
                break;
        }
    }*/

    /*private boolean validation() {
        boolean isvalid = false;

        isvalid = EditTextUtils.isEmpty(txtInputUserName, getResources().getString(R.string.error_email));

        isvalid = EditTextUtils.isEmpty(txtInputPassword, getResources().getString(R.string.error_password));

        return isvalid;
    }*/

}
