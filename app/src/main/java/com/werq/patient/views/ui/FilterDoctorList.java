package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Factory.BottomTabVmFactory;
import com.werq.patient.Interfaces.FilterDoctorClickListerner;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityFilterDoctorListBinding;
import com.werq.patient.service.model.ResponcejsonPojo.Doctor;
import com.werq.patient.viewmodel.BottomTabViewModel;
import com.werq.patient.views.adapter.FilterDoctorAdapter;
import com.werq.patient.Interfaces.Callback.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterDoctorList extends BaseActivity implements FilterDoctorClickListerner {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    FilterDoctorAdapter adapter;
    Context mContext;
    FilterDoctorClickListerner recyclerViewClickListerner;
    @BindView(R.id.tvlabelallDoctors)
    TextView tvlabelallDoctors;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.cbFilter)
    CustomCheckBox cbAllDoctorFilter;
    ActivityFilterDoctorListBinding doctorListBinding;
    BottomTabViewModel bottomTabViewModel;
    ArrayList<Doctor> doctorArrayList;
    String receivedDoctors;
    boolean receivedIsAllSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_filter_doctor_list);

        doctorListBinding = DataBindingUtil.setContentView(this,R.layout.activity_filter_doctor_list);
        ButterKnife.bind(this);
        inilializeVariables();
        bottomTabViewModel= ViewModelProviders.of(this,new BottomTabVmFactory(getAuthToken())).get(BottomTabViewModel.class);
        setBaseViewModel(bottomTabViewModel);
        doctorListBinding.setLifecycleOwner(this);
        doctorListBinding.setBottomTabViewModel(bottomTabViewModel);

        receivedDoctors=getIntent().getStringExtra("selectedDoctors");
        receivedIsAllSelected=getIntent().getBooleanExtra("isAllSelected",false);
        cbAllDoctorFilter.setChecked(receivedIsAllSelected);
        setSupportActionBar(toolbar);

        setToolbar();


        if(Helper.hasNetworkConnection(mContext)){

            bottomTabViewModel.fetchFilterDoctorList(0);

        }else {
            bottomTabViewModel.getToast().setValue(mContext.getResources().getString(R.string.no_network_conection));
        }

        doctorArrayList =new ArrayList<>();
        adapter = new FilterDoctorAdapter(mContext, recyclerViewClickListerner,doctorArrayList,bottomTabViewModel,this,receivedIsAllSelected,receivedDoctors);
        setDoctorTeams();

        cbAllDoctorFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Helper.setLog("isChecked",cbAllDoctorFilter.isChecked()+"");
                if(cbAllDoctorFilter.isChecked()){
                    cbAllDoctorFilter.setChecked(false);
                    adapter.setAll(false);
                    FilterDoctorAdapter.selectedDoctorList.clear();
                    adapter.notifyDataSetChanged();
                }else {
                    cbAllDoctorFilter.setChecked(true);
                    adapter.setAll(true);
                    adapter.notifyDataSetChanged();

                }

            }
        });


    }

    private void setDoctorTeams() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,adapter);
    }

    private void setToolbar() {
        Helper.setToolbarwithCross(getSupportActionBar(), "Filter by Doctors");
    }

    private void inilializeVariables() {
        //Context
        mContext = this;

        //listner
        recyclerViewClickListerner=this;

        //adapters


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tick_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_check:
                String doctors="";
                if(cbAllDoctorFilter.isChecked()){
                    doctors="";

                }else {
                    for (int i = 0; i < FilterDoctorAdapter.selectedDoctorList.size(); i++) {
                        doctors=TextUtils.isEmpty(doctors)?FilterDoctorAdapter.selectedDoctorList.get(i).getiD()+"":
                            doctors+","+FilterDoctorAdapter.selectedDoctorList.get(i).getiD();
                    }

                }

                Helper.setLog("doctors selected",doctors);
                Helper.setLog("All selected",cbAllDoctorFilter.isChecked()+"");
              /*  bottomTabViewModel.selectedDoctors.setValue(doctors);
                Bundle bundle = new Bundle();
                bundle.putString("doctors",doctors);
                FilesFragment passId = new FilesFragment();
                passId.setArguments(bundle);
                finish();*/
                Intent intent=new Intent(this,BottomTabActivity.class);
                intent.putExtra("doctors",doctors);
                intent.putExtra("isAllSelected",cbAllDoctorFilter.isChecked());
                intent.putExtra("fromFragment","FileFragment");
                setResult(1,intent);
                finish();
                //startActivityForResult(intent,2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onclick(int position) {

    }

    @Override
    public void changeAllDoctorFlag(boolean setChecked) {

        if(cbAllDoctorFilter.isChecked()){
            cbAllDoctorFilter.setChecked(false);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
