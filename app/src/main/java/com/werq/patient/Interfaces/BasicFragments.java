package com.werq.patient.Interfaces;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public interface BasicFragments {
    public Fragment newInstance(Context context, Bundle bundle,Fragment fragment);
}
