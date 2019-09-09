package com.werq.patient.Utils;

import androidx.databinding.InverseMethod;

public class MyConversions {
    @InverseMethod("myBox")
    public static boolean myUnbox(Boolean b) {
        return (b != null) && b.booleanValue();
    }

    public static Boolean myBox(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }
}
