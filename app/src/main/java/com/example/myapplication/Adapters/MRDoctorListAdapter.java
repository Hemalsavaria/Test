package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Model.MR_doctor_Model;
import com.example.myapplication.Mr_doctor_visit_list_report;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MRDoctorListAdapter extends RecyclerView.Adapter<MRDoctorListAdapter.ViewHolder> {
    ArrayList<MR_doctor_Model> mr_doctor_models;
    MR_doctor_Model model;
    boolean open_details = false;
    Context context;

    public MRDoctorListAdapter(ArrayList<MR_doctor_Model> mr_doctor_models, Context context) {
        this.mr_doctor_models = mr_doctor_models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_doctor_report, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model = mr_doctor_models.get(position);
        holder.doctor_name.setText(model.getDoctor_name());
        holder.hospital_name.setText(model.getMedical_hostpital_name());
        int count_done = Integer.parseInt(model.getTotal_visit()) - Integer.parseInt(model.getRemain_visit());
        holder.counts.setText(count_done + " / " + model.getTotal_visit() + " Completed");

    }

    @Override
    public int getItemCount() {
        return mr_doctor_models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctor_name, hospital_name, show_details, counts;

        ImageView down_details;
        GridLayout gridLayout;
        LinearLayout details, mr_details;

        public ViewHolder(View list) {
            super(list);
//            gridLayout = list.findViewById(R.id.staff_rec_design);
            doctor_name = list.findViewById(R.id.doctor_name);
            hospital_name = list.findViewById(R.id.hospital_name);
            counts = list.findViewById(R.id.counts);
            show_details = list.findViewById(R.id.show_details);

            show_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Mr_doctor_visit_list_report.class);
                    intent.putExtra("mr_id", mr_doctor_models.get(getAdapterPosition()).getMr_id());
                    intent.putExtra("dr_id", mr_doctor_models.get(getAdapterPosition()).getDoctor_id());
                    context.startActivity(intent);
                }
            });

        }
    }


}
