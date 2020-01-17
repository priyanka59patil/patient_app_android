package com.werq.patient.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.views.ui.BottomTabActivity;
import com.werq.patient.views.ui.CreateAccountActivity;
import com.werq.patient.views.ui.FingerPrintActivity;
import com.werq.patient.views.ui.ForgotPasswordActivity;
import com.werq.patient.views.ui.LoginActivity;
import com.werq.patient.views.ui.SignUpActivity;
import com.werq.patient.views.ui.VerifyIdentity;

public abstract class CustomBaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
implements CustomBaseFragment.Callback{
    BaseViewModel baseViewModel;
    Context mContext;
    String TAG="BaseActivity";
    public Sprite fadingCircle;
    private String authToken;
    private T mViewDataBinding;
    private V mViewModel;


    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if(TextUtils.isEmpty(authToken)){
            SessionManager sessionManager=SessionManager.getSessionManager(mContext);
            authToken=sessionManager.getAuthToken();
            Helper.setLog("BaseActivity",authToken);
        }

        if(TextUtils.isEmpty(Helper.refreshTokenId)){
            SessionManager sessionManager=SessionManager.getSessionManager(mContext);
            Helper.refreshTokenId=sessionManager.getRefreshTokenId();
        }

        if(fadingCircle==null){
            fadingCircle= new Circle();
        }
        performDataBinding();






        if(baseViewModel!=null){
            baseViewModel.getToast().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    Helper.showToast(mContext, s);

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

    public String getAuthToken() {
        return authToken;
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

    public boolean isNetworkConnected() {
        return Helper.hasNetworkConnection(getApplicationContext());
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        baseViewModel=mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

}
