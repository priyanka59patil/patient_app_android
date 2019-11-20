package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.disposables.CompositeDisposable;

public class AttachmentViewModel extends BaseViewModel {

    private final AppointmentRepository appointmentRepository;
    private CompositeDisposable disposable;
    private static final String TAG = "AttachmentViewModel";

    public MutableLiveData<ArrayList<AttachmentResult>> listAttachments ;
    public MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> rvVisibility;
    boolean isFromUpcoming=true;;
   // public MutableLiveData<String> selectedAttachmentResizeUrl ;

    int visibleItemCount,totalItemCount,pastVisiblesItems;
    private int listcount;
    boolean loading;
    private int page=0;
    String authToken;
    String refreshTokenId;
    ApiResponce apiResponce=this;


    public AttachmentViewModel() {

        appointmentRepository=new AppointmentRepository();
        listAttachments=new MutableLiveData<>();
        rvVisibility=new MutableLiveData<>();
        //selectedAttachmentResizeUrl=new MutableLiveData<>();
    }

    @Override
    public void onSuccess(String url, String responseJson) {

        Helper.setLog("responseJson",responseJson);

        AttachmentResponse attachmentResponse=Helper.getGsonInstance().fromJson(responseJson,AttachmentResponse.class);

        getLoading().setValue(false);

        if(url!=null && url.equals("AllAttachments"))
        {
            listcount = attachmentResponse.getData().getCount();
            ArrayList<AttachmentResult> dataArrayList=new ArrayList<>();
            dataArrayList.addAll(attachmentResponse.getData().getResult());
            listAttachments.setValue(dataArrayList);

            if (listAttachments.getValue().size() > 0) {
                rvVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvVisibility.setValue(false);
            }
        }

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {
        getLoading().setValue(false);
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        fetchAttachments();
    }

    private void fetchAttachments() {
        getLoading().setValue(true);

        //appointmentRepository.getAttachments(authToken,"",10+"",page*10+"",getToast(),apiResponce,"AllAttachments");
    }

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public MutableLiveData<ArrayList<AttachmentResult>> getListAttachments() {
        return listAttachments;
    }

    @Override
    public MutableLiveData<Boolean> getRepoLoadError() {
        return repoLoadError;
    }
}
