package com.werq.patient.Utils;
import android.view.View;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessageHolders;
import com.werq.patient.R;
import com.werq.patient.service.model.chat.Message;

import java.text.SimpleDateFormat;


public class SingleCustomOutcomingTextMessageViewHolder
        extends MessageHolders.OutcomingTextMessageViewHolder<Message> {

    private final TextView tvName;
    private final TextView messageTime;
//    private final TextView tvdelTick;

    public SingleCustomOutcomingTextMessageViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        messageTime = (TextView)itemView.findViewById(R.id.messageTime);
//        tvdelTick = (TextView)itemView.findViewById(R.id.deltick);

    }

    @Override
    public void onBind(Message message) {
        super.onBind(message);
        
        tvName.setVisibility(View.GONE);
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        messageTime.setText(formatDate.format(message.getCreatedAt()).toString());

    }
}
