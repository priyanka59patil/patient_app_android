package com.werq.patient.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;

public abstract class CustomBaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    BaseViewModel baseViewModel;
    Context mContext;
    public static Sprite fadingcircle;
    private String authToken;
    private View mRootView;
    private T mViewDataBinding;
    private V mViewModel;
    private CustomBaseActivity mActivity;
    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

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

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CustomBaseActivity) {
            CustomBaseActivity activity = (CustomBaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();

        return mRootView;
    }

    public void setBaseViewModel(BaseViewModel viewModel){
        this.baseViewModel=viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext=getContext();
        if(TextUtils.isEmpty(authToken)){
            SessionManager sessionManager=SessionManager.getSessionManager(mContext);
            authToken=sessionManager.getAuthToken();
           // Helper.setLog("BaseFragment",authToken);
        }
        if(fadingcircle==null){
            fadingcircle=new Circle();
        }

        mViewModel = getViewModel();

        if(baseViewModel!=null){
            baseViewModel.getToast().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    Helper.showToast(mContext,s);
                    //Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    public void onResume() {
        super.onResume();



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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
