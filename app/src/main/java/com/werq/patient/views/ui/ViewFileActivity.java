package com.werq.patient.views.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.databinding.FragmentFilesBinding;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;
import com.werq.patient.viewmodel.AttachmentViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewFileActivity extends AppCompatActivity implements BasicActivities {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Context mContext;

    @BindView(R.id.ivImageFileMain)
    ImageView backgroundImage;

    /*@BindView(R.id.rlViewFile)
    RelativeLayout rlViewFile;*/
    @BindView(R.id.ivImageFile)
    ImageView foregroundImage;
    @BindView(R.id.tvFileName)
    TextView tvFileName;
    @BindView(R.id.tvUserName)
    TextView tvUsername;

    AttachmentResult attachmentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Helper.setToolbarwithCross(getSupportActionBar(),"");
        initializeVariables();
        getIntentData();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(attachmentResult!=null)
        {
            try{
                Helper.setLog("ViewFileActivity",attachmentResult.getFileUrl());
                Helper.setLog("ViewFileActivity",attachmentResult.getResizeURL());
            }
            catch (Exception e)
            {
                Log.e("Exception: ", e.getMessage());
            }
            Glide.with(mContext).load(attachmentResult.getFileUrl()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_image_gray_24dp)
                    .error(R.drawable.ic_image_gray_24dp)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(backgroundImage);

            backgroundImage.setAlpha(0.5f);

            Glide.with(mContext).load(attachmentResult.getFileUrl()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_image_gray_24dp)
                    .error(R.drawable.ic_image_gray_24dp)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(foregroundImage);
            tvFileName.setText(attachmentResult.getFileName());
            tvUsername.setText(attachmentResult.getCreatedByUser().getFirstName()
                    + " " + attachmentResult.getCreatedByUser().getMiddleName()
                    + " " + attachmentResult.getCreatedByUser().getLastName());
        }
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

    @Override
    public void initializeVariables() {
        mContext=this;

    }

    @Override
    public void setRecyclerView() {

    }

    @Override
    public void setView(Object data) {

    }

    @Override
    public void getIntentData() {

        attachmentResult= (AttachmentResult) getIntent().getSerializableExtra("attachmentResult");


    }

    @Override
    public void getData() {

    }

    @Override
    public void setToolbar() {

    }
}
