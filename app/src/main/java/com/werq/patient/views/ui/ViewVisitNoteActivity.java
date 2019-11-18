package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.databinding.ActivityViewVisitNoteBinding;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.viewmodel.ViewVisitNoteViewModel;
import com.werq.patient.views.adapter.AttachmentsAdapter;
import com.werq.patient.views.adapter.FilesAdapter;
import com.werq.patient.Interfaces.RecyclerViewClickListerner;
import com.werq.patient.MockData.JsonData;
import com.werq.patient.service.model.Files;
import com.werq.patient.service.model.FilesData;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ViewVisitNoteActivity extends BaseActivity implements RecyclerViewClickListerner {

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
    @BindView(R.id.cvNoAttachments)
    CardView cvNoAttachments;
    private ArrayList<AttachmentResult> allFiles;
    private AttachmentsAdapter filesAdapter;
    private Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;

    ActivityViewVisitNoteBinding viewVisitNoteBinding;
    ViewVisitNoteViewModel viewModel;
    int appointmentId;
    int visitNoteId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_visit_note);
        viewVisitNoteBinding = DataBindingUtil.setContentView(this,R.layout.activity_view_visit_note);
        mContext=this;
        viewVisitNoteBinding.setLifecycleOwner(this);
        viewModel= ViewModelProviders.of(this).get(ViewVisitNoteViewModel.class);
        viewVisitNoteBinding.setViewVnViewModel(viewModel);
        viewModel.setAuthToken(SessionManager.getSessionManager(mContext).getAuthToken());
        viewModel.setRefreshTokenId(SessionManager.getSessionManager(mContext).getRefreshTokenId());
        appointmentId=getIntent().getIntExtra("appointmentId",0);
        visitNoteId=getIntent().getIntExtra("visitNoteId",0);
        Helper.setLog("getIntent",appointmentId+" "+visitNoteId );
    /*    viewModel.setAppointmentId(appointmentId);
        viewModel.setVisitNoteId(visitNoteId);*/
        viewModel.setUrlRequest(appointmentId,visitNoteId);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Helper.setToolbarwithBack(getSupportActionBar(),"Visit Note");
        initializevariables();


    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getRvAttachmentsVisibility().observe(this, aBoolean -> {
            if(aBoolean)
            {
                rvFiles.setVisibility(View.VISIBLE);
                cvNoAttachments.setVisibility(View.GONE);
            }
            else {
                rvFiles.setVisibility(View.GONE);
                cvNoAttachments.setVisibility(View.VISIBLE);
            }

        });

        viewModel.profileUrl.observe(this,s -> {
            Glide.with(mContext).load(s).apply(new RequestOptions()
                    .placeholder(R.drawable.user_image_placeholder)
                    .error(R.drawable.user_image_placeholder).skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ivDoctorProfile);
        });
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

    private void initializevariables() {

        allFiles=new ArrayList<>();
        recyclerViewClickListerner=this::onclick;
        filesAdapter = new AttachmentsAdapter(mContext, allFiles,recyclerViewClickListerner,viewModel,this);
        rvFiles.setLayoutManager(new LinearLayoutManager(mContext));
        rvFiles.setAdapter(filesAdapter);
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
