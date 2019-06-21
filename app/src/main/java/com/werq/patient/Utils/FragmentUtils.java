package com.werq.patient.Utils;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.werq.patient.Interfaces.BasicFragments;

public class FragmentUtils implements BasicFragments {
    @Override
    public Fragment newInstance(Context context, Bundle bundle, Fragment fragment) {
        fragment.setArguments(bundle);
        return fragment;
    }
}
