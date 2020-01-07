package com.werq.patient.Utils;

import android.view.View;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessageHolders;
import com.werq.patient.R;
import com.werq.patient.service.model.chat.Message;

import java.text.SimpleDateFormat;


/*
 * Created by troy379 on 05.04.17.
 */
public class SingleCustomIncomingImageMessageViewHolder
        extends MessageHolders.IncomingImageMessageViewHolder<Message> {

    private final TextView messageTime;
    private TextView tvName;

    public SingleCustomIncomingImageMessageViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        messageTime = (TextView)itemView.findViewById(R.id.messageTime);
    }

    @Override
    public void onBind(Message message) {
        super.onBind(message);

        tvName.setVisibility(View.GONE);
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        messageTime.setText(formatDate.format(message.getCreatedAt()).toString());

    }
}
