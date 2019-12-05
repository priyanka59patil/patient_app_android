package com.werq.patient.viewmodel;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorListResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;
import com.werq.patient.service.model.ResponcejsonPojo.Insurance;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.internal.http2.ErrorCode;

public class BottomTabViewModel extends BaseViewModel implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "BottomTabViewModel";
    private PatientRepository patientRepository;
    private CompositeDisposable disposable;

    /*String authToken;
    String refreshTokenId;*/
    ApiResponce apiResponce=this;
    private MutableLiveData<Boolean> rvVisibility;
    private MutableLiveData<Boolean> rvDoctorListVisibility;
   // public MutableLiveData<Boolean> teamloading;
    public MutableLiveData<Boolean> doctorDetailsloading;
    public MutableLiveData<Boolean> isAllCheckedState;
    public  MutableLiveData<ArrayList<DoctorTeamResult>> teamList;
    public  MutableLiveData<ArrayList<Doctor>> filterDoctorsList;
    public MutableLiveData<ArrayList<AttachmentResult>> listAttachments ;
    public  MutableLiveData<String> openFrag;
    int doctorListPage=0;


    public BottomTabViewModel() {
        super();
        openFrag=new MutableLiveData<>();
        openFrag.setValue("calendar");

        patientRepository = new PatientRepository();
        disposable = new CompositeDisposable();
        this.patientRepository =new PatientRepository();


        rvVisibility=new MutableLiveData<>();
        teamList=new MutableLiveData<>();
        listAttachments=new MutableLiveData<>();
        doctorDetailsloading=new MutableLiveData<>();
        filterDoctorsList=new MutableLiveData<>();
        rvDoctorListVisibility=new MutableLiveData<>();
        isAllCheckedState=new MutableLiveData<>();


    }

    public MutableLiveData<String> getOpenFrag() {
        return openFrag;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.calendar:
                Log.e(TAG, "calendar: " );
                openFrag.setValue("calendar");
                return true;

            case R.id.messages:
                Log.e(TAG, "messages: " );
                openFrag.setValue("messages");
                return true;

            case R.id.people:
                openFrag.setValue("people");
                Log.e(TAG, "people: " );
               return true;

            case R.id.profile:
                openFrag.setValue("profile");
                Log.e(TAG, "profile: " );
                return true;

            case R.id.folder:
                openFrag.setValue("folder");
                Log.e(TAG, "folder: " );
                return true;
        }
        return false;
    }




    public MutableLiveData<Boolean> getRvVisibility() {
        return rvVisibility;
    }


    public void fetchTeamList(int page){
        getLoading().setValue(true);

        patientRepository.getDocterTeamAppoitment(Helper.autoken,"10",""+page*10,
                    getToast(),apiResponce,"DoctorTeam");

    }

    public void fetchAttachments(int page,String doctors,String filter) {
        getLoading().setValue(true);
        patientRepository.getAttachments(Helper.autoken,doctors,filter,"10",page*10+"",getToast(),apiResponce,"AllAttachments");
    }

    public void fetchFilterDoctorList(int page) {
        getLoading().setValue(true);
        doctorListPage=page;
        patientRepository.getFilterDoctorList(Helper.autoken,"10",page*10+"",getToast(),apiResponce,"FilterDoctorList");
    }


    @Override
    public void onSuccess(String url, String responseJson) {
        Helper.setLog("responseJson",responseJson);


        getLoading().setValue(false);

        if(url!=null && url.equals("DoctorTeam"))
        {
            if (teamList.getValue()!=null)
            Helper.setLog(TAG,"onSuccess"+teamList.getValue().size()+"");

            DoctorTeamResponse doctorTeamResponse=Helper.getGsonInstance().fromJson(responseJson,DoctorTeamResponse.class);
            if(doctorTeamResponse!=null){
                ArrayList<DoctorTeamResult> dataArrayList=new ArrayList<>();
                if(doctorTeamResponse.getData()!=null && doctorTeamResponse.getData().getResult()!=null) {
                    dataArrayList.addAll(doctorTeamResponse.getData().getResult());
                    teamList.setValue(dataArrayList);
                }else {
                    teamList.setValue(null);
                }
            }
            else {
                teamList.setValue(null);
            }


            Helper.setLog(TAG,"onSuccess  next"+teamList.getValue().size()+"");

        }
        if(url!=null && url.equals("AllAttachments"))
        {

            AttachmentResponse attachmentResponse=Helper.getGsonInstance().fromJson(responseJson,AttachmentResponse.class);

            if(attachmentResponse !=null){
                ArrayList<AttachmentResult> dataArrayList=new ArrayList<>();
                if(attachmentResponse.getData()!=null && attachmentResponse.getData().getResult()!=null){
                    dataArrayList.addAll(attachmentResponse.getData().getResult());
                    listAttachments.setValue(dataArrayList);

                }else {
                    listAttachments.setValue(null);
                }

            }
            else {
                listAttachments.setValue(null);
            }

        }
        if(url!=null && url.equals("FilterDoctorList"))
        {

            DoctorListResponse doctorListResponse=Helper.getGsonInstance().fromJson(responseJson,DoctorListResponse.class);

            if(doctorListResponse !=null && doctorListResponse.getData()!=null){

                ArrayList<Doctor> doctorList=new ArrayList<>();

                if(doctorListResponse.getData().getAppointment()!=null){
                    if(filterDoctorsList.getValue()!=null && doctorListPage!=0){
                        doctorList.addAll(filterDoctorsList.getValue());
                    }

                    for (int i = 0; i < doctorListResponse.getData().getAppointment().size(); i++) {
                        if(doctorListResponse.getData().getAppointment().get(i).getDoctor()!=null)
                            doctorList.add(doctorListResponse.getData().getAppointment().get(i).getDoctor());
                    }
                }

                filterDoctorsList.setValue(doctorList);

                if(filterDoctorsList.getValue().size()>0){
                    rvDoctorListVisibility.setValue(true);
                }else {
                    rvDoctorListVisibility.setValue(false);
                }

            }
            else {
                listAttachments.setValue(null);
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

    public MutableLiveData<ArrayList<AttachmentResult>> getListAttachments() {
        return listAttachments;
    }

    public MutableLiveData<ArrayList<DoctorTeamResult>> getTeamList() {
        return teamList;
    }

    public MutableLiveData<ArrayList<Doctor>> getFilterDoctorsList() {
        return filterDoctorsList;
    }

    public MutableLiveData<Boolean> getRvDoctorListVisibility() {
        return rvDoctorListVisibility;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
