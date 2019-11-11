package com.werq.patient.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.ResponcejsonPojo.Coworker;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorData;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorDetailsResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.service.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ProfileDoctorViewModel extends BaseViewModel {

    private DoctorRepository doctorRepository;
    private CompositeDisposable disposable;
    private static final String TAG = "TabAppoinmentViewModel";

    int visibleItemCount,totalItemCount,pastVisiblesItems;
    private int listcount;
    boolean loading;
    private int page=0;
    String authToken;
    String refreshTokenId;
    ApiResponce apiResponce=this;
    int doctorId;

    public MutableLiveData<String> doctorName;
    public MutableLiveData<String> doctorSpeciality;
    public MutableLiveData<String> about;
    public MutableLiveData<String> profileUrl;

    public MutableLiveData<DoctorDetailsResponse> doctorDetailsResponse;
    public MutableLiveData<ArrayList<Coworker>> coworkerList;

    public MutableLiveData<Boolean> rvCoworkerVisibility;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;

        getDoctorDetails();
    }

    private void getDoctorDetails() {

        getLoading().setValue(true);
        if(doctorId!=0){
            doctorRepository.getDocterDetails(authToken,doctorId,getToast(),apiResponce,"DoctorDetails");
        }
    }

    public ProfileDoctorViewModel() {

        Log.e("ProfileDoctorViewModel","init");

        doctorRepository = new DoctorRepository();
        disposable = new CompositeDisposable();
        this.doctorRepository =new DoctorRepository();

        doctorName=new MutableLiveData<>();
        doctorSpeciality=new MutableLiveData<>();
        about=new MutableLiveData<>();
        profileUrl=new MutableLiveData<>();
        doctorDetailsResponse=new MutableLiveData<>();
        coworkerList=new MutableLiveData<>();
        rvCoworkerVisibility=new MutableLiveData<>();
    }

    @Override
    public void onSuccess(String url, String responseJson) {

        getLoading().setValue(false);
        if(url!=null && !url.isEmpty()){
            if(url.equalsIgnoreCase("DoctorDetails"))
            {
                DoctorDetailsResponse detailsResponse= Helper.getGsonInstance().fromJson(responseJson,DoctorDetailsResponse.class);
                Doctor doctor=detailsResponse.getData().getDoctor();

                doctorName.setValue(doctor.getFirstName()+" "+doctor.getMiddleName()+doctor.getLastName());
                //currently they dont give speciality in json
                doctorSpeciality.setValue("Dentistry, Hermatology, Cytophatology");
                about.setValue(doctor.getAboutMe());

                profileUrl.setValue(doctor.getProfilePhoto());
                doctorDetailsResponse.setValue(detailsResponse);
                ArrayList<Coworker> coworkerArrayList= (ArrayList<Coworker>) detailsResponse.getData().getCoworker();
                coworkerList.setValue(coworkerArrayList);

                if(coworkerArrayList.size()>0){
                    rvCoworkerVisibility.setValue(true);
                }else {
                    rvCoworkerVisibility.setValue(false);
                }

            }
        }

    }

    @Override
    public void onError(String url, String errorCode) {
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {
        getLoading().setValue(false);
    }

    public MutableLiveData<String> getDoctorName() {
        return doctorName;
    }

    public MutableLiveData<String> getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public MutableLiveData<String> getAbout() {
        return about;
    }

    public MutableLiveData<String> getProfileUrl() {
        return profileUrl;
    }

    public MutableLiveData<DoctorDetailsResponse> getDoctorDetailsResponse() {
        return doctorDetailsResponse;
    }

    public void setDoctorDetailsResponse(MutableLiveData<DoctorDetailsResponse> doctorDetailsResponse) {
        this.doctorDetailsResponse = doctorDetailsResponse;

    }

    public MutableLiveData<ArrayList<Coworker>> getCoworkerList() {
        return coworkerList;
    }

    public MutableLiveData<Boolean> getRvCoworkerVisibility() {
        return rvCoworkerVisibility;
    }
}
