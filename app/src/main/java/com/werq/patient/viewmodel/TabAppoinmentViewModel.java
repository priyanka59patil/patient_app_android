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
import com.werq.patient.Utils.DateHelper;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.AppointmentResponce;
import com.werq.patient.service.model.RequestJsonPojo.ConfirmAppointment;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentDetailResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResponse;
import com.werq.patient.service.model.ResponcejsonPojo.AppointmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.model.ResponcejsonPojo.LoginResponce;
import com.werq.patient.service.repository.AppointmentRepository;
import com.werq.patient.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.internal.http2.ErrorCode;

public class TabAppoinmentViewModel extends BaseViewModel {

    private static final String TAG = "TabAppoinmentViewModel";
    public static Boolean toConfirmChanged=false;
    private final AppointmentRepository appointmentRepository;
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    public MutableLiveData<ArrayList<AppointmentResult>> listUpcommingAppointments;
    public MutableLiveData<ArrayList<AppointmentResult>> listHistoryAppointments;
    public MutableLiveData<Boolean> upcommingloading;
    public MutableLiveData<Boolean> historyloading;
    public MutableLiveData<Boolean> apptDetailsloading;
    public MutableLiveData<List<AttachmentResult>> attachmentList;
    public MutableLiveData<AppointmentResult> appointmentResultData;
    public MutableLiveData<Boolean> attachmentVisibility;
    public MutableLiveData<String> doctorProfilePhoto;
    public MutableLiveData<String> appointmentNote;
    boolean isFromUpcoming = true;
    MutableLiveData<String> day, month, time, fullUserName, speciality, address, addressOnMap;
    //int appointmentId;
    String authToken;
    String refreshTokenId;
    ApiResponce apiResponce = this;
    int upcommingPageNo = 0;
    int historyPageNo = 0;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> rvVisibility;
    private MutableLiveData<Boolean> rvHistoryVisibility;
    private MutableLiveData<Boolean> scheduleDetailsVisibility;

    public TabAppoinmentViewModel() {
        appointmentRepository = new AppointmentRepository();
    }

    public TabAppoinmentViewModel(boolean isFromUpcoming) {

        appointmentRepository = new AppointmentRepository();
        disposable = new CompositeDisposable();
        this.isFromUpcoming = isFromUpcoming;
        rvVisibility = new MutableLiveData<>();
        listUpcommingAppointments = new MutableLiveData<>();
        listHistoryAppointments = new MutableLiveData<>();
        rvHistoryVisibility = new MutableLiveData<>();

        day = new MutableLiveData<>();
        month = new MutableLiveData<>();
        time = new MutableLiveData<>();
        fullUserName = new MutableLiveData<>();
        speciality = new MutableLiveData<>();
        address = new MutableLiveData<>();
        addressOnMap = new MutableLiveData<>();
        day = new MutableLiveData<>();
        attachmentVisibility = new MutableLiveData<Boolean>();
        //confirmByPatient=new MutableLiveData<Boolean>();
        //appointmentStatus =new MutableLiveData<>();
        attachmentVisibility.setValue(false);
        attachmentList = new MutableLiveData<>();
        doctorProfilePhoto = new MutableLiveData<>();
        scheduleDetailsVisibility = new MutableLiveData<>();

        //toolbarTitle =new MutableLiveData<>();
        appointmentResultData = new MutableLiveData<>();
        upcommingloading = new MutableLiveData<>();
        historyloading = new MutableLiveData<>();
        apptDetailsloading = new MutableLiveData<>();
        appointmentNote = new MutableLiveData<>();

    }

   /* public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
        getAppointmentData(appointmentId);

    }*/

    public MutableLiveData<Boolean> getRepoLoadError() {
        return repoLoadError;
    }


    public MutableLiveData<Boolean> getRvVisibility() {
        return rvVisibility;
    }

    public void setRvVisibility(MutableLiveData<Boolean> rvVisibility) {
        this.rvVisibility = rvVisibility;
    }

