package com.werq.patient.viewmodel;

import com.werq.patient.base.BaseViewModel;

public class FingerPrintViewModel extends BaseViewModel {

    public FingerPrintViewModel() {
    }

    public void yesOnClick()
    {
        getActivity().setValue("BottomActivity");
    }

    public  void noOnClick()
    {
        getActivity().setValue("BottomActivity");
    }
}
