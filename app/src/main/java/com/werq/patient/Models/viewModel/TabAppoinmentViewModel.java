package com.werq.patient.Models.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.Controller.AppointmentController;
import com.werq.patient.Interfaces.AppointmentInterface;
import com.werq.patient.MockData.JsonData;
import com.werq.patient.Models.pojo.AppointmentData;
import com.werq.patient.Models.pojo.AppointmentResponce;
import com.werq.patient.Models.repository.AppointmentRepository;
import com.werq.patient.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.disposables.CompositeDisposable;

public class TabAppoinmentViewModel extends BaseViewModel {

    private final AppointmentRepository appointmentRepo;
    private CompositeDisposable disposable;
    private static final String TAG = "TabAppoinmentViewModel";

    private final MutableLiveData<ArrayList<AppointmentData>> listAppointments = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    boolean isFromUpcoming;


    public TabAppoinmentViewModel(boolean isFromUpcoming) {

        appointmentRepo = new AppointmentRepository();
        disposable = new CompositeDisposable();
        this.isFromUpcoming=isFromUpcoming;
        if(isFromUpcoming)
             getUpcomingData();
        else
            getHistoryData();
    }

    public MutableLiveData<ArrayList<AppointmentData>> getListAppointments() {
        return listAppointments;
    }

    public MutableLiveData<Boolean> getRepoLoadError() {
        return repoLoadError;
    }



    public void getUpcomingData()  {
        getLoading().setValue(true);
        AppointmentResponce appointmentResponce= JsonData.getUpcomingData();
        ArrayList<AppointmentData> list = new ArrayList<>();
        list.addAll(Arrays.asList(appointmentResponce.getResponse()));
        Log.e(TAG, "getUpcomingData: "+list.size());

        listAppointments.setValue(list);
        getLoading().setValue(false);


    }

    public void getHistoryData()  {
        getLoading().setValue(true);
        AppointmentResponce appointmentResponce= JsonData.getHistoryData();
        ArrayList<AppointmentData> list = new ArrayList<>();
        list.addAll(Arrays.asList(appointmentResponce.getResponse()));
        Log.e(TAG, "getHistoryData: "+list.size());
        listAppointments.setValue(list);
        getLoading().setValue(false);


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
}