package com.werq.patient.views.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityBookNewAppointmentBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Location;
import com.werq.patient.viewmodel.BookAppointmentViewModel;
import com.werq.patient.views.adapter.SpinnerArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class BookNewAppointmentActivity extends BaseActivity {


    ActivityBookNewAppointmentBinding activityBinding;
    BookAppointmentViewModel viewModel;
    Context mContext;
    MenuItem apply;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spOrg)
    Spinner spOrg;
    @BindView(R.id.et_selectDate)
    EditText etSelectDate;
    @BindView(R.id.et_selectTime)
    EditText etSelectTime;
    @BindView(R.id.errorSpinner)
    TextView errorSpinner;
    @BindView(R.id.errorDate)
    TextView errorDate;
    @BindView(R.id.errorTime)
    TextView errorTime;
    SpinnerArrayAdapter locationAdapter;
    ArrayList<Location> locationArrayList;
    private Calendar myCalendar = Calendar.getInstance();
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String date = new SimpleDateFormat(Helper.MMM_DD_YYYY).format(myCalendar.getTime());
            Log.e("AppointMent Date", date);
            etSelectDate.setText(date);
            viewModel.getAppointmentDate().setValue(date);

        }
    };
    final TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            String date = new SimpleDateFormat(Helper.HH_MM_a).format(myCalendar.getTime());
            etSelectTime.setText(date);
            viewModel.getAppointmentTime().setValue(date);

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        if (activityBinding == null) {
            activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_new_appointment);
        }
        viewModel = ViewModelProviders.of(this).get(BookAppointmentViewModel.class);
        activityBinding.setLifecycleOwner(this);
        setBaseViewModel(viewModel);
        activityBinding.setViewModel(viewModel);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.set_new_password));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSelectDate.setFocusable(false);
        etSelectTime.setFocusable(false);
        locationArrayList = new ArrayList<>();
        locationAdapter = new SpinnerArrayAdapter(mContext, R.layout.location_spinner, locationArrayList);
        spOrg.setAdapter(locationAdapter);

        viewModel.getLocationList().observe(this, locations -> {
            if (locations != null) {
                locationArrayList.clear();
                locationArrayList.addAll(locations);
                locationAdapter.notifyDataSetChanged();
            }
        });

        viewModel.getLocationError().observe(this, aBoolean -> {
            if (aBoolean) {
                errorSpinner.setVisibility(View.VISIBLE);

            } else {
                errorSpinner.setVisibility(View.GONE);
            }

        });

        viewModel.getDateError().observe(this, aBoolean -> {
            if (aBoolean) {
                errorDate.setVisibility(View.VISIBLE);
            } else {
                errorDate.setVisibility(View.GONE);
            }
        });

        viewModel.getTimeError().observe(this, aBoolean -> {
            if (aBoolean) {
                errorTime.setVisibility(View.VISIBLE);

            } else {
                errorTime.setVisibility(View.GONE);
            }
        });

        viewModel.getRequestStatus().observe(this,aBoolean -> {
            if(aBoolean){
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tick_menu, menu);
        apply = menu.findItem(R.id.action_check);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_check:
                viewModel.saveOnClick();
                break;

            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.et_selectDate, R.id.et_selectTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_selectDate:
                etSelectDate.setError(null);
                Helper.hideKeyboardFrom(mContext, etSelectDate);
                DatePickerDialog dg = new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dg.getDatePicker().setMinDate(System.currentTimeMillis());
                dg.show();
                viewModel.getDateError().setValue(false);
                break;
            case R.id.et_selectTime:
                etSelectTime.setError(null);
                TimePickerDialog timeDialog = new TimePickerDialog(mContext, timeSetListener
                        , myCalendar.get(Calendar.HOUR_OF_DAY)
                        , myCalendar.get(Calendar.MINUTE), true);
                timeDialog.show();
                viewModel.getTimeError().setValue(false);
                break;
        }
    }


    @OnItemSelected(R.id.spOrg)
    void setSptodrNew_click(AdapterView<?> adapterView, View view, int i, long l) {

        viewModel.getLocationError().setValue(false);
        String location=locationArrayList.get(adapterView.getSelectedItemPosition()).getAddress1();
        viewModel.getSelectedLocation().setValue(location);
    }
}
