package com.werq.patient.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.MockData.JsonData;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.repository.AppointmentRepository;
import com.werq.patient.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.internal.http2.ErrorCode;

public class TabAppoinmentViewModel extends BaseViewModel {

    private final AppointmentRepository appointmentRepository;
    private CompositeDisposable disposable;
    private static final String TAG = "TabAppoinmentViewModel";

    private final MutableLiveData<ArrayList<AppointmentResult>> listAppointments = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private MutableLiveData<Boolean> rvVisibility;
    boolean isFromUpcoming=true;;


    int visibleItemCount,totalItemCount,pastVisiblesItems;
    private int listcount;
    boolean loading;
    private int page=0;
    String authToken;
    ApiResponce apiResponce=this;

    public TabAppoinmentViewModel(boolean isFromUpcoming, Context context) {

        appointmentRepository = new AppointmentRepository();
        disposable = new CompositeDisposable();
        this.isFromUpcoming=isFromUpcoming;
        loading=true;
        rvVisibility=new MutableLiveData<>();


    }

    public MutableLiveData<ArrayList<AppointmentResult>> getListAppointments() {
        return listAppointments;
    }

    public MutableLiveData<Boolean> getRepoLoadError() {
        return repoLoadError;
    }


    public MutableLiveData<Boolean> getRvVisibility() {
        return rvVisibility;
    }

    public void setRvVisibility(MutableLiveData<Boolean> rvVisibility) {
        this.rvVisibility = rvVisibility;
    }

    /* private void fetchRepos() {
        loading.setValue(true);
        disposable.add(repoRepository.getRepositories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                    @Override
                    public void onSuccess(List<Repo> value) {
                        repoLoadError.setValue(false);
                        repos.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }*/

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    @Override
    public void onSuccess(String url,String responseJson) {

        Helper.setLog("responseJson",responseJson);

        AppointmentResponse appointmentResponce=Helper.getGsonInstance().fromJson(responseJson,AppointmentResponse.class);

        getLoading().setValue(false);

        if(url!=null && url.equals("UpcomingAppointment"))
        {
            listcount = appointmentResponce.getData().getCount();
            ArrayList<AppointmentResult> dataArrayList=new ArrayList<>();
            dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
            listAppointments.setValue(dataArrayList);

            if (listAppointments.getValue().size() > 0) {
                rvVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvVisibility.setValue(false);
            }
        }

        if(url!=null && url.equals("HistoryAppointment"))
        {
            listcount = appointmentResponce.getData().getCount();
            ArrayList<AppointmentResult> dataArrayList=new ArrayList<>();
            dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
            listAppointments.setValue(dataArrayList);

           /* if (listAppointments.getValue().size() > 0) {
                if (rvVisitList !=null) {
                    rvVisitList.setVisibility(View.VISIBLE);
                    noVisitNote.setVisibility(View.GONE);
                }

            } else {
                if (rvVisitList !=null) {
                    rvVisitList.setVisibility(View.GONE);
                    noVisitNote.setVisibility(View.VISIBLE);
                }
            }*/
        }

    }

    @Override
    public void onError(String url, String errorCode) {
        getLoading().setValue(false);
    }

   /* public void onScrollDown(int childCount,int itemCount,int firstVisibleItemPosition){

       *//* visibleItemCount = recyclerView.getChildCount();
        totalItemCount = recyclerView.getAdapter().getItemCount();
        pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();*//*

        visibleItemCount = childCount;
        totalItemCount = itemCount;
        pastVisiblesItems = firstVisibleItemPosition;
        if (listcount < 20) {
            loading = false;
        }
        int count = page + 1;
        int data = totalItemCount ;

        if (data == (count * 20)) {
            if (loading) {
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    //                                loading = false;
                    //Logv("...", "Last Item Wow !");
                    loading=true;
                    ++page;
                    getUpcomingAppointmentList();
                    //Do pagination.. i.e. fetch new data
                }
            }
        }
    }*/

    private void getUpcomingAppointmentList() {
        getLoading().setValue(true);

        if(authToken!=null&& !authToken.isEmpty()){
            Log.e(TAG, "getToken: "+authToken );

            appointmentRepository.getUpcommingAppoitment(authToken,"10",""+page*10,
                    getToast(),apiResponce,"UpcomingAppointment");
        }

    }

    private void getHistoryAppointmentList(){
        getLoading().setValue(true);
        appointmentRepository.getHistoryAppoitment(authToken,"10",""+page*10,
                getToast(),apiResponce,"HistoryAppointment");
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        if(isFromUpcoming)
            getUpcomingAppointmentList();
        else
            getHistoryAppointmentList();
    }
}
