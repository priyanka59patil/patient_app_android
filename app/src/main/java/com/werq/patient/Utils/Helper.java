package com.werq.patient.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.werq.patient.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Helper {
    public static Gson gson;
    public static String ContentType ="application/json";
    public static String refreshTokenId ;

    public static String MMM_DD_YYYY="MMM dd, yyyy";
    public static String YYYY_MM_DD="yyyy-MM-dd";
    public static String HH_MM_SS="hh:mm:ss";
    public static String HH_MM="hh:mm";
    public static String HH_MM_a="hh:mmaa";
    public static String YYYY_MM_DD_T_HH_MM_SS=YYYY_MM_DD+"'T'"+HH_MM_SS;
    public static boolean startLogin = false;

    public static String getRefreshTokenId() {
        return refreshTokenId;
    }

    public static Gson getGsonInstance(){
        if(gson==null)
            gson=new Gson();
        return gson;
    }

    public static void setLog(String tag,String value){
        try {
            Log.e(tag,value);

        }catch (Exception e){
            Helper.setExceptionLog("LogException",e);
        }

    }

    public static void setExceptionLog(String exceptionClassName,Exception e){
        Helper.setLog(exceptionClassName,e.getMessage());
    }

    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    public static void setSnackbarWithMsg(String msg, View view) {
        Snackbar snackbar = Snackbar
                .make(view, msg , Snackbar.LENGTH_SHORT)
                .setAction("Setting", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = view.getContext();
                        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + context.getPackageName()));
                        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(myAppSettings);
                    }
                });

        snackbar.show();
    }

    public static boolean hasNetworkConnection(Context context) {
        ConnectivityManager connMgr =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        return (activeInfo != null && activeInfo.isConnected());
    }


    public static void setToolbar(ActionBar supportActionBar, String title) {
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_icon_logo);
        supportActionBar.setTitle(title);

    }
    public static void setToolbarwithBack(ActionBar supportActionBar, String title) {
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(title);

    }
    public static void setToolbarwithCross(ActionBar supportActionBar, String title) {
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setTitle(title);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
    }
    public static  void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIPv4 true=return ipv4, false=return ipv6
     * @return address or empty string
     */
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            Helper.setExceptionLog("Exception",e);
            e.printStackTrace();
        } // for now eat exceptions
        return "";
    }

    public static boolean isOlderDate(String date) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat(MMM_DD_YYYY+" hh:mmaa");
        Date inputDate = sdf.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,-15);//15 min before
        cal.add(Calendar.HOUR, -2);//two hr before
        Date currentDate=cal.getTime();
        if (inputDate.before(currentDate) ) {

            return true;
        }

        return false;

    }


    public static Date parseUtcStringToDate(String utcDate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date sessionTimestamp= sdf.parse(utcDate);
        return sessionTimestamp;
    }

    public static Date convertUtcToLocale(String utcDate) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date sessionTimestamp= sdf.parse(utcDate);
        return sessionTimestamp;

    }


    public static Date currentlocalDateToUtc() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        localDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return localDateFormat.parse( simpleDateFormat.format(new Date()) );
    }



    public static boolean isTimestampExpired(long timestamp) throws ParseException {

        Date inputDate = new Date(timestamp);
        Date currentDate=currentlocalDateToUtc();

        if (inputDate.before(currentDate) || inputDate.equals(currentDate)) {

            return true;
        }

        return false;
    }

    public static boolean isValidEmail(CharSequence target) {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isValidPhone(CharSequence target) {
        Pattern pattern = Pattern.compile("^[+][0-9]{11,13}$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }
    public static boolean isValidPhoneUsername(CharSequence target) {
        Pattern pattern = Pattern.compile("^[0-9]{10,13}$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }


    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
            Helper.setExceptionLog("WindowManager.BadTokenException",e);
        }
        //dialog.setCancelable(false);
        dialog.getWindow()
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_progress_bar);
        // dialog.setMessage(Message);
        return dialog;
    }

    public static boolean isValidPassword(String password){
        Helper.setLog("passwordlength",password.trim().length()+"");
        if(password.trim().length()<8){
            return false;
        }
        String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{0,8})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidUrl(String url) {
        try {
            Pattern regex = Pattern.compile("\\b(?:(https?|ftp|file)://|www\\.)?[-A-Z0-9+&#/%?=~_|$!:,.;]*[A-Z0-9+&@#/%=~_|$]\\.[-A-Z0-9+&@#/%?=~_|$!:,.;]*[A-Z0-9+&@#/%=~_|$]", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            return regex.matcher(url).matches();
        } catch (PatternSyntaxException ex) {
            // Syntax error in the regular expression
            return false;
        }

    }


    public static boolean isNumeric(String s) {
        String pattern = "^[0-9]*$";
        return s.matches(pattern);
    }


}
