package com.werq.patient.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.Coworker;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorDetailsResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.model.ResponeError.ErrorData;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class ProfileDoctorViewModel extends BaseViewModel {

    private PatientRepository patientRepository;
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

    public MutableLiveData<ArrayList<Location>> locationsList;
    public MutableLiveData<String> practicePhoneNumber;
    public MutableLiveData<String> practiceWebUrl;
    //public MutableLiveData<String> aboutPractice;
    public MutableLiveData<String> practiceName;
    public MutableLiveData<Boolean> rvPracticesVisibility;

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
            patientRepository.getDocterDetails(authToken,doctorId,getToast(),10+"",page*10+"",apiResponce,"DoctorDetails");
        }
    }

    public ProfileDoctorViewModel() {

        Log.e("ProfileDoctorViewModel","init");

        patientRepository = new PatientRepository();
        disposable = new CompositeDisposable();
        this.patientRepository =new PatientRepository();

        doctorName=new MutableLiveData<>();
        doctorSpeciality=new MutableLiveData<>();
        about=new MutableLiveData<>();
        profileUrl=new MutableLiveData<>();
        doctorDetailsResponse=new MutableLiveData<>();
        
        coworkerList=new MutableLiveData<>();
        rvCoworkerVisibility=new MutableLiveData<>();
        locationsList=new MutableLiveData<>();
        rvPracticesVisibility=new MutableLiveData<>();
        
        practiceWebUrl=new MutableLiveData<>();
        practicePhoneNumber=new MutableLiveData<>();
       // aboutPractice=new MutableLiveData<>();
        practiceName=new MutableLiveData<>();
    }

    @Override
    public void onSuccess(String url, String responseJson) {

        getLoading().setValue(false);
        if(url!=null && !url.isEmpty()){

            if(url.equalsIgnoreCase("DoctorDetails"))
            {
                DoctorDetailsResponse detailsResponse= Helper.getGsonInstance().fromJson(responseJson,DoctorDetailsResponse.class);

                if(detailsResponse!=null && detailsResponse.getData()!=null && detailsResponse.getData().getDoctor()!=null)
                {
                    doctorDetailsResponse.setValue(detailsResponse);
                    Doctor doctor=detailsResponse.getData().getDoctor();

                    if(doctor!=null){

                        doctorName.setValue(doctor.getFirstName()+" "+doctor.getMiddleName()+doctor.getLastName());

                        if(doctor.getSpeciality()!=null)
                        {
                            doctorSpeciality.setValue(doctor.getSpeciality().getName());
                        }
                        else {
                            doctorSpeciality.setValue("Not Available");
                        }

                        if(doctor.getAboutMe()!=null && !doctor.getAboutMe().isEmpty())
                            about.setValue(doctor.getAboutMe());
                        else
                            about.setValue("Not Available");

                        if(doctor.getProfilePhoto()!=null){
                            profileUrl.setValue(doctor.getProfilePhoto());
                        }else {
                            profileUrl.setValue("");
                        }



                    }

                    if(detailsResponse.getData().getCoworker()!=null){

                        ArrayList<Coworker> coworkerArrayList= (ArrayList<Coworker>) detailsResponse.getData().getCoworker();
                        coworkerList.setValue(coworkerArrayList);
                        if(coworkerArrayList.size()>0){
                            rvCoworkerVisibility.setValue(true);
                        }else {
                            rvCoworkerVisibility.setValue(false);
                        }
                    }
                    else {
                        coworkerList.setValue(null);
                        rvCoworkerVisibility.setValue(false);
                    }

                    if(detailsResponse.getData().getLocations()!=null){

                        ArrayList<Location> locationsArrayList=(ArrayList<Location>) detailsResponse.getData().getLocations();
                        locationsList.setValue(locationsArrayList);
                        //practiceName.setValue(doctor.get);
                        if(locationsArrayList.size()>0){
                            rvPracticesVisibility.setValue(true);
                        }else {
                            rvPracticesVisibility.setValue(false);
                        }
                    }
                    else {
                        locationsList.setValue(null);
                        rvPracticesVisibility.setValue(false);
                    }

                    if(doctor.getContactInfo()!=null){

                        for (int i = 0; i < doctor.getContactInfo().size(); i++) {
                            //1 means website
                            // 2 means phone number
                            if(doctor.getContactInfo().get(i).getType()==1 ){
                                practiceWebUrl.setValue(doctor.getContactInfo().get(i).getDetails());
                            }

                            if(doctor.getContactInfo().get(i).getType()==2 ){
                                practicePhoneNumber.setValue(doctor.getContactInfo().get(i).getDetails());
                            }
                        }
                    }
                    else {
                       profileUrl.setValue("");
                       practicePhoneNumber.setValue("");
                    }
                }
                else {
                    doctorDetailsResponse.setValue(null);
                }

            }
        }

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
        Helper.setLog("onError","url-"+url+"  errorMessage-"+errorMessage );
        getToast().setValue(errorMessage);
        doctorDetailsResponse.setValue(null);
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

    public MutableLiveData<ArrayList<Location>> getLocationsList() {
        return locationsList;
    }

    public MutableLiveData<String> getPracticePhoneNumber() {
        return practicePhoneNumber;
    }

    public MutableLiveData<String> getPracticeWebUrl() {
        return practiceWebUrl;
    }


    public MutableLiveData<Boolean> getRvPracticesVisibility() {
        return rvPracticesVisibility;
    }

    public MutableLiveData<String> getPracticeName() {
        return practiceName;
    }
}
