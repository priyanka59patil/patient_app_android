package com.werq.patient.Utils;

import android.os.Build;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingUtils {

    @BindingAdapter({"bind:titleText"})
    public static void setToolbarTitle(Toolbar view, String title) {
        view.setTitle(title);
    }
}
