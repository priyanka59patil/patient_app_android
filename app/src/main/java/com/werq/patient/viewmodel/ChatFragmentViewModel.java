package com.werq.patient.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.werq.patient.Interfaces.ApiInterface;
import com.werq.patient.Interfaces.ApiResponce;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseViewModel;
import com.werq.patient.service.PatientRepository;
import com.werq.patient.service.model.ResponcejsonPojo.NewChat;
import com.werq.patient.service.model.ResponcejsonPojo.NewChatResponse;

import io.reactivex.disposables.CompositeDisposable;

public class ChatFragmentViewModel extends BaseViewModel {

    CompositeDisposable disposable;
    PatientRepository patientRepository;
    ApiResponce apiResponce = this;
    boolean isNewChat = false;
    MutableLiveData<String> typedMsg;
    FirebaseDatabase database;
    DatabaseReference channelIdRef;

    public ChatFragmentViewModel(Context mContext) {

/*FirebaseApp.initializeApp(mContext);
        DatabaseReference dr=FirebaseDatabase.getInstance().getReference("019cf1a3-1feb-11ea-978c-028da6c5910c");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.e( "onDataChange: ", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e( "onCancelled: ", databaseError.toString());
            }
        });*/

        patientRepository = new PatientRepository();
        isNewChat = true;
        typedMsg = new MutableLiveData<>();


    }

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


        if (!TextUtils.isEmpty(url)) {

            switch (url) {
                case "NewChat":
                    getLoading().setValue(false);
                    NewChatResponse newChatResponse=Helper.getGsonInstance().fromJson(responseJson,NewChatResponse.class);
                    Helper.setLog("NewChatResponse",newChatResponse.toString());
                    channelIdRef = FirebaseDatabase.getInstance().getReference(newChatResponse.getChannelId());
                    /*if(!TextUtils.isEmpty(newChatResponse.getChannelId())){
                        channelIdRef=database.getReference(newChatResponse.getChannelId());
                    }

                    channelIdRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            String value = dataSnapshot.getValue(String.class);
                            Helper.setLog("listen",value);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Failed to read value
                           Helper.setLog("Firebase-DatabaseError","Failed to read value."+databaseError.getMessage());
                        }
                    });*/

                    break;

                default:
                    break;
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

    public boolean isNewChat() {
        return isNewChat;
    }

    public void setNewChat(String message) {


            NewChat newChat = new NewChat();
            newChat.setMessage(message);
            getLoading().setValue(true);
            patientRepository.setNewChatRequest(Helper.autoken, newChat, getToast(), apiResponce, "NewChat");

    }

    public MutableLiveData<String> getTypedMsg() {
        return typedMsg;
    }

}
