package com.werq.patient.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.Interfaces.DiologListner;

public class DiologHelper {

   public static BottomSheetDialog createDialogFromBottom(Context context, int layout, DiologListner listner){
       BottomSheetDialog  mBottomSheetDialog = new BottomSheetDialog(context);
       View sheetView = ((Activity)context).getLayoutInflater().inflate(layout, null);
       listner.setdiologview(sheetView);
       mBottomSheetDialog.setContentView(sheetView);
       return mBottomSheetDialog;
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
