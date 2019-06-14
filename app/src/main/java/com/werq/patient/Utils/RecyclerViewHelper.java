package com.werq.patient.Utils;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.R;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class RecyclerViewHelper {

  public static void setAdapterToRecylerView(Context mContext, RecyclerView recyclerView, RecyclerView.Adapter o){
      recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
      recyclerView.setHasFixedSize(false);
      recyclerView.setAdapter(o);
  }

    public static void setAdapterToStackRecylerView(Context mContext, RecyclerView recyclerView, RecyclerView.Adapter o){
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(o);
    }

    public static void setAdapterToRecylerViewwithanimation(Context mContext, RecyclerView recyclerView){
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(mContext, R.anim.layout_animatin_from_right);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

  }

}
