package com.werq.patient.views.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.werq.patient.R;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.service.model.ResponcejsonPojo.Organization;

import java.util.ArrayList;

public class SpinnerArrayAdapter extends ArrayAdapter<Location> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final ArrayList<Location> items;
    private final int mResource;
    boolean referralTo=false;


    public SpinnerArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Location> objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView tvLocationName;


        tvLocationName = (TextView) view.findViewById(R.id.tvLocation);

        Location location = items.get(position);
        tvLocationName.setText(location.getAddress1()/*+ " " + location.getAddress2()*/);
        /*if (position == 0 ) {
            *//*tvLocationName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams)tvLocationName.getLayoutParams();
            layoutParams.setMargins(10, 0, 0, 10);
            tvLocationName.setLayoutParams(layoutParams);*//*
            tvLocationName.setText("Select Location");

        }
        else {
            tvLocationName.setText(location.getAddress1()*//*+ " " + location.getAddress2()*//*);

        }*/


        return view;
    }
}
