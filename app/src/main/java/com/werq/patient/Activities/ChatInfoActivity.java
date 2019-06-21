package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.DoctorUserList;
import com.werq.patient.Adapters.FilesAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.Files;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.RecyclerViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatInfoActivity extends AppCompatActivity implements RecyclerViewClickListerner {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvTxtDoctor)
    TextView tvTxtDoctor;
    @BindView(R.id.tvtitledoctorteam)
    TextView tvtitledoctorteam;
    @BindView(R.id.rvDoctorTeam)
    RecyclerView rvDoctorTeam;
    @BindView(R.id.tvTitleSharedMedia)
    TextView tvTitleSharedMedia;
    Context mContext;
    @BindView(R.id.rvDoctor)
    RecyclerView rvDoctor;
    @BindView(R.id.rvSharedMedia)
    RecyclerView rvSharedMedia;
    RecyclerViewClickListerner recyclerViewClickListerner;


    //list
    ArrayList<Files> allFiles;
    private DoctorUserList doctorUserList;
    private DoctorUserList teamList;
    private FilesAdapter filesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_info);
        ButterKnife.bind(this);
        initializeVariables();
        setSupportActionBar(toolbar);
        Helper.setToolbarwithBack(getSupportActionBar(),"Chat Info");
        setAdapetForDoctorList();
        setAdapterForTeamList();
        setAdapterForFilesList();

    }

    private void setAdapterForFilesList() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvSharedMedia,filesAdapter);

    }

    private void setAdapterForTeamList() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctorTeam,teamList);

    }

    private void setAdapetForDoctorList() {
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvDoctor,doctorUserList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /*private ArrayList<Files> getFilesData() {
        ArrayList<Files> files = new ArrayList<>();
        Files file = new Files(R.drawable.imageone, "image", "Image-Attachment-01.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file1 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file2 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        files.add(file);
        files.add(file1);
        files.add(file2);
        return files;
    }*/
    private void initializeVariables() {
        //context
        mContext = this;

        //listers
        recyclerViewClickListerner=this::onclick;

        //adapters
        doctorUserList = new DoctorUserList(this, 1);

        teamList = new DoctorUserList(mContext, 7);

        //allFiles = getFilesData();

        filesAdapter = new FilesAdapter(mContext, allFiles,recyclerViewClickListerner);





    }

    @Override
    public void onclick(int position) {

    }
}
