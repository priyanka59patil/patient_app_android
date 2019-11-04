package com.werq.patient.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;

public class SignUpViewModel extends BaseViewModel {

    MutableLiveData<String> pin1;
    MutableLiveData<String> pin2;
    MutableLiveData<String> pin3;
    MutableLiveData<String> pin4;
    MutableLiveData<String> pin1Error;
    MutableLiveData<String> pin2Error;
    MutableLiveData<String> pin3Error;
    MutableLiveData<String> pin4Error;
    MutableLiveData<String> etFocus;
 /*   MutableLiveData<Boolean> pin2Focus;
    MutableLiveData<Boolean> pin3Focus;
    MutableLiveData<Boolean> pin4Focus;*/

    public SignUpViewModel() {
        pin1=new MutableLiveData<>();
        pin2=new MutableLiveData<>();
        pin3=new MutableLiveData<>();
        pin4=new MutableLiveData<>();
        pin1Error=new MutableLiveData<>();
        pin2Error=new MutableLiveData<>();
        pin3Error=new MutableLiveData<>();
        pin4Error=new MutableLiveData<>();
        etFocus=new MutableLiveData<>();
        etFocus.setValue("pin1");
    }

    public MutableLiveData<String> getPin1() {
        return pin1;
    }

    public MutableLiveData<String> getPin2() {
        return pin2;
    }

    public MutableLiveData<String> getPin3() {
        return pin3;
    }

    public MutableLiveData<String> getPin4() {
        return pin4;
    }

    public MutableLiveData<String> getPin1Error() {
        return pin1Error;
    }

    public MutableLiveData<String> getPin2Error() {
        return pin2Error;
    }

    public MutableLiveData<String> getPin3Error() {
        return pin3Error;
    }

    public MutableLiveData<String> getPin4Error() {
        return pin4Error;
    }

    public MutableLiveData<String> getEtFocus() {
        return etFocus;
    }

    public void nextOnClick()
    {
        if(pin1.getValue()!=null && !pin1.getValue().trim().isEmpty()
                && pin2.getValue()!=null && !pin2.getValue().trim().isEmpty()
                && pin3.getValue()!=null && !pin3.getValue().trim().isEmpty()
                && pin4.getValue()!=null && !pin4.getValue().trim().isEmpty())
        {
            getActivity().setValue("VerifyIdentity");
        }
        else {
            if(pin1.getValue()==null || pin1.getValue().trim().isEmpty() )
            {
                pin1Error.setValue("Please Enter Pin");
            }
            if(pin2.getValue()==null || pin2.getValue().trim().isEmpty() )
            {
                pin2Error.setValue("Please Enter Pin");
            }
            if(pin3.getValue()==null || pin3.getValue().trim().isEmpty() )
            {
                pin3Error.setValue("Please Enter Pin");
            }
            if(pin4.getValue()==null || pin4.getValue().trim().isEmpty() )
            {
                pin4Error.setValue("Please Enter Pin");
            }

        }

    }

    public void loginOnClick()
    {
        getActivity().setValue("Login");
    }

    public TextWatcher pin1TextWatcher=new TextWatcher() {

        private final String TAG = "in-un";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin1Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1) {
                etFocus.setValue("pin2");
            }
        }
    };

    public TextWatcher pin2TextWatcher=new TextWatcher() {

        private final String TAG = "in-un";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin2Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1)
            {
                etFocus.setValue("pin3");
            }
            else if (editable.toString().length() == 0) {
                etFocus.setValue("pin1");
            }
        }
    };
    public TextWatcher pin3TextWatcher=new TextWatcher() {

        private final String TAG = "in-un";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin3Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 1){
               etFocus.setValue("pin4");
            }
            else if (editable.toString().length() == 0)
                etFocus.setValue("pin2");
        }
    };
    public TextWatcher pin4TextWatcher=new TextWatcher() {

        private final String TAG = "in-un";
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            pin4Error.setValue(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 0)
                etFocus.setValue("pin3");
        }
    };


}
