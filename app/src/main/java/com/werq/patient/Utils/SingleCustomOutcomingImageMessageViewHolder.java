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
public class SingleCustomOutcomingImageMessageViewHolder
        extends MessageHolders.OutcomingImageMessageViewHolder<Message> {

    private final TextView tvName;
    private final TextView messageTime;
//    private final TextView tvdelTick;

    public SingleCustomOutcomingImageMessageViewHolder(View itemView) {
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
        messageTime.setText(formatDate.format(message.getCreatedAt()));

//        if (message.getIsRead()){
//            tvdelTick.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_done_all_black_18dp, 0, 0, 0);
//        }else{
//            tvdelTick.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_done_all_grey_400_18dp, 0, 0, 0);
//        }



    }
}
