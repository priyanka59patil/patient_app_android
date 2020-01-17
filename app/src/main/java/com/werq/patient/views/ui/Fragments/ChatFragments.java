package com.werq.patient.views.ui.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.utils.DateFormatter;
import com.werq.patient.BR;
import com.werq.patient.Factory.ChatFragmentVmFactory;
import com.werq.patient.Interfaces.Callback.RecyclerViewClickListerner;
import com.werq.patient.R;
import com.werq.patient.Utils.Helper;
import com.werq.patient.Utils.SessionManager;
import com.werq.patient.Utils.SingleCustomIncomingImageMessageViewHolder;
import com.werq.patient.Utils.SingleCustomIncomingTextMessageViewHolder;
import com.werq.patient.Utils.SingleCustomOutcomingImageMessageViewHolder;
import com.werq.patient.Utils.SingleCustomOutcomingTextMessageViewHolder;
import com.werq.patient.base.BaseFragment;
import com.werq.patient.base.CustomBaseFragment;
import com.werq.patient.databinding.FragmentChatFragmentsBinding;
import com.werq.patient.databinding.FragmentMedicalInfoBinding;
import com.werq.patient.service.model.chat.Message;
import com.werq.patient.viewmodel.ChatFragmentViewModel;
import com.werq.patient.views.ui.SupportChatActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatFragments extends BaseFragment
        implements RecyclerViewClickListerner, MessagesListAdapter.OnLoadMoreListener,
        DateFormatter.Formatter{


    /*@BindView(R.id.rv_chats)
    RecyclerView rvChats;*/

    //ChatAdapters chatAdapters;
    Context mContext;
    RecyclerViewClickListerner recyclerViewClickListerner;
    ChatFragmentViewModel viewModel;
    @BindView(R.id.messagesList)
    MessagesList messagesList;
    @BindView(R.id.input)
    MessageInput input;
    @BindView(R.id.loadingView)
    ProgressBar loadingView;
    MessagesListAdapter<Message> messagesAdapter;
    private ImageLoader imageLoader;
    int page =0;
    int prevItemCount =0;
    private boolean loading = true;
    FragmentChatFragmentsBinding chatFragmentsBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_fragments, container, false);
        mContext = getActivity();
        FirebaseApp.initializeApp(getActivity().getApplicationContext());
        if(chatFragmentsBinding==null){
            chatFragmentsBinding= FragmentChatFragmentsBinding.bind(view);
        }
        chatFragmentsBinding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this,new ChatFragmentVmFactory(getAuthToken())).get(ChatFragmentViewModel.class);
        setBaseViewModel(viewModel);
        chatFragmentsBinding.setChatViewModel(viewModel);
        viewModel.setSessionManager(SessionManager.getSessionManager(mContext));
        ButterKnife.bind(this, view);
        inilizeVariables();

        initImageLoader();

        initAndSetMessageAdapter();

        viewModel.fetchInitialChat();

        viewModel.getMessageList().observe(this,messages -> {

            if(messages.size()>0){

                for (int i = 0; i < messages.size(); i++) {
                    Helper.setLog("chat obj initial",messages.get(i).toString());
                }

                if(page==0){
                    messagesAdapter.clear();
                }

                messagesAdapter.addToEnd(messages, true);
                messagesAdapter.notifyDataSetChanged();

                viewModel.getLastMessageTimestamp().setValue(Long.valueOf(messages.get(0).getId()));
                Helper.setLog("last msg timestamp",messages.get(0).getId()+"");
                viewModel.getFirstMessageTimestamp().setValue(Long.valueOf(messages.get(messages.size()-1).getId()));


            }

        });


        viewModel.getMessageListAfter().observe(this, messages -> {

            if(messages.size()>0){

                for (int i = 0; i < messages.size(); i++) {

                    Helper.setLog("chat obj after",messages.get(i).toString());
                    messagesAdapter.addToStart(messages.get(i),true);
                    messagesAdapter.notifyDataSetChanged();
                }

                Helper.setLog("chat obj after last msg timestamp",messages.get(messages.size()-1).getId()+"");
                viewModel.getLastMessageTimestamp().setValue(Long.valueOf(messages.get(messages.size()-1).getId()));


            }

        });

        viewModel.getMessageListBefore().observe(this,messages -> {

            if(messages.size()>0){

                for (int i = 0; i < messages.size(); i++) {
                    Helper.setLog("chat obj before",messages.get(i).toString());
                }

                messagesAdapter.addToEnd(messages, true);
                messagesAdapter.notifyDataSetChanged();

                Helper.setLog("chat obj before first msg timestamp",messages.get(messages.size()-1).getId()+"");
                viewModel.getFirstMessageTimestamp().setValue(Long.valueOf(messages.get(messages.size()-1).getId()));

            }
        });

        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence inputString) {
                if (Helper.hasNetworkConnection(mContext)) {

                    if (!inputString.toString().trim().isEmpty()) {

                        if(!viewModel.isFirstMsgSent()){

                            viewModel.getTypedMsg().setValue(inputString.toString().trim());
                            viewModel.setNewChat(inputString.toString().trim());
                            input.getInputEditText().setText("");

                        }
                        else {

                            viewModel.getTypedMsg().setValue(inputString.toString().trim());
                            viewModel.sendMessageToServer(inputString.toString().trim());
                            input.getInputEditText().setText("");
                        }

                    }

                } else {
                    Helper.showToast(mContext, getString(R.string.no_network_conection));
                    return false;
                }

                return true;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void inilizeVariables() {

        recyclerViewClickListerner = this;
      /*  chatAdapters=new ChatAdapters(mContext,true,recyclerViewClickListerner,viewModel,this);
        RecyclerViewHelper.setAdapterToRecylerView(mContext,rvChats,chatAdapters);
        RecyclerViewHelper.setAdapterToRecylerViewwithanimation(mContext,rvChats);*/

    }


    @Override
    public void onclick(int position) {
        startActivity(new Intent(mContext, SupportChatActivity.class));

    }

    public void initAndSetMessageAdapter() {

        MessageHolders holdersConfig;
        holdersConfig = new MessageHolders()
                .setIncomingTextConfig(
                        SingleCustomIncomingTextMessageViewHolder.class,
                        R.layout.incoming_chat_layout)
                .setOutcomingTextConfig(
                        SingleCustomOutcomingTextMessageViewHolder.class,
                        R.layout.outcoming_chat_layout)
                .setIncomingImageConfig(
                        SingleCustomIncomingImageMessageViewHolder.class,
                        R.layout.incoming_image_layout)
                .setOutcomingImageConfig(
                        SingleCustomOutcomingImageMessageViewHolder.class,
                        R.layout.outcoming_image_layout);



        messagesAdapter = new MessagesListAdapter<>(SessionManager.getUserId(), holdersConfig, imageLoader);
        messagesAdapter.setDateHeadersFormatter(this);
        messagesAdapter.setLoadMoreListener(this);
        messagesList.setAdapter(messagesAdapter);


        messagesAdapter.setOnMessageClickListener(new MessagesListAdapter.OnMessageClickListener<Message>() {
            @Override
            public void onMessageClick(Message message) {

                //Log.e(TAG, "onMessageClick: " + message.toString());
                if (message.getMediaType() == null && message.getText() != null) {
                    String messageText = message.getText();
                    if (messageText.length() == 10 && Helper.isNumeric(messageText) && !messageText.startsWith("0")) {
                        Helper.setLog("ChatMsgClick","onMessageClick: this is contact no");
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + messageText.trim()));

                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                            Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CALL_PHONE).withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    // permission is granted
                                    mContext.startActivity(callIntent);
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {
                                    // check for permanent denial of permission
                                    if (response.isPermanentlyDenied()) {

                                        Helper.setSnackbarWithMsg("Phone access is needed to make call",input );
                                    }
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();

                            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                        }
                        else {

                            mContext.startActivity(callIntent);
                        }

                    } else if (Helper.isValidUrl(messageText)) {
                            if(!messageText.startsWith("http") )
                               messageText="http://"+messageText;

                        Uri uri;
                        if (messageText.contains("http://") || messageText.contains("https://")) {
                            uri = Uri.parse(messageText); // missing 'http://' will cause crashed
                        } else {
                            uri = Uri.parse("http://" + messageText); // missing 'http://' will cause crashed

                        }
                        Helper.setLog("ChatMsgClick","onMessageClick:"+ uri.getPath());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        mContext.startActivity(intent);

                    }

                }
                /*if (message.getImageUrl() != null) {
                    if (message.getMediaType().equals("Img")) {
                        messageIntent(ViewPhoto.class, message);
                    } else if (message.getMediaType().equals("unsup")) {
                        Helper.makeToast(mContext, "Sorry, this file is not supported.");
                    } else {
                        messageIntent(DisplayAttachments.class, message);
                    }

                } else {
                    if (message.getMediaType() != null)
                        Helper.makeToast(mContext, "Please wait, the Attachment is loading.");
                }*/

            }
        });
    }

    public void initImageLoader() {
        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(final ImageView imageView, final String url, @Nullable Object payload) {
                imageView.setImageResource(android.R.color.transparent);
                String type, imgurl;


                if (url.contains("##*&*")) {
                    type = url.substring(0, url.indexOf("##*&*"));
                    imgurl = url.substring(url.indexOf("##*&*") + 5);
                } else {
                    type = url;
                    imgurl = url;
                }

                setImageViewLayout(imageView, R.drawable.user_image_placeholder);

               /* if (url.contains("/profile/photo")) {
                    Helper.glideImage(mContext, url, imageView, getResources().getDrawable(R.drawable.user_image_placeholder));
                    // Picasso.with(mContext).load(url).placeholder(R.drawable.user_image_placeholder).error(R.drawable.user_image_placeholder).into(imageView);
                } else {
                    if (type.equals("Pdf")) {
                        setImageViewLayout(imageView, R.drawable.ic_pdf);
                    } else if (type.equals("Doc")) {
                        setImageViewLayout(imageView, R.drawable.ic_doc);
                    } else if (type.equals("unsup")) {
                        setImageViewLayout(imageView, R.drawable.ic_file_unsupported);
                    } else {
                        byte[] decodedString = Base64.decode(type, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        int height = decodedByte.getHeight();
                        int width = decodedByte.getWidth();

                        if (height > width) {
                            float scale = (float) height / width;
                            height = 350;
                            width = Math.round(height / scale);
                        } else {
                            float scale = (float) width / height;
                            width = 350;
                            height = Math.round(width / scale);
                        }

                        imageView.requestLayout();
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(getWidthLayoutParam(200), getHeightLayoutParam(140)));
                        imageView.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, width, height, false));
                        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodedByte, width, height, false));

                        if (url.contains("##*&*")) {
                            Picasso.with(mContext).load(imgurl).placeholder(d).into(imageView);
                        }
                    }
                }*/
            }
        };
    }

    public void setImageViewLayout(ImageView imageView, int icon) {
        imageView.requestLayout();
        imageView.setLayoutParams(new LinearLayout.LayoutParams(getWidthLayoutParam(140), getHeightLayoutParam(160)));
        imageView.setImageResource(icon);
    }

    public int getWidthLayoutParam(int width) {
        final float sc = getResources().getDisplayMetrics().density;
        return (int) (width * sc + 0.5f);
    }

    public int getHeightLayoutParam(int height) {
        final float sc = getResources().getDisplayMetrics().density;
        return (int) (height * sc + 0.5f);
    }

    @Override
    public void onLoadMore(int page, int totalItemsCount) {

        Helper.setLog("onLoadMore-page",page+"");
        Helper.setLog("onLoadMore-last api total count totalItemsCount",viewModel.getRemainingHistoryMsgCount()+"");

        if(viewModel.getRemainingHistoryMsgCount()>5){

            Helper.setLog("onLoadMore-page",viewModel.getFirstMessageTimestamp().getValue()+"");
            viewModel.fetchBeforeChat(viewModel.getFirstMessageTimestamp().getValue());

        }

    }

    public void addOnScrollListner() {

        messagesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Helper.hideKeyboardFrom(mContext,input);
            }
        });
    }

    @Override
    public String format(Date date) {
        return null;
    }
}
