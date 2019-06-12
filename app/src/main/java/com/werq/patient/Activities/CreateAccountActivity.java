package com.werq.patient.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.werq.patient.R;
import com.werq.patient.Utils.EditTextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateAccountActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvCreateAccount)
    TextView tvCreateAccount;
    @BindView(R.id.etemail)
    EditText etemail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btSignUp)
    Button btSignUp;
    @BindView(R.id.txtInputUserName)
    TextInputLayout txtInputUserName;
    @BindView(R.id.txtInputPassword)
    TextInputLayout txtInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create An Account");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.btSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btSignUp:
                if (!Validation())
                    startActivity(new Intent(this, FingerPrintActivity.class));
                break;
        }
    }

    private boolean Validation() {
        boolean isInvalid = false;
        isInvalid = EditTextUtils.isEmpty(txtInputUserName, getResources().getString(R.string.error_email));
        isInvalid = EditTextUtils.isEmpty(txtInputPassword, getResources().getString(R.string.error_password));
        return isInvalid;
    }
}
