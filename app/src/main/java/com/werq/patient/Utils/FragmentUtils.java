package com.werq.patient.Utils;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.werq.patient.Interfaces.BasicFragments;
import com.werq.patient.R;

public class FragmentUtils implements BasicFragments {
    @Override
    public Fragment newInstance(Context context, Bundle bundle, Fragment fragment) {
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Fragment addFragment(Context Context, Fragment fragment) {
        return null;
    }

}
