package com.werq.patient.views.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.werq.patient.service.model.ResponcejsonPojo.HistoryOfProcedure;
import com.werq.patient.views.ui.AllergyListActivity;
import com.werq.patient.views.ui.EncounterActivity;
import com.werq.patient.views.ui.AssessmentActivity;
import com.werq.patient.views.ui.InstructionActivity;
import com.werq.patient.views.ui.PastillnessHistoryActivity;
import com.werq.patient.views.ui.PlanOfCareActivity;
import com.werq.patient.views.ui.ProblemListActivity;
import com.werq.patient.views.ui.ProcedureHistoryActivity;
import com.werq.patient.views.ui.SocialHistoryActivity;
import com.werq.patient.views.ui.SummaryCareActivity;
import com.werq.patient.R;

import java.util.ArrayList;

/**
 * Created by nisostech on 7/2/18.
 */

public class MedicalInfoAdapter extends RecyclerView.Adapter<MedicalInfoAdapter.ChipHolder> {
    Activity context;
    ArrayList<String> titleList;
    Boolean fromSummeryCare;



    public MedicalInfoAdapter(Activity context,   ArrayList<String> titleList,Boolean fromSummeryCare) {
        this.context = context;
        this.titleList = titleList;
        this.fromSummeryCare=fromSummeryCare;
    }


    @Override
    public ChipHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_medical_info, parent, false);
        return new ChipHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChipHolder holder, int position) {
        String medical_info = titleList.get(position);

        holder.tvTitle.setText(medical_info);

        if(fromSummeryCare){


            if(medical_info.equals(context.getResources().getString(R.string.encounter))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(context, EncounterActivity.class);
                        context.startActivity(intent1);
                    }
                });
            }else if(medical_info.equals(context.getResources().getString(R.string.assesssment))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(context, AssessmentActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }else if(medical_info.equals(context.getResources().getString(R.string.instruction))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(context, InstructionActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }
            else if(medical_info.equals(context.getResources().getString(R.string.plan_of_care))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(context, PlanOfCareActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }else if(medical_info.equals(context.getResources().getString(R.string.history_of_procedure))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(context, ProcedureHistoryActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }

        }
        else {

            if(medical_info.equals(context.getResources().getString(R.string.summery_of_care))){
                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(context, SummaryCareActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }else if(medical_info.equals(context.getResources().getString(R.string.allergies))) {

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent1 = new Intent(context, AllergyListActivity.class);
                        context.startActivity(intent1);
                    }
                });
            }
            else if(medical_info.equals(context.getResources().getString(R.string.history_of_pastillness))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent1 = new Intent(context, PastillnessHistoryActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }else if(medical_info.equals(context.getResources().getString(R.string.social_history))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent1 = new Intent(context, SocialHistoryActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }else if(medical_info.equals(context.getResources().getString(R.string.problem))){

                holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent1 = new Intent(context, ProblemListActivity.class);
                        context.startActivity(intent1);
                    }
                });

            }

        }




    /*    holder.ll_item_medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                title=titleList.get(position);
                Log.e( "onClick: ", title);
                if (title.equals("Summery Of Care")) {
                    Intent intent1 = new Intent(context, SummaryCareActivity.class);
                    context.startActivityForResult(intent1, 2);
                }

                if (title.equals("Immunization And Results")) {
                    Intent intent1 = new Intent(context, ImmunizationResultsActivity.class);
                    context.startActivityForResult(intent1, 2);
                }

                if (title.equals("Functional And Cognitive Status")) {
                    Intent intent1 = new Intent(context, FunctionalActivity.class);
                    context.startActivityForResult(intent1, 2);
                }
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class ChipHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle,tvNextArrow;
        LinearLayout ll_item_medical;


        public ChipHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvNextArrow=(TextView) itemView.findViewById(R.id.tvNextArrow);
            ll_item_medical=(LinearLayout) itemView.findViewById(R.id.ll_item_medical);

            ll_item_medical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e( "onClick: ", "setOnClickListener");
                }
            });
        }

        @Override
        public void onClick(View v) {
            Log.e( "onClick: ", "View");
            switch (v.getId()) {
                case R.id.ll_item_medical:
                        Intent intent1 = new Intent(context, SummaryCareActivity.class);
                        context.startActivityForResult(intent1, 2);


                    break;


                default:

            }
        }
    }
}
