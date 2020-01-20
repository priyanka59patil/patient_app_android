package com.werq.patient.applicationServices;



import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;

public class OnAppKillService extends Service {
    SessionManager sessionManager;
    private Context mContext;

    public OnAppKillService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mContext=getApplicationContext();
        sessionManager = new SessionManager(mContext);
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("onTaskRemoved","onTaskRemoved");
        Helper.startLogin=false;
        sessionManager.logoutUser(mContext);
        super.onTaskRemoved(rootIntent);
    }
}
