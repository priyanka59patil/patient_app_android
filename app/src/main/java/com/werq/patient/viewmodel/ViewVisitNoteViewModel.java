package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.Callback.ApiCallback;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.ApiResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.VisitNoteCreatedByUser;
import com.werq.patient.service.model.ResponcejsonPojo.VisitNoteDetailsData;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

public class ViewVisitNoteViewModel extends BaseViewModel {

    private static final String TAG = "ViewVisitNoteViewModel";
    private PatientRepository patientRepository;
    private CompositeDisposable disposable;

    ApiCallback apiCallback=this;
    //public  MutableLiveData<ArrayList<DoctorTeamResult>> teamList;
    public MutableLiveData<String> doctorName;
    public MutableLiveData<String> speciality;
    public MutableLiveData<String> visitNoteText;
    public MutableLiveData<String> profileUrl;
    public MutableLiveData<ArrayList<AttachmentResult>> visitNoteAttachments ;

    public ViewVisitNoteViewModel() {
        super();

        patientRepository = new PatientRepository();
        disposable = new CompositeDisposable();
        this.patientRepository =new PatientRepository();
        visitNoteAttachments=new MutableLiveData<>();
        doctorName =new MutableLiveData<>();
        speciality =new MutableLiveData<>();
        visitNoteText =new MutableLiveData<>();
        profileUrl =new MutableLiveData<>();
        speciality.setValue("Not Available");
        visitNoteText.setValue("Not Available");

    }


    @Override
    public void onSuccess(String url, Response response) {

        Helper.setLog("responseJson",response.body().toString());
        getLoading().setValue(false);
        if(url!=null && url.equals("VisitNoteDetails")) {

            ApiResponse<VisitNoteDetailsData> visitNoteResponse= (ApiResponse<VisitNoteDetailsData>) response.body();
            VisitNoteCreatedByUser createdByUser=visitNoteResponse.getData().getVisitNoteResult().getVisitNoteCreatedByUser();
            doctorName.setValue(createdByUser.getFirstName()+" "+createdByUser.getMiddleName()+" "+createdByUser.getLastName());
            speciality.setValue(createdByUser.getJobTitle().getTitle());
            visitNoteText.setValue(visitNoteResponse.getData().getVisitNoteResult().getNote());
            profileUrl.setValue(createdByUser.getProfilePhoto());


            ArrayList<AttachmentResult> dataArrayList=new ArrayList<>();
            if(visitNoteResponse.getData()!=null
                    && visitNoteResponse.getData().getVisitNoteResult()!=null
                    && visitNoteResponse.getData().getVisitNoteResult().getAttachement()!=null){

                dataArrayList.addAll(visitNoteResponse.getData().getVisitNoteResult().getAttachement());
            }

            visitNoteAttachments.setValue(dataArrayList);

        }

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(Response response) {
        getLoading().setValue(false);
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


    public  void fetchVisitNoteDetails(int page,int appointmentId,int visitNoteId){
        getLoading().setValue(true);
        patientRepository.getVisitNoteDetails(Helper.autoken,appointmentId,visitNoteId,
                10+"",page*10+"",getToast(),apiCallback,"VisitNoteDetails");
    }
}
