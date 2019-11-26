package com.werq.patient.views.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.werq.patient.Interfaces.BasicActivities;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.databinding.FragmentFilesBinding;
import com.werq.patient.service.model.ResponcejsonPojo.AttachmentResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.voghdev.pdfviewpager.library.PDFViewPager;

public class ViewFileActivity extends AppCompatActivity implements BasicActivities{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Context mContext;

    @BindView(R.id.ivImageFile)
    ZoomageView ivImageFile;

    @BindView(R.id.wvDocumentFile)
    WebView wvDocumentFile;



    String fileType="";

    ProgressDialog progressDialog;

    /*@BindView(R.id.rlViewFile)
    RelativeLayout rlViewFile;*/
    /*@BindView(R.id.ivImageFile)
    ImageView foregroundImage;
    @BindView(R.id.tvFileName)
    TextView tvFileName;
    @BindView(R.id.tvUserName)
    TextView tvUsername;*/



    AttachmentResult attachmentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Window window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        mContext=this;
        Helper.setToolbarwithCross(getSupportActionBar(),"");
        progressDialog=Helper.createProgressDialog(mContext);
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

            fileType=attachmentResult.getFileType();

            if (fileType.toLowerCase().contains("image")
                    || fileType.toLowerCase().contains("jpeg")
                    || fileType.toLowerCase().contains("png")
                    || fileType.toLowerCase().contains("jpg")) {

                wvDocumentFile.setVisibility(View.GONE);
           /*     ivImageFile.setZoomable(false);
                ivImageFile.setClickable(false);
                ivImageFile.setEnabled(false);*/

                progressDialog.show();

                Glide.with(mContext)
                        .load(attachmentResult.getResizeURL())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Helper.showToast(mContext,"Something went wrong");
                                progressDialog.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressDialog.hide();
           /*                     ivImageFile.setZoomable(true);
                                ivImageFile.setClickable(true);
                                ivImageFile.setEnabled(true);*/
                                return false;
                            }
                        }).into(ivImageFile);
            }
            else {
                progressDialog.show();
                String query = "";
                try {
                    if(attachmentResult.getFileUrl()!=null && attachmentResult.getFileUrl().length()>0)
                        query = URLEncoder.encode(attachmentResult.getFileUrl(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    Log.e( "UnsupEncException:", e.getMessage());
                    e.printStackTrace();
                }

                ivImageFile.setVisibility(View.GONE);
                wvDocumentFile.setWebChromeClient(new WebChromeClient() {
                    public void onProgressChanged(WebView view, int progress) {

                        Log.e("Progress",""+progress);
                        if(progress == 100)
                            progressDialog.hide();
                    }
                });


                if(attachmentResult.getFileUrl().length()>0)
                {
                    progressDialog.hide();
                    wvDocumentFile.loadUrl( "https://docs.google.com/viewer?url="+query);
                }
            }
            /*Glide.with(mContext).load(attachmentResult.getFileUrl()).apply(new RequestOptions()
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
                    + " " + attachmentResult.getCreatedByUser().getLastName());*/
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
