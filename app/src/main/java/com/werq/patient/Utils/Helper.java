package com.werq.patient.Utils;

import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.ActionBar;

import com.werq.patient.R;

public class Helper {
    public static void setToolbar(ActionBar supportActionBar, String title) {
       /* supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_icon_logo);*/
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

}