    public MutableLiveData<ArrayList<AppointmentResult>> getListUpcommingAppointments() {
        return listUpcommingAppointments;
    }

    public MutableLiveData<ArrayList<AppointmentResult>> getListHistoryAppointments() {
        return listHistoryAppointments;
    }

    public MutableLiveData<Boolean> getRvHistoryVisibility() {
        return rvHistoryVisibility;
    }

    public MutableLiveData<String> getDay() {
        return day;
    }

    public MutableLiveData<String> getMonth() {
        return month;
    }

    public MutableLiveData<String> getTime() {
        return time;
    }

    public MutableLiveData<String> getFullUserName() {
        return fullUserName;
    }

    public MutableLiveData<String> getSpeciality() {
        return speciality;
    }

    public MutableLiveData<String> getAddress() {
        return address;
    }

    public MutableLiveData<String> getAddressOnMap() {
        return addressOnMap;
    }

    public MutableLiveData<List<AttachmentResult>> getAttachmentList() {
        return attachmentList;
    }

    public MutableLiveData<AppointmentResult> getAppointmentResultData() {
        return appointmentResultData;
    }

    public MutableLiveData<Boolean> getAttachmentVisibility() {
        return attachmentVisibility;
    }

    public MutableLiveData<String> getDoctorProfilePhoto() {
        return doctorProfilePhoto;
    }

    public MutableLiveData<Boolean> getScheduleDetailsVisibility() {
        return scheduleDetailsVisibility;
    }

    public MutableLiveData<Boolean> getUpcommingloading() {
        return upcommingloading;
    }

    public MutableLiveData<Boolean> getHistoryloading() {
        return historyloading;
    }

    public MutableLiveData<Boolean> getApptDetailsloading() {
        return apptDetailsloading;
    }

    public MutableLiveData<String> getAppointmentNote() {
        return appointmentNote;
    }

    /*private void fetchRepos() {
                                    getLoading().setValue(true);
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
                                }
                            */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

    @Override
    public void onSuccess(String url, String responseJson) {

        Helper.setLog("responseJson", responseJson);
        AppointmentResponse appointmentResponce = Helper.getGsonInstance().fromJson(responseJson, AppointmentResponse.class);


        if (url != null && url.equals("UpcomingAppointment")) {
            upcommingloading.setValue(false);

            ArrayList<AppointmentResult> dataArrayList = new ArrayList<>();
            if (listUpcommingAppointments.getValue() != null && upcommingPageNo != 0) {
                dataArrayList.addAll(listUpcommingAppointments.getValue());
            }

            dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
            listUpcommingAppointments.setValue(dataArrayList);

            if (listUpcommingAppointments.getValue().size() > 0) {
                rvVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvVisibility.setValue(false);
            }
        }

        if (url != null && url.equals("HistoryAppointment")) {
            historyloading.setValue(false);
            ArrayList<AppointmentResult> dataArrayList = new ArrayList<>();
            if (listHistoryAppointments.getValue() != null && historyPageNo != 0) {
                dataArrayList.addAll(listHistoryAppointments.getValue());
            }
            dataArrayList.addAll(Arrays.asList(appointmentResponce.getData().getResult()));
            listHistoryAppointments.setValue(dataArrayList);


            if (listHistoryAppointments.getValue().size() > 0) {
                rvHistoryVisibility.setValue(true);
                //noVisitNote.setVisibility(View.GONE);

            } else {
                rvHistoryVisibility.setValue(false);
            }

        }

        if (url != null && url.equalsIgnoreCase("GetAppointmentDetails")) {

            apptDetailsloading.setValue(false);

            AppointmentDetailResponse apptDetailResponse = Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
                /*if(apptDetailResponse!=null && apptDetailResponse.getData().getVisitNoteAttachment()!=null)
                {
                    List<VisitNoteAttachment> visitNoteList=apptDetailResponse.getData().getVisitNoteAttachment();

                    List<VisitNoteAttachment> get=apptDetailResponse.getData().getVisitNoteAttachment();
                    if(visitNoteList!=null && visitNoteList.size()>0)
                    {
                        Helper.setLog("visitNoteList.size()",visitNoteList.size()+"");
                    }

                }*/

            if (apptDetailResponse != null) {
                scheduleDetailsVisibility.setValue(true);
                appointmentResultData.setValue(apptDetailResponse.getData().getAppointment());
                prepareAppointmentDetailsData();
                doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());
                attachmentList.setValue(prepareAttachmentsList(apptDetailResponse));



                if (attachmentList.getValue() != null) {
                    if (attachmentList.getValue().size() > 0) {
                        attachmentVisibility.setValue(true);

                    } else {
                        attachmentVisibility.setValue(false);
                    }
                }

            } else {
                scheduleDetailsVisibility.setValue(false);
            }


        }

