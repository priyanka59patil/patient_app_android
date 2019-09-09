package com.werq.patient.Models.viewModel;

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
