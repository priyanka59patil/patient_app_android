package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.base.BaseViewModel;

public class VerifyIdentityViewModel extends BaseViewModel {

    MutableLiveData<String> dob;
    MutableLiveData<String> dobError;

    public VerifyIdentityViewModel() {

        dob=new MutableLiveData<>();
        dobError=new MutableLiveData<>();
    }

    public MutableLiveData<String> getDob() {
        return dob;
    }

    public MutableLiveData<String> getDobError() {
        return dobError;
    }

    public void nextOnClick()
    {
        if(dob.getValue()!=null && !dob.getValue().trim().isEmpty())
        {
            getActivity().setValue("CreateAccountActivity");
        }
        else
        {
            dobError.setValue("Please Select Dob");
        }

    }
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        dobError.setValue(null);
    }

}
