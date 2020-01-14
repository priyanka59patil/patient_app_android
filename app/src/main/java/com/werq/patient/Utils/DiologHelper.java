package com.werq.patient.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Interfaces.Callback.DiologListner;

public class DiologHelper {

   public static BottomSheetDialog createDialogFromBottom(Context context, int layout, DiologListner listner){
       BottomSheetDialog  mBottomSheetDialog = new BottomSheetDialog(context);
       View sheetView = ((Activity)context).getLayoutInflater().inflate(layout, null);
       listner.setdiologview(sheetView);
       mBottomSheetDialog.setContentView(sheetView);
       return mBottomSheetDialog;
   }

    public static Dialog createDialogWithLayout(Context context, int layout, DiologListner listner){
        Dialog  dilog = new Dialog(context);
        View sheetView = ((Activity)context).getLayoutInflater().inflate(layout, null);
        listner.setdiologview(sheetView);
        dilog.setContentView(sheetView);
        return dilog;
    }
    /*public static BottomSheetDialog createDialogFromBottom1(Context context, int layout, DiologListner1 listner, String currentFragment){
        BottomSheetDialog  mBottomSheetDialog = new BottomSheetDialog(context);
        View sheetView = ((Activity)context).getLayoutInflater().inflate(layout, null);
        //listner.setdiologview(sheetView,currentFragment);
        listner.setdiologview(sheetView);
        mBottomSheetDialog.setContentView(sheetView);
        return mBottomSheetDialog;
    }*/
}
