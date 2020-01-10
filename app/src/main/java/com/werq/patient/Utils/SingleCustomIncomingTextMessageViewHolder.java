package com.werq.patient.Utils;

import android.view.View;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessageHolders;
import com.werq.patient.R;
import com.werq.patient.service.model.chat.Message;

import java.text.SimpleDateFormat;


public class SingleCustomIncomingTextMessageViewHolder
        extends MessageHolders.IncomingTextMessageViewHolder<Message> {

    private final TextView messageTime;
//    private final CircleImageView messageUserAvatar;
    private TextView tvName;

    public SingleCustomIncomingTextMessageViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        messageTime = (TextView)itemView.findViewById(R.id.messageTime);
//        messageUserAvatar= itemView.findViewById(R.id.messageUserAvatar);
    }

    @Override
    public void onBind(Message message) {
        super.onBind(message);

        tvName.setVisibility(View.VISIBLE);
        tvName.setText(message.getUser().getName());
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        messageTime.setText(formatDate.format(message.getCreatedAt()).toString());

    }
}
