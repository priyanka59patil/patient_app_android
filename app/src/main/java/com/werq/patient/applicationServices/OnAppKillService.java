package com.werq.patient.applicationServices;



import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.werq.patient.Utils.SessionManager;

public class OnAppKillService extends Service {
    SessionManager sessionManager;
    private Context mContext;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext=getApplicationContext();
        sessionManager = new SessionManager(mContext);
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        Log.e("onTaskRemoved","onTaskRemoved");

        sessionManager.logoutUser(mContext);
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
