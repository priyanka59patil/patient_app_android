package com.werq.patient.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.Adapters.FilesAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.Models.Files;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ViewVisitNoteActivity extends AppCompatActivity implements RecyclerViewClickListerner {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivDoctorProfile)
    CircleImageView ivDoctorProfile;
    @BindView(R.id.tvUsername)
    TextView tvUsername;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.tvTextNote)
    TextView tvTextNote;
    @BindView(R.id.cvNote)
    CardView cvNote;
    @BindView(R.id.tvTextAttachedFiles)
    TextView tvTextAttachedFiles;
    @BindView(R.id.rvFiles)
    RecyclerView rvFiles;
    private ArrayList<Files> allFiles;
    private FilesAdapter filesAdapter;
    private Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_visit_note);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Helper.setToolbarwithBack(getSupportActionBar(),"Visit Note");
        initializevariables();
        setAdapters();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_forword, menu);
        return super.onCreateOptionsMenu(menu);
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
    private void setAdapters() {
        rvFiles.setLayoutManager(new LinearLayoutManager(mContext));
        rvFiles.setAdapter(filesAdapter);
    }

    private void initializevariables() {
       //context
        mContext=this;

        //data
        allFiles = getFilesData();

        //listner
        recyclerViewClickListerner=this::onclick;

        //adapters
        filesAdapter = new FilesAdapter(mContext, allFiles,recyclerViewClickListerner);

    }

    private ArrayList<Files> getFilesData() {
        ArrayList<Files> files = new ArrayList<>();
       /* Files file = new Files(R.drawable.imageone, "image", "Image-Attachment-01.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file1 = new Files(R.drawable.imagetwo, "image", "Image-Attachment-02.jpg", "receiver", "jeffery Crippin", "Yesterday 02:12:32 PM");
        Files file2 = new Files(R.drawable.imagetwo, "pdf", "Intro-part-02.pdf", "sender", "David Crippin", "Yesterday 02:12:32 PM");
        files.add(file);
        files.add(file1);
        files.add(file2);*/
        return files;
    }

    @Override
    public void onclick(int position) {

    }
}
