package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.service.model.ResponcejsonPojo.VisitNoteCreatedByUser;
import com.werq.patient.service.model.ResponcejsonPojo.VisitNoteResponse;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class ViewVisitNoteViewModel extends BaseViewModel {

    private static final String TAG = "ViewVisitNoteViewModel";
    private PatientRepository patientRepository;
    private CompositeDisposable disposable;

    int visibleItemCount,totalItemCount,pastVisiblesItems;
    private int listcount;
    boolean loading;
    private int page=0;
    String authToken;
    String refreshTokenId;
    ApiResponce apiResponce=this;
    private MutableLiveData<Boolean> rvAttachmentsVisibility;
    //public  MutableLiveData<ArrayList<DoctorTeamResult>> teamList;
    public MutableLiveData<String> doctorName;
    public MutableLiveData<String> speciality;
    public MutableLiveData<String> visitNoteText;
    public MutableLiveData<String> profileUrl;
    public MutableLiveData<ArrayList<AttachmentResult>> visitNoteAttachments ;
    int appointmentId;
    int visitNoteId;

    public ViewVisitNoteViewModel() {
        super();

        patientRepository = new PatientRepository();
        disposable = new CompositeDisposable();
        this.patientRepository =new PatientRepository();
        rvAttachmentsVisibility=new MutableLiveData<>();
        visitNoteAttachments=new MutableLiveData<>();
        doctorName =new MutableLiveData<>();
        speciality =new MutableLiveData<>();
        visitNoteText =new MutableLiveData<>();
        profileUrl =new MutableLiveData<>();
        speciality.setValue("Not Available");
        visitNoteText.setValue("Not Available");

    }

    @Override
    public void onSuccess(String url, String responseJson) {
        Helper.setLog("responseJson",responseJson);
        if(url!=null && url.equals("VisitNoteDetails")) {

            VisitNoteResponse visitNoteResponse= Helper.getGsonInstance().fromJson(responseJson,VisitNoteResponse.class);
            VisitNoteCreatedByUser createdByUser=visitNoteResponse.getData().getVisitNoteResult().getVisitNoteCreatedByUser();
            doctorName.setValue(createdByUser.getFirstName()+" "+createdByUser.getMiddleName()+" "+createdByUser.getLastName());
            speciality.setValue(createdByUser.getJobTitle().getTitle());
            visitNoteText.setValue(visitNoteResponse.getData().getVisitNoteResult().getNote());
            profileUrl.setValue(createdByUser.getProfilePhoto());


            ArrayList<AttachmentResult> dataArrayList=new ArrayList<>();
            dataArrayList.addAll(visitNoteResponse.getData().getVisitNoteResult().getAttachement());
            visitNoteAttachments.setValue(dataArrayList);
            if(visitNoteAttachments.getValue()!=null){

                if (visitNoteAttachments.getValue().size() > 0) {
                    rvAttachmentsVisibility.setValue(true);
                    //noVisitNote.setVisibility(View.GONE);

                } else {
                    rvAttachmentsVisibility.setValue(false);
                }

            }else {
                rvAttachmentsVisibility.setValue(false);
            }

        }

    }

    @Override
    public void onError(String url, String errorCode) {

    }

    @Override
    public void onTokenRefersh(String responseJson) {

    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public MutableLiveData<Boolean> getRvAttachmentsVisibility() {
        return rvAttachmentsVisibility;
    }

    public MutableLiveData<String> getDoctorName() {
        return doctorName;
    }

    public MutableLiveData<String> getSpeciality() {
        return speciality;
    }

    public MutableLiveData<String> getVisitNoteText() {
        return visitNoteText;
    }

    public MutableLiveData<ArrayList<AttachmentResult>> getVisitNoteAttachments() {
        return visitNoteAttachments;
    }

    public void setUrlRequest(int appointmentId, int visitNoteId) {
        this.appointmentId = appointmentId;
        this.visitNoteId = visitNoteId;
        fetchVisitNoteDetails();
    }

    public  void fetchVisitNoteDetails(){
        patientRepository.getVisitNoteDetails(authToken,appointmentId,visitNoteId,
                10+"",page*10+"",getToast(),apiResponce,"VisitNoteDetails");
    }
}
