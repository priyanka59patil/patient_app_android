package com.werq.patient.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.werq.patient.MockData.JsonData;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.model.AppointmentData;
import com.werq.patient.service.model.Files;
import com.werq.patient.service.model.FilesData;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatInfoViewModel extends BaseViewModel {

    MutableLiveData<ArrayList<Files>> filesList;
    //MutableLiveData<Boolean> visibility;

    public ChatInfoViewModel() {
        filesList=new MutableLiveData<>();
       // visibility=new MutableLiveData<>();
        prepareData();
    }

    public MutableLiveData<ArrayList<Files>> getFilesList() {
        return filesList;
    }

   /* public MutableLiveData<Boolean> getVisibility() {
        return visibility;
    }*/

    public void prepareData()
    {

        FilesData filesData= JsonData.getFilesData();
        ArrayList<Files> filesArrayList=new ArrayList<>();
        filesArrayList.addAll(Arrays.asList(filesData.getResponse()));
        filesList.setValue(filesArrayList);

        /*if(filesArrayList.size()>0){
            visibility.setValue(true);
        }
        else {
            visibility.setValue(false);
        }*/
    }
}
