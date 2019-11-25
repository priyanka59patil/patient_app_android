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
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;

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

    public MutableLiveData<Boolean> teamloading;
    public MutableLiveData<Boolean> doctorDetailsloading;
    public MutableLiveData<Boolean> attachmentsloading;

    public  MutableLiveData<ArrayList<DoctorTeamResult>> teamList;
    public MutableLiveData<ArrayList<AttachmentResult>> listAttachments ;

    public  MutableLiveData<String> openFrag;
    int doctorTeamPageNo=0;
    int historyPageNo=0;
    int attachmentPageNo=0;


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

        teamloading=new MutableLiveData<>();
        doctorDetailsloading=new MutableLiveData<>();
        attachmentsloading=new MutableLiveData<>();


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

        teamloading.setValue(true);

            patientRepository.getDocterTeamAppoitment(Helper.autoken,"10",""+page*10,
                    getToast(),apiResponce,"DoctorTeam");

    }

    public void fetchAttachments(int page) {
        attachmentsloading.setValue(true);
        attachmentPageNo=page;
        patientRepository.getAttachments(Helper.autoken,"","10",page*10+"",getToast(),apiResponce,"AllAttachments");
    }


    @Override
    public void onSuccess(String url, String responseJson) {
        Helper.setLog("responseJson",responseJson);

        DoctorTeamResponse doctorTeamResponse=Helper.getGsonInstance().fromJson(responseJson,DoctorTeamResponse.class);

        getLoading().setValue(false);

        if(url!=null && url.equals("DoctorTeam"))
        {
            teamloading.setValue(false);
            ArrayList<DoctorTeamResult> dataArrayList=new ArrayList<>();

            if(teamList.getValue()!=null && doctorTeamPageNo!=0){
                dataArrayList.addAll(teamList.getValue());
            }
            dataArrayList.addAll(doctorTeamResponse.getData().getResult());
            teamList.setValue(dataArrayList);

            if (teamList.getValue().size() > 0) {
                rvVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvVisibility.setValue(false);
            }
        }
        if(url!=null && url.equals("AllAttachments"))
        {

            attachmentsloading.setValue(false);
            AttachmentResponse attachmentResponse=Helper.getGsonInstance().fromJson(responseJson,AttachmentResponse.class);

            if(attachmentResponse !=null){
                ArrayList<AttachmentResult> dataArrayList=new ArrayList<>();
                if(listAttachments.getValue()!=null && attachmentPageNo!=0){
                    dataArrayList.addAll(listAttachments.getValue());
                }
                if(attachmentResponse.getData()!=null && attachmentResponse.getData().getResult()!=null){
                    dataArrayList.addAll(attachmentResponse.getData().getResult());
                    listAttachments.setValue(dataArrayList);

                    if (listAttachments.getValue().size() > 0) {
                        rvVisibility.setValue(true);
                        //noVisitNote.setVisibility(View.GONE);

                    } else {
                        rvVisibility.setValue(false);
                    }
                }else {
                    listAttachments.setValue(null);
                    rvVisibility.setValue(false);
                }

            }
            else {
                listAttachments.setValue(null);
                rvVisibility.setValue(false);
            }

        }

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
        teamloading.setValue(false);
        attachmentsloading.setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {
        teamloading.setValue(false);
        attachmentsloading.setValue(false);
        getLoading().setValue(false);
    }

    public MutableLiveData<ArrayList<AttachmentResult>> getListAttachments() {
        return listAttachments;
    }

    public MutableLiveData<ArrayList<DoctorTeamResult>> getTeamList() {
        return teamList;
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
