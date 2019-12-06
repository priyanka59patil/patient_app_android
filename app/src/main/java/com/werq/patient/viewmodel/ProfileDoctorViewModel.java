package com.werq.patient.viewmodel;

import android.text.TextUtils;
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

    ApiResponce apiResponce=this;
    int doctorId;

    public MutableLiveData<String> doctorName;
    public MutableLiveData<String> doctorSpeciality;
    public MutableLiveData<String> about;
    public MutableLiveData<String> profileUrl;

    public MutableLiveData<DoctorDetailsResponse> doctorDetailsResponse;
    public MutableLiveData<ArrayList<Coworker>> coworkerList;

    public MutableLiveData<ArrayList<Location>> locationsList;
    public MutableLiveData<String> practicePhoneNumber;
    public MutableLiveData<String> practiceWebUrl;
    //public MutableLiveData<String> aboutPractice;
    public MutableLiveData<String> practiceName;
    public MutableLiveData<Boolean> rvPracticesVisibility;
    public MutableLiveData<Boolean> coworkerLoading;

    int coworkerPageNo=0;



    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;

        getDoctorDetails(0);
    }

    public void getDoctorDetails( int page) {


        if(doctorId!=0){
            coworkerPageNo=page;
            if(coworkerPageNo!=0){
                coworkerLoading.setValue(true);
            }else
            getLoading().setValue(true);
            patientRepository.getDocterDetails(Helper.autoken,doctorId,getToast(),"10",page*10+"",apiResponce,"DoctorDetails");
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
        locationsList=new MutableLiveData<>();
        rvPracticesVisibility=new MutableLiveData<>();

        practiceWebUrl=new MutableLiveData<>();
        practicePhoneNumber=new MutableLiveData<>();
        coworkerLoading=new MutableLiveData<>();
        practiceName=new MutableLiveData<>();
    }

    @Override
    public void onSuccess(String url, String responseJson) {
        if(coworkerPageNo!=0){
            coworkerLoading.setValue(false);
        }else
            getLoading().setValue(false);
        Helper.setLog("onSuccess=responseJson",responseJson);
        if(url!=null && !url.isEmpty()){

            if(url.equalsIgnoreCase("DoctorDetails"))
            {
                DoctorDetailsResponse detailsResponse= Helper.getGsonInstance().fromJson(responseJson,DoctorDetailsResponse.class);

                if(detailsResponse!=null && detailsResponse.getData()!=null && detailsResponse.getData().getDoctor()!=null)
                {

                    Helper.setLog("onSuccess=responseJson",detailsResponse.toString());
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

                        ArrayList<Coworker> coworkerArrayList=new ArrayList<>();

                        coworkerArrayList.addAll( detailsResponse.getData().getCoworker());
                        coworkerList.setValue(coworkerArrayList);
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
                       // Helper.setLog("inside","doctor.getContactInfo()");

                        for (int i = 0; i < doctor.getContactInfo().size(); i++) {
                            //1 means website
                            // 2 means phone number
                            if(doctor.getContactInfo().get(i).getType()==1 ){
                                if(!TextUtils.isEmpty(doctor.getContactInfo().get(i).getDetails())){
                                    practiceWebUrl.setValue(doctor.getContactInfo().get(i).getDetails());
                                }else {
                                    practiceWebUrl.setValue("Not Available");
                                }

                            }

                            else if(doctor.getContactInfo().get(i).getType()==2 ){
                                practicePhoneNumber.setValue(doctor.getContactInfo().get(i).getDetails());
                            }

                            else if(doctor.getContactInfo().get(i).getType()==0){
                                practiceWebUrl.setValue("Not Available");
                                practicePhoneNumber.setValue("Not Available");
                            }
                        }
                    }
                    else {
                        Helper.setLog("inside practiceWebUrl","Not Available");
                        practiceWebUrl.setValue("Not Available");
                        practicePhoneNumber.setValue("Not Available");
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
        if(coworkerPageNo!=0){
            coworkerLoading.setValue(false);
        }else
            getLoading().setValue(false);
        Helper.setLog("onError","url-"+url+"  errorMessage-"+errorMessage );
        getToast().setValue(errorMessage);
        //doctorDetailsResponse.setValue(null);
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

    public MutableLiveData<Boolean> getcoworkerLoading() {
        return coworkerLoading;
    }
}
