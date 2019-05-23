package com.werq.patient.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.werq.patient.R;
import com.werq.patient.Utils.EditTextUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyIdentity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTextverifyIdentity)
    TextView tvTextverifyIdentity;
    @BindView(R.id.spDob)
    EditText spDob;
    @BindView(R.id.button)
    Button button;
    Context mContext;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_identity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initlizeVariables();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            spDob.setText(monthOfYear + 1 + "/" + dayOfMonth + "/" + year);
        }

    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initlizeVariables() {
        mContext = this;
        myCalendar = Calendar.getInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @OnClick({R.id.spDob, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spDob:
                new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.button:
                if (!validation())
                    startActivity(new Intent(mContext, CreateAccountActivity.class));
                break;
        }
    }

    private boolean validation() {
        boolean isInvalid = false;

        isInvalid = EditTextUtils.isEmpty(spDob, getResources().getString(R.string.empty_dob));

        return isInvalid;
    }
}
