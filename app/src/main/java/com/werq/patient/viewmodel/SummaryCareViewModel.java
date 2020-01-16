package com.werq.patient.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.Callback.ApiCallback;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.Allergy;
import com.werq.patient.service.model.ResponcejsonPojo.AllergyResponse;
import com.werq.patient.service.model.ResponcejsonPojo.ApiResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Assessment;
import com.werq.patient.service.model.ResponcejsonPojo.Encounter;
import com.werq.patient.service.model.ResponcejsonPojo.EncounterListResponse;
import com.werq.patient.service.model.ResponcejsonPojo.HistoryOfProcedure;
import com.werq.patient.service.model.ResponcejsonPojo.Instruction;
import com.werq.patient.service.model.ResponcejsonPojo.InstructionResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Insurance;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationData;
import com.werq.patient.service.model.ResponcejsonPojo.MedicationDatum;
import com.werq.patient.service.model.ResponcejsonPojo.PastillnessHistory;
import com.werq.patient.service.model.ResponcejsonPojo.PastillnessHistoryResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Patient;
import com.werq.patient.service.model.ResponcejsonPojo.PatientProfileData;
import com.werq.patient.service.model.ResponcejsonPojo.PlanOfCare;
import com.werq.patient.service.model.ResponcejsonPojo.PlanOfCareResponse;
import com.werq.patient.service.model.ResponcejsonPojo.Problem;
import com.werq.patient.service.model.ResponcejsonPojo.ProblemListResponse;
import com.werq.patient.service.model.ResponcejsonPojo.ProcedureHistoryResponse;
import com.werq.patient.service.model.ResponcejsonPojo.SocialHistory;
import com.werq.patient.service.model.ResponcejsonPojo.SocialHistoryResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Response;

public class SummaryCareViewModel extends BaseViewModel {
    private static final String TAG = "PatientProfileViewModel";
    private final PatientRepository patientRepository;
    ApiCallback apiCallback = this;
    public MutableLiveData<ArrayList<Insurance>> insuranceList;
    public MutableLiveData<ArrayList<MedicationDatum>> medicationList;
    public MutableLiveData<ArrayList<Encounter>> encounterList;
    public MutableLiveData<ArrayList<Instruction>> instructionList;
    public MutableLiveData<ArrayList<PlanOfCare>> planOfCareList;
    public MutableLiveData<ArrayList<HistoryOfProcedure>> historyProcedureList;
    public MutableLiveData<ArrayList<Allergy>> allergyList;
    public MutableLiveData<ArrayList<PastillnessHistory>> pastillnessHistoryList;
    public MutableLiveData<ArrayList<SocialHistory>> socialHistoryList;
    public MutableLiveData<ArrayList<Problem>> problemList;
    private MutableLiveData<Boolean> rvInsuranceVisibility;
    private MutableLiveData<Boolean> rvMedicationVisibility;
    private MutableLiveData<Boolean> rvEnountersVisibility;
    private MutableLiveData<Boolean> rvInstructionVisibility;
    private MutableLiveData<Boolean> rvPlanOfCareVisibility;
    private MutableLiveData<Boolean> rvHistoryOfProcedureVisibility;
    private MutableLiveData<Boolean> rvAllergyVisibility;
    private MutableLiveData<Boolean> rvPastillnessHistoryVisibility;
    private MutableLiveData<Boolean> rvSocialHistoryVisibility;
    private MutableLiveData<Boolean> rvProblemListVisibility;


    public MutableLiveData<String> patientProfileUrl;
    public MutableLiveData<String> patientName;
    public MutableLiveData<String> patientDOB;
    public MutableLiveData<String> phoneNumber;
    public MutableLiveData<String> address;

    public MutableLiveData<String> assessments;

    int medicationPage=0;
    int encounterPage=0;
    int instructionPage=0;
    int planCarePage=0;
    int historyProcedurePage=0;
    int allergyPage=0;
    int pastillnessHistoryPage=0;
    int socialHistoryPage=0;
    int problemListPage=0;

    Context mContext;