        if (url != null && url.equalsIgnoreCase("ConfirmAppointment")) {
            apptDetailsloading.setValue(false);
            AppointmentDetailResponse apptDetailResponse = Helper.getGsonInstance().fromJson(responseJson, AppointmentDetailResponse.class);
            Log.e(TAG, "onSuccess: " + apptDetailResponse.getData().getAppointment().getConfirmByPatient());
            appointmentResultData.setValue(apptDetailResponse.getData().getAppointment());
            prepareAppointmentDetailsData();
            doctorProfilePhoto.setValue(apptDetailResponse.getData().getAppointment().getDoctor().getProfilePhoto());

               /* if(apptDetailResponse.getData().getAppointment().getConfirmByPatient()==true)
                {
                    appointmentStatus.setValue("Confirmed");

                }else
                {
                   appointmentStatus.setValue(apptDetailResponse.getData().getAppointment().getAppointmentStatus());

                }*/

            //confirmByPatient.setValue(true);

        }

    }

    @Override
    public void onError(String url, String errorCode, String errorMessage) {
        if (isFromUpcoming) {
            upcommingloading.setValue(false);
        } else {
            historyloading.setValue(false);
        }
    }

    @Override
    public void onTokenRefersh(String responseJson) {
        if (isFromUpcoming) {
            upcommingloading.setValue(false);
        } else {
            historyloading.setValue(false);
        }
    }

    private void prepareAppointmentDetailsData() {
        //toolbar.setValue(data.getProvider().getFirst_name() + " " + data.getProvider().getLast_name());

        //this.appointmentResult=apptDetailResponse.getData().getAppointment();
        AppointmentResult appointmentResult = appointmentResultData.getValue();
        fullUserName.setValue(appointmentResult.getDoctor().getFirstName() + " " + appointmentResult.getDoctor().getLastName());
        speciality.setValue(appointmentResult.getDoctor().getSpeciality().getName());

        try {
            Date date = DateHelper.dateFromUtc(appointmentResult.getAppintmentDate());
            day.setValue(DateHelper.dayFromDate(date, "day"));
            month.setValue(DateHelper.dayFromDate(date, "month"));
            time.setValue(DateHelper.dayFromDate(date, "time"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (appointmentResultData.getValue().getLocation() != null) {

            Location location = appointmentResult.getLocation();
            String strAddress = location.getOrganizationName() + " " + location.getAddress1() + " " + location.getCity()
                    + " " + location.getState() + " " + location.getPostalcode() + "" + location.getCountry();
            address.setValue(strAddress);

            addressOnMap.setValue(strAddress);
        }
        Log.e(TAG, "prepareData: " + appointmentResult.getConfirmByPatient());

        if (appointmentResultData.getValue().getReferralReason() != null) {

            appointmentNote.setValue(appointmentResultData.getValue().getReferralReason());

        }else {
            appointmentNote.setValue("Not Available");
        }

        // confirmByPatient.setValue(appointmentResult.getConfirmByPatient());

       /* if(isFromUpcoming)
        {
            if(appointmentResult.getConfirmByPatient()!=null && appointmentResult.getConfirmByPatient()){
                appointmentStatus.setValue("confirmed");
            }
            else {
                appointmentStatus.setValue("toconfirm");
            }
        }
        else {
            appointmentStatus.setValue(appointmentResult.getAppointmentStatus());
        }*/


        if (attachmentList.getValue() != null) {
            if (attachmentList.getValue().size() > 0) {
                attachmentVisibility.setValue(true);

            } else {
                attachmentVisibility.setValue(false);
            }
        }

    }


    public void fetchUpcomingAppointmentList(int page) {
        upcommingloading.setValue(true);
        upcommingPageNo = page;
        if (authToken != null && !authToken.isEmpty()) {
            Log.e(TAG, "getToken: " + authToken);

            appointmentRepository.getUpcommingAppoitment(authToken, "10", "" + page * 10,
                    getToast(), apiResponce, "UpcomingAppointment");
        }

    }

    public void fetchHistoryAppointmentList(int page) {
        historyloading.setValue(true);
        historyPageNo = page;
        appointmentRepository.getHistoryAppoitment(authToken, "10", "" + page * 10,
                getToast(), apiResponce, "HistoryAppointment");
    }

    public void getAppointmentData(int appointmentId) {
        apptDetailsloading.setValue(true);
        appointmentRepository.getAppointmentDetails(authToken, appointmentId, getToast(), apiResponce, "GetAppointmentDetails");
    }

    public List<AttachmentResult> prepareAttachmentsList(AppointmentDetailResponse apptDetailResponse) {
        List<AttachmentResult> attachmentResultList = new ArrayList<>();

        Helper.setLog("ReferralAttachment.size()", apptDetailResponse.getData().getAppointment().getReferralAttachment().size() + "");

        try {
            if (apptDetailResponse.getData().getAppointment().getReferralAttachment() != null) {
                attachmentResultList.addAll(apptDetailResponse.getData().getAppointment().getReferralAttachment());
            }
        } catch (Exception e) {
            Helper.setLog(TAG + " -Exception", e.getMessage());
        }

        Helper.setLog("visitnote size", apptDetailResponse.getData().getVisitNoteAttachment().size() + "");

        try {


            for (int i = 0; i < apptDetailResponse.getData().getVisitNoteAttachment().size(); i++) {

                Helper.setLog("apptDetailResponse", apptDetailResponse.getData().getVisitNoteAttachment().get(i).getAttachement().size() + "");

                List<AttachmentResult> visitNote = apptDetailResponse.getData().getVisitNoteAttachment().get(i).getAttachement();
                if (visitNote != null) {
                    attachmentResultList.addAll(visitNote);
                }


            }
        } catch (Exception e) {
            Helper.setLog(TAG + " -Exception", e.getMessage());
        }


        Helper.setLog("ReferralAttachment.size()", apptDetailResponse.getData().getAppointment().getReferralAttachment().size() + "");

        return attachmentResultList;
    }

    public void confirmButtonOnClick() {

        if (appointmentResultData.getValue().getiD() != null && appointmentResultData.getValue().getiD() != 0) {
            apptDetailsloading.setValue(true);
            ConfirmAppointment confirmAppointment = new ConfirmAppointment();
            confirmAppointment.setID(appointmentResultData.getValue().getiD());
            confirmAppointment.setConfirmByPatient("true");

            appointmentRepository.setConfirmAppointment(authToken, confirmAppointment, getToast(), apiResponce, "ConfirmAppointment");
        }
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        /*if(isFromUpcoming) {
            fetchUpcomingAppointmentList(0);
        }
        else
            fetchHistoryAppointmentList(0);*/
    }

    public String getRefreshTokenId() {
        return refreshTokenId;
    }

    public void setRefreshTokenId(String refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }
}
