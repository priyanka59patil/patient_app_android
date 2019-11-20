package com.werq.patient.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.werq.patient.views.ui.LoginActivity;

public class SessionManager {

    public static SharedPreferences pref, userPref;

    public static SharedPreferences.Editor editor,userEditor;

    // Sharedpref file name
    private static final String PrefName_Login = "Login";
    private static final String PrefName_RememberUsername = "RememberUsername";


    public static final String userid = "userid";
    public static final String username = "username";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String token = "accessToken";
    public static final String idToken = "idToken";
    public static final String timeStamp = "timeStamp";


    public static final String REMEMBER_USERNAME = "RememberUsername";
    public static final String rem_username = "rem_username";


    public static final String REMEMBER_PASSWORD = "RememberPassword";
    public static final String rem_password = "rem_password";


    Context context;
    static int PRIVATE_MODE = 0;

    public static SessionManager sessionManager;


    private synchronized static void createInstance(Context context){
        if(sessionManager==null){
            sessionManager=new SessionManager(context);

        }
    }

    public static SessionManager getSessionManager(Context context){
        if(sessionManager==null)
        {
            createInstance(context);
        }

        return sessionManager;
    }
    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PrefName_Login, PRIVATE_MODE);
        editor = pref.edit();
        userPref = context.getSharedPreferences(PrefName_RememberUsername, PRIVATE_MODE);
        userEditor = userPref.edit();
    }

   /* public SessionManager(Context context, String userRemember) {
        this.context = context;
        userPref = context.getSharedPreferences(PrefName_RememberUsername, PRIVATE_MODE);
        userEditor = userPref.edit();
    }
*/
    public void creteUserSession(String AccessToken, String IdToken, String Username, String Userid, long tmpstmp) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(token, AccessToken);
        editor.putString(idToken, IdToken);
        editor.putString(username, Username);
        editor.putString(userid, Userid);
        editor.putLong(timeStamp, tmpstmp);
        editor.commit();
    }


    public void setRememberUsername(boolean doRemember, String uname) {
        userEditor.putBoolean(REMEMBER_USERNAME, doRemember);
        userEditor.putString(rem_username, uname);
        userEditor.commit();
    }
    public void setRememberPassword(boolean doRemember, String uname) {
        userEditor.putBoolean(REMEMBER_PASSWORD, doRemember);
        userEditor.putString(rem_password, uname);
        userEditor.commit();
    }

    public static long getTimeStamp() {
        return pref.getLong(timeStamp, 0);
    }

    public boolean isRememberUsername() {
        return userPref.getBoolean(REMEMBER_USERNAME, false);
    }

    public String getRem_username() {
        return userPref.getString(rem_username, "");
    }

    public String getAuthToken() {
        return pref.getString(token, "");
    }

    public String getRefreshTokenId() {
        return pref.getString(idToken, ""); }


    public String getRem_password() {
        return userPref.getString(rem_password, "");
    }

    public boolean isRememberPassword() {
        return userPref.getBoolean(REMEMBER_PASSWORD, false);
    }






    public static String getRememberUsername() {
        return REMEMBER_USERNAME;
    }

    public static String getRememberPassword() {
        return REMEMBER_PASSWORD;
    }

    public void logoutUser(Context context) {

        clear();
        editor.putBoolean(IS_LOGIN, false);
        editor.commit();

            Intent i = new Intent(context, LoginActivity.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
           // Helper.startLogin = true;

    }


    public void clear() {
        editor.clear();
        editor.commit();

    }
}
