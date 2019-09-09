package com.werq.patient.Utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.R;

public class EditTextUtils {
    public static boolean isEmpty(EditText view, String error) {

        if (TextUtils.isEmpty(view.getText().toString().trim())) {
            view.setError(error);
            return true;
        }
        return false;
    }
    public static boolean isEmpty(TextInputLayout view, String error) {

        if (TextUtils.isEmpty(view.getEditText().getText().toString().trim())) {
            view.setError(error);
            return true;
        }
        return false;
    }

}
