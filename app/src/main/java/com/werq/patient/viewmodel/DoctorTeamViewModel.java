package com.werq.patient.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResponse;
import com.werq.patient.service.model.ResponcejsonPojo.DoctorTeamResult;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class DoctorTeamViewModel  extends BaseViewModel {

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
    private MutableLiveData<Boolean> rvVisibility;
    public  MutableLiveData<ArrayList<DoctorTeamResult>> teamList;

    public DoctorTeamViewModel() {
        patientRepository = new PatientRepository();
        disposable = new CompositeDisposable();
        this.patientRepository =new PatientRepository();

        teamList=new MutableLiveData<>();
        rvVisibility=new MutableLiveData<>();
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        fetchTeamList();
    }

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public MutableLiveData<Boolean> getRvVisibility() {
        return rvVisibility;
    }

    public MutableLiveData<ArrayList<DoctorTeamResult>> getTeamList() {
        return teamList;
    }

    public void fetchTeamList(){

        if(!TextUtils.isEmpty(authToken)){
            Log.e(TAG, "authToken: "+authToken );

            patientRepository.getDocterTeamAppoitment(authToken,"20",""+0,
                    getToast(),apiResponce,"DoctorTeam");
        }
    }


    @Override
    public void onSuccess(String url, String responseJson) {
        Helper.setLog("responseJson",responseJson);

        DoctorTeamResponse doctorTeamResponse=Helper.getGsonInstance().fromJson(responseJson,DoctorTeamResponse.class);

        getLoading().setValue(false);

        if(url!=null && url.equals("DoctorTeam"))
        {
            listcount = doctorTeamResponse.getData().getCount();
            ArrayList<DoctorTeamResult> dataArrayList=new ArrayList<>();
            dataArrayList.addAll(doctorTeamResponse.getData().getResult());
            teamList.setValue(dataArrayList);

            if (teamList.getValue().size() > 0) {
                rvVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvVisibility.setValue(false);
            }
        }

    }

    @Override
    public void onError(String url, String errorCode,String errorMessage) {
        getLoading().setValue(false);
    }

    @Override
    public void onTokenRefersh(String responseJson) {

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
