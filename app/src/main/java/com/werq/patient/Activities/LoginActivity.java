package com.werq.patient.Activities;

import android.os.Bundle;
;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.werq.patient.R;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.switch2)
    Switch switch2;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

}