    public SummaryCareViewModel(String authToken) {
        super(authToken);
        patientRepository = new PatientRepository();
        insuranceList=new MutableLiveData<>();
        rvInsuranceVisibility=new MutableLiveData<>();
        rvMedicationVisibility=new MutableLiveData<>();
        rvEnountersVisibility=new MutableLiveData<>();
        rvInstructionVisibility=new MutableLiveData<>();
        rvPlanOfCareVisibility=new MutableLiveData<>();
        rvHistoryOfProcedureVisibility=new MutableLiveData<>();
        rvAllergyVisibility=new MutableLiveData<>();
        rvPastillnessHistoryVisibility=new MutableLiveData<>();
        rvSocialHistoryVisibility=new MutableLiveData<>();
        rvProblemListVisibility=new MutableLiveData<>();

        patientProfileUrl=new MutableLiveData<>();
        patientName=new MutableLiveData<>();
        patientDOB=new MutableLiveData<>();
        phoneNumber=new MutableLiveData<>();
        address=new MutableLiveData<>();
        medicationList =new MutableLiveData<>();
        encounterList =new MutableLiveData<>();
        assessments =new MutableLiveData<>();
        instructionList=new MutableLiveData<>();
        planOfCareList=new MutableLiveData<>();
        historyProcedureList=new MutableLiveData<>();
        allergyList=new MutableLiveData<>();
        pastillnessHistoryList=new MutableLiveData<>();
        socialHistoryList=new MutableLiveData<>();
        problemList=new MutableLiveData<>();

    }


    public void fetchPatientProfileData(){
        getLoading().setValue(true);
        patientRepository.getPatientProfile(getAuthToken(), getToast(), apiCallback, "PatientProfile");

        /*patientName.setValue("Priya Patil");
        patientDOB.setValue("31-07-1996");
        phoneNumber.setValue("9870234047");
        address.setValue("At-ksj Post -kjdhsf, kjhdf, hf,400987");*/

    }

