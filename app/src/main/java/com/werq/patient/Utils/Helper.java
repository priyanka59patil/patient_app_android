package com.werq.patient.Utils;

import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.ActionBar;

import com.google.gson.Gson;
import com.werq.patient.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class Helper {
    public static Gson gson;
    public static Gson getGsonInstance(){
        if(gson==null)
            gson=new Gson();
        return gson;
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
        } catch (Exception ignored) {
            ignored.printStackTrace();
        } // for now eat exceptions
        return "";
    }
}
