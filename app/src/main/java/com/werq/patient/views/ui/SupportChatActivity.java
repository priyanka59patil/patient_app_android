package com.werq.patient.views.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.base.BaseActivity;
import com.werq.patient.base.CustomBaseActivity;
import com.werq.patient.databinding.ActivitySupportChatBinding;
import com.werq.patient.viewmodel.ChatFragmentViewModel;
import com.werq.patient.views.ui.Fragments.ChatFragments;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupportChatActivity
        extends /*CustomBaseActivity<ActivitySupportChatBinding,ChatFragmentViewModel>*/BaseActivity
        implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    BottomSheetDialog mBottomSheetDialog;

    private Context mContext;
    private EditText chooseTopics;
    FragmentManager fm = getSupportFragmentManager();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_chat);
        /*ActivitySupportChatBinding activityChatRoomBinding = DataBindingUtil.setContentView(this,R.layout.activity_support_chat);
        activityChatRoomBinding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(ChatFragmentViewModel.class);
        setBaseViewModel(viewModel);
        activityChatRoomBinding.setChatViewModel(viewModel);*/
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initializeVariables();
        Helper.setToolbarwithBack(getSupportActionBar(), "Chats");
        fm.beginTransaction().add(R.id.mainLayout, new ChatFragments(), "1").commit();
       // createDialog();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.chat_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_info:
                //startActivity(new Intent(mContext, ChatInfoActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeVariables() {
        mContext = this;
    }

    private void createDialog() {
        mBottomSheetDialog = new BottomSheetDialog(mContext);
        View sheetView = getLayoutInflater().inflate(R.layout.start_conversation_dialog, null);
        chooseTopics = sheetView.findViewById(R.id.etChooseTopic);
        mBottomSheetDialog.setContentView(sheetView);
        chooseTopics.setOnClickListener(this::onClick);
    }

    /*@OnClick(R.id.btStartConversation)
    public void onViewClicked() {
        mBottomSheetDialog.show();
    }
*/
    @Override
    public void onClick(View view) {

    }
}
