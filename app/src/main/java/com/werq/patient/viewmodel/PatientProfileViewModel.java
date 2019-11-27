package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.Insurance;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.model.ResponcejsonPojo.Patient;
import com.werq.patient.service.model.ResponcejsonPojo.PatientProfileResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientProfileViewModel extends BaseViewModel {
    private static final String TAG = "PatientProfileViewModel";
    private final PatientRepository patientRepository;
    ApiResponce apiResponce = this;
    public MutableLiveData<ArrayList<Insurance>> insuranceList;
    private MutableLiveData<Boolean> rvInsuranceVisibility;

    public MutableLiveData<String> patientProfileUrl;
    public MutableLiveData<String> patientName;
    public MutableLiveData<String> patientDOB;
    public MutableLiveData<String> phoneNumber;
    public MutableLiveData<String> address;


    public PatientProfileViewModel() {
        patientRepository = new PatientRepository();
        insuranceList=new MutableLiveData<>();
        rvInsuranceVisibility=new MutableLiveData<>();


        patientProfileUrl=new MutableLiveData<>();
        patientName=new MutableLiveData<>();
        patientDOB=new MutableLiveData<>();
        phoneNumber=new MutableLiveData<>();
        address=new MutableLiveData<>();
    }


    public void fetchPatientProfileData(){
        getLoading().setValue(true);
        patientRepository.getPatientProfile(Helper.autoken, getToast(), apiResponce, "PatientProfile");

        /*patientName.setValue("Priya Patil");
        patientDOB.setValue("31-07-1996");
        phoneNumber.setValue("9870234047");
        address.setValue("At-ksj Post -kjdhsf, kjhdf, hf,400987");*/

    }

    @Override
    public void onSuccess(String url, String responseJson) {
        getLoading().setValue(false);

        if(url!=null && url.equals("PatientProfile")){

            PatientProfileResponse patientProfileResponse=Helper
                    .getGsonInstance().fromJson(responseJson,PatientProfileResponse.class);

            if(patientProfileResponse!=null){
                if(patientProfileResponse.getData()!=null && patientProfileResponse.getData().getPatient()!=null ){

                    Patient patient=patientProfileResponse.getData().getPatient();
                    patientName.setValue(patient.getFirstName()+" "+patient.getLastName());
                    patientDOB.setValue(patient.getDOB());
                    for (int i = 0; i <patient.getContactInfo().size() ; i++) {
                        if(patient.getContactInfo().get(i).getType()==2){
                            phoneNumber.setValue(patient.getContactInfo().get(i).getDetails());
                        }
                    }


                    String strAddress =patient.getAddress1()+" "+patient.getCity()
                            +" "+patient.getState()+" "+ patient.getCity()+""+patient.getCountry();
                    if(!strAddress.trim().equals("")){
                        address.setValue(strAddress);
                    }else {
                        address.setValue("Not Available");
                    }

                }

                if(patientProfileResponse.getData()!=null && patientProfileResponse.getData().getInsurance()!=null ){

                    ArrayList<Insurance> insuranceArrayList=new ArrayList<>();
                    insuranceArrayList.addAll(patientProfileResponse.getData().getInsurance());
                    insuranceList.setValue(insuranceArrayList);
                }

            }
        }

    }

    @Override
    public void onError(String url, String errorCode, String errorMessage) {
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {
        getLoading().setValue(false);
    }

    public MutableLiveData<String> getPatientProfileUrl() {
        return patientProfileUrl;
    }

    public MutableLiveData<String> getPatientName() {
        return patientName;
    }

    public MutableLiveData<String> getPatientDOB() {
        return patientDOB;
    }

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public MutableLiveData<String> getAddress() {
        return address;
    }
}