    public void fetchMedicationList(int page){
        getLoading().setValue(true);
        patientRepository.getMedicationList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "MedicationList");
        medicationPage=page;
    }

    public void fetchEncounterList(int page){
        getLoading().setValue(true);
        patientRepository.getEncounterList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "EncountersList");
        encounterPage=page;
    }

    public void fetchAssessments(){
        getLoading().setValue(true);
        patientRepository.getAssessmets(getAuthToken(), getToast(), apiCallback, "Asssessments");
    }

    public void fetchInstruction(int page){
        getLoading().setValue(true);
        patientRepository.getInstructionList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "Instructions");
        instructionPage=page;
    }

    public void fetchPlanOfCareList(int page){
        getLoading().setValue(true);
        patientRepository.getPlanOfCareList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "PlanOfCare");
        planCarePage=page;
    }

    public void fetchHistoryOfProcedureList(int page){
        getLoading().setValue(true);
        patientRepository.getHistoryOfProcedureList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "HistoryOfProcedure");
        historyProcedurePage=page;
    }

    public void fetchAllergyList(int page){
        getLoading().setValue(true);
        patientRepository.getAllergyList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "AllergyList");
        allergyPage=page;
    }

    public void fetchPastillnessHistoryList(int page){
        getLoading().setValue(true);
        patientRepository.getPastillnessHistoryList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "PastillnessHistoryList");
        allergyPage=page;
    }

    public void fetchSocialHistoryList(int page){
        getLoading().setValue(true);
        patientRepository.getSocialHistoryList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "SocialHistoryList");
        allergyPage=page;
    }

    public void fetchProblemList(int page){
        getLoading().setValue(true);
        patientRepository.getProblemList(getAuthToken(),"10",page*10+"", getToast(), apiCallback, "ProblemList");
        allergyPage=page;
    }

    @Override
    public void onSuccess(String url, Response response) {
        getLoading().setValue(false);

        if(url!=null && url.equals("PatientProfile")){

            ApiResponse<PatientProfileData> patientProfileResponse= (ApiResponse<PatientProfileData>) response.body();

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
                        Helper.setExceptionLog("ParseException",e);
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

            ApiResponse<MedicationData> medicationResponse= (ApiResponse<MedicationData>) response.body();

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
            String json = Helper.getGsonInstance().toJson(response.body());
            EncounterListResponse encounterListResponse=Helper.getGsonInstance()
                    .fromJson(json, EncounterListResponse.class);

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

            ApiResponse<Assessment> assessmentsResponse = (ApiResponse<Assessment>) response.body();
            if(assessmentsResponse!=null && assessmentsResponse.getData()!=null){
                if(assessmentsResponse.getData().getAssessment()!=null){
                    assessments.setValue(assessmentsResponse.getData().getAssessment());
                }else {
                    assessments.setValue(null);
                }

            }
        }

        if(url!=null && url.equals("Instructions")){
            String json = Helper.getGsonInstance().toJson(response.body());
            InstructionResponse instructionResponse=Helper.getGsonInstance()
                    .fromJson(json, InstructionResponse.class);

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

        if(url!=null && url.equals("PlanOfCare")){
            String json = Helper.getGsonInstance().toJson(response.body());
            PlanOfCareResponse planOfCareResponse=Helper.getGsonInstance()
                    .fromJson(json, PlanOfCareResponse.class);

            if(planOfCareResponse!=null && planOfCareResponse.getData()!=null){

                if(planOfCareResponse.getData().getPlanOfCareList()!=null){

                    ArrayList<PlanOfCare> planCareList=new ArrayList<>();

                    if(planOfCareList.getValue()!=null && planCarePage!=0){
                        planCareList.addAll(planOfCareList.getValue());
                    }
                    planCareList.addAll(planOfCareResponse.getData().getPlanOfCareList());
                    planOfCareList.setValue(planCareList);

                    if(planOfCareList.getValue().size()>0){
                        rvPlanOfCareVisibility.setValue(true);
                    }else {
                        rvPlanOfCareVisibility.setValue(false);
                    }
                }

            }
        }

        if(url!=null && url.equals("HistoryOfProcedure")){
            String json = Helper.getGsonInstance().toJson(response.body());
            ProcedureHistoryResponse procHistoryResponse=Helper.getGsonInstance()
                    .fromJson(json, ProcedureHistoryResponse.class);

            if(procHistoryResponse!=null && procHistoryResponse.getData()!=null){

                if(procHistoryResponse.getData().getHistoryOfProcedureList()!=null){

                    ArrayList<HistoryOfProcedure> procHistoryList=new ArrayList<>();

                    if(historyProcedureList.getValue()!=null && historyProcedurePage!=0){
                        procHistoryList.addAll(historyProcedureList.getValue());
                    }
                    procHistoryList.addAll(procHistoryResponse.getData().getHistoryOfProcedureList());
                    historyProcedureList.setValue(procHistoryList);

                    if(historyProcedureList.getValue().size()>0){
                        rvHistoryOfProcedureVisibility.setValue(true);
                    }else {
                        rvHistoryOfProcedureVisibility.setValue(false);
                    }
                }

            }
        }

        if(url!=null && url.equals("AllergyList")){
            String json = Helper.getGsonInstance().toJson(response.body());
            AllergyResponse allergyResponse=Helper.getGsonInstance()
                    .fromJson(json, AllergyResponse.class);

            if(allergyResponse!=null && allergyResponse.getData()!=null){

                if(allergyResponse.getData().getAllergyList()!=null){

                    ArrayList<Allergy> list=new ArrayList<>();

                    if(allergyList.getValue()!=null && allergyPage!=0){
                        list.addAll(allergyList.getValue());
                    }
                    list.addAll(allergyResponse.getData().getAllergyList());
                    allergyList.setValue(list);

                    if(allergyList.getValue().size()>0){
                        rvAllergyVisibility.setValue(true);
                    }else {
                        rvAllergyVisibility.setValue(false);
                    }
                }

            }
        }

        if(url!=null && url.equals("PastillnessHistoryList")){
            String json = Helper.getGsonInstance().toJson(response.body());
            PastillnessHistoryResponse pastillnessResponse=Helper.getGsonInstance()
                    .fromJson(json, PastillnessHistoryResponse.class);

            if(pastillnessResponse!=null && pastillnessResponse.getData()!=null){

                if(pastillnessResponse.getData().getHistoryOfPastIllnessList()!=null){

                    ArrayList<PastillnessHistory> list=new ArrayList<>();

                    if(pastillnessHistoryList.getValue()!=null && pastillnessHistoryPage!=0){
                        list.addAll(pastillnessHistoryList.getValue());
                    }
                    list.addAll(pastillnessResponse.getData().getHistoryOfPastIllnessList());
                    pastillnessHistoryList.setValue(list);

                    if(pastillnessHistoryList.getValue().size()>0){
                        rvPastillnessHistoryVisibility.setValue(true);
                    }else {
                        rvPastillnessHistoryVisibility.setValue(false);
                    }
                }

            }
        }

        if(url!=null && url.equals("SocialHistoryList")){
            String json = Helper.getGsonInstance().toJson(response.body());
            SocialHistoryResponse socialHistoryResponse=Helper.getGsonInstance()
                    .fromJson(json, SocialHistoryResponse.class);

            if(socialHistoryResponse!=null && socialHistoryResponse.getData()!=null){

                if(socialHistoryResponse.getData().getSocialHistoryList()!=null){

                    ArrayList<SocialHistory> list=new ArrayList<>();

                    if(socialHistoryList.getValue()!=null && socialHistoryPage!=0){
                        list.addAll(socialHistoryList.getValue());
                    }
                    list.addAll(socialHistoryResponse.getData().getSocialHistoryList());
                    socialHistoryList.setValue(list);

                    if(socialHistoryList.getValue().size()>0){
                        rvSocialHistoryVisibility.setValue(true);
                    }else {
                        rvSocialHistoryVisibility.setValue(false);
                    }
                }

            }
        }

        if(url!=null && url.equals("ProblemList")){
            String json = Helper.getGsonInstance().toJson(response.body());
            ProblemListResponse problemListResponse=Helper.getGsonInstance()
                    .fromJson(json, ProblemListResponse.class);

            if(problemListResponse!=null && problemListResponse.getData()!=null){

                if(problemListResponse.getData().getProblemsList()!=null){

                    ArrayList<Problem> list=new ArrayList<>();

                    if(problemList.getValue()!=null && problemListPage!=0){
                        list.addAll(problemList.getValue());
                    }
                    list.addAll(problemListResponse.getData().getProblemsList());
                    problemList.setValue(list);

                    if(problemList.getValue().size()>0){
                        rvProblemListVisibility.setValue(true);
                    }else {
                        rvProblemListVisibility.setValue(false);
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
    public void onTokenRefersh(Response response) {
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

    public MutableLiveData<ArrayList<PlanOfCare>> getPlanOfCareList() {
        return planOfCareList;
    }

    public MutableLiveData<Boolean> getRvPlanOfCareVisibility() {
        return rvPlanOfCareVisibility;
    }

    public MutableLiveData<ArrayList<HistoryOfProcedure>> getHistoryProcedureList() {
        return historyProcedureList;
    }

    public MutableLiveData<ArrayList<Allergy>> getAllergyList() {
        return allergyList;
    }

    public MutableLiveData<Boolean> getRvHistoryOfProcedureVisibility() {
        return rvHistoryOfProcedureVisibility;
    }

    public MutableLiveData<Boolean> getRvAllergyVisibility() {
        return rvAllergyVisibility;
    }

    public MutableLiveData<ArrayList<PastillnessHistory>> getPastillnessHistoryList() {
        return pastillnessHistoryList;
    }

    public MutableLiveData<ArrayList<SocialHistory>> getSocialHistoryList() {
        return socialHistoryList;
    }

    public MutableLiveData<ArrayList<Problem>> getProblemList() {
        return problemList;
    }

    public MutableLiveData<Boolean> getRvPastillnessHistoryVisibility() {
        return rvPastillnessHistoryVisibility;
    }

    public MutableLiveData<Boolean> getRvSocialHistoryVisibility() {
        return rvSocialHistoryVisibility;
    }

    public MutableLiveData<Boolean> getRvProblemListVisibility() {
        return rvProblemListVisibility;
    }
}
