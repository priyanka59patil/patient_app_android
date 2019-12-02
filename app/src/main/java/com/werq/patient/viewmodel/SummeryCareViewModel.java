package com.werq.patient.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.AssessmentsResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Encounter;
import com.werq.patient.service.model.ResponcejsonPojo.EncounterListResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Instruction;
import com.werq.patient.service.model.ResponcejsonPojo.InstructionResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Insurance;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationDatum;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Patient;
import com.werq.patient.service.model.ResponcejsonPojo.PatientProfileResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SummeryCareViewModel extends BaseViewModel {
    private static final String TAG = "PatientProfileViewModel";
    private final PatientRepository patientRepository;
    ApiResponce apiResponce = this;
    public MutableLiveData<ArrayList<Insurance>> insuranceList;
    public MutableLiveData<ArrayList<MedicationDatum>> medicationList;
    public MutableLiveData<ArrayList<Encounter>> encounterList;
    public MutableLiveData<ArrayList<Instruction>> instructionList;
    private MutableLiveData<Boolean> rvInsuranceVisibility;
    private MutableLiveData<Boolean> rvMedicationVisibility;
    private MutableLiveData<Boolean> rvEnountersVisibility;
    private MutableLiveData<Boolean> rvInstructionVisibility;


    public MutableLiveData<String> patientProfileUrl;
    public MutableLiveData<String> patientName;
    public MutableLiveData<String> patientDOB;
    public MutableLiveData<String> phoneNumber;
    public MutableLiveData<String> address;

    public MutableLiveData<String> assessments;

    int medicationPage=0;
    int encounterPage=0;
    int instructionPage=0;

    Context mContext;


    public SummeryCareViewModel() {
        patientRepository = new PatientRepository();
        insuranceList=new MutableLiveData<>();
        rvInsuranceVisibility=new MutableLiveData<>();
        rvMedicationVisibility=new MutableLiveData<>();
        rvEnountersVisibility=new MutableLiveData<>();
        rvInstructionVisibility=new MutableLiveData<>();

        patientProfileUrl=new MutableLiveData<>();
        patientName=new MutableLiveData<>();
        patientDOB=new MutableLiveData<>();
        phoneNumber=new MutableLiveData<>();
        address=new MutableLiveData<>();
        medicationList =new MutableLiveData<>();
        encounterList =new MutableLiveData<>();
        assessments =new MutableLiveData<>();
        instructionList=new MutableLiveData<>();

    }


    public void fetchPatientProfileData(){
        getLoading().setValue(true);
        patientRepository.getPatientProfile(Helper.autoken, getToast(), apiResponce, "PatientProfile");

        /*patientName.setValue("Priya Patil");
        patientDOB.setValue("31-07-1996");
        phoneNumber.setValue("9870234047");
        address.setValue("At-ksj Post -kjdhsf, kjhdf, hf,400987");*/

    }

    public void fetchMedicationList(int page){
        getLoading().setValue(true);
        patientRepository.getMedicationList(Helper.autoken,"10",page*10+"", getToast(), apiResponce, "MedicationList");
        medicationPage=page;
    }

    public void fetchEncounterList(int page){
        getLoading().setValue(true);
        patientRepository.getEncounterList(Helper.autoken,"10",page*10+"", getToast(), apiResponce, "EncountersList");
        encounterPage=page;
    }

    public void fetchAssessments(){
        getLoading().setValue(true);
        patientRepository.getAssessmets(Helper.autoken, getToast(), apiResponce, "Asssessments");
    }

    public void fetchInstruction(int page){
        getLoading().setValue(true);
        patientRepository.getInstructionList(Helper.autoken,"10",page*10+"", getToast(), apiResponce, "Instructions");
        instructionPage=page;
    }

    @Override
    public void onSuccess(String url, String responseJson) {
        getLoading().setValue(false);

        if(url!=null && url.equals("PatientProfile")){

            PatientProfileResponse patientProfileResponse=Helper
                    .getGsonInstance().fromJson(responseJson,PatientProfileResponse.class);

            if(patientProfileResponse!=null){
                Helper.setLog("PatientProfileResponse",patientProfileResponse.toString());
                if(patientProfileResponse.getData()!=null && patientProfileResponse.getData().getPatient()!=null ){

                    Patient patient=patientProfileResponse.getData().getPatient();
                    patientName.setValue(patient.getFirstName()+" "+patient.getLastName());
                    Date patDob=null;
                    try {
                        patDob= new SimpleDateFormat("yyyy-MM-dd").parse(patient.getDOB());

                        patientDOB.setValue(new SimpleDateFormat("MMMM dd, yyyy").format(patDob));

                    } catch (ParseException e) {
                        patientDOB.setValue("");
                        e.printStackTrace();
                    }

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
                    insuranceList.setValue(null);
                    insuranceList.setValue(insuranceArrayList);

                    if(insuranceList.getValue().size()>0){
                        rvInsuranceVisibility.setValue(true);
                    }else {
                        rvInsuranceVisibility.setValue(false);
                    }
                }

            }
        }
        if(url!=null && url.equals("MedicationList")){

            MedicationResponse medicationResponse=Helper.getGsonInstance()
                    .fromJson(responseJson, MedicationResponse.class);

            if(medicationResponse!=null && medicationResponse.getData()!=null){

                if(medicationResponse.getData().getMedicationDataList()!=null){

                    ArrayList<MedicationDatum> medicationArrayList=new ArrayList<>();

                    if(medicationList.getValue()!=null && medicationPage!=0){
                        medicationArrayList.addAll(medicationList.getValue());
                    }
                    medicationArrayList.addAll(medicationResponse.getData().getMedicationDataList());
                    medicationList.setValue(medicationArrayList);

                    if(medicationList.getValue().size()>0){
                        rvMedicationVisibility.setValue(true);
                    }else {
                        rvMedicationVisibility.setValue(false);
                    }

                }
            }

        }

        if(url!=null && url.equals("EncountersList")){
            EncounterListResponse encounterListResponse=Helper.getGsonInstance()
                    .fromJson(responseJson, EncounterListResponse.class);

            if(encounterListResponse!=null && encounterListResponse.getData()!=null){

                if(encounterListResponse.getData().getEncountersList()!=null){

                    ArrayList<Encounter> encounterArrayList=new ArrayList<>();

                    if(encounterList.getValue()!=null && encounterPage!=0){
                        encounterArrayList.addAll(encounterList.getValue());
                    }
                    encounterArrayList.addAll(encounterListResponse.getData().getEncountersList());
                    encounterList.setValue(encounterArrayList);

                    if(encounterList.getValue().size()>0){
                        rvEnountersVisibility.setValue(true);
                    }else {
                        rvEnountersVisibility.setValue(false);
                    }
                }

            }
        }

        if(url!=null && url.equals("Asssessments")){

            AssessmentsResponse response =Helper.getGsonInstance().fromJson(responseJson,AssessmentsResponse.class);
            if(response!=null && response.getAssessment()!=null){
                if(response.getAssessment().getAssessment()!=null){
                    assessments.setValue(response.getAssessment().getAssessment());
                }else {
                    assessments.setValue(null);
                }

            }
        }

        if(url!=null && url.equals("Instructions")){
            InstructionResponse instructionResponse=Helper.getGsonInstance()
                    .fromJson(responseJson, InstructionResponse.class);

            if(instructionResponse!=null && instructionResponse.getData()!=null){

                if(instructionResponse.getData().getInstructionsList()!=null){

                    ArrayList<Instruction> instructionArrayList=new ArrayList<>();

                    if(instructionList.getValue()!=null && instructionPage!=0){
                        instructionArrayList.addAll(instructionList.getValue());
                    }
                    instructionArrayList.addAll(instructionResponse.getData().getInstructionsList());
                    instructionList.setValue(instructionArrayList);

                    if(instructionList.getValue().size()>0){
                        rvInstructionVisibility.setValue(true);
                    }else {
                        rvInstructionVisibility.setValue(false);
                    }
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

    public MutableLiveData<ArrayList<Insurance>> getInsuranceList() {
        return insuranceList;
    }

    public MutableLiveData<ArrayList<MedicationDatum>> getMedicationList() {
        return medicationList;
    }

    public MutableLiveData<Boolean> getRvInsuranceVisibility() {
        return rvInsuranceVisibility;
    }

    public MutableLiveData<Boolean> getRvMedicationVisibility() {
        return rvMedicationVisibility;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public MutableLiveData<ArrayList<Encounter>> getEncounterList() {
        return encounterList;
    }

    public MutableLiveData<Boolean> getRvEnountersVisibility() {
        return rvEnountersVisibility;
    }

    public MutableLiveData<ArrayList<Instruction>> getInstructionList() {
        return instructionList;
    }

    public MutableLiveData<Boolean> getRvInstructionVisibility() {
        return rvInstructionVisibility;
    }

    public MutableLiveData<String> getAssessments() {
        return assessments;
    }
}
