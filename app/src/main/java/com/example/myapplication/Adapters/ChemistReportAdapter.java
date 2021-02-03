package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Model.DoctorReportModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChemistReportAdapter extends RecyclerView.Adapter<ChemistReportAdapter.ViewHolder> {
    ArrayList<DoctorReportModel> doctorReport;
    DoctorReportModel model;
    boolean open_details = false;

    public ChemistReportAdapter(ArrayList<DoctorReportModel> doctorReport) {
        this.doctorReport = doctorReport;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_mr_visits, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        model = doctorReport.get(position);

        holder.date.setText(model.getVisit_date());
        if (model.getVisit_status().equals("1")) {
            holder.status.setText("Done");
        } else {
            holder.status.setText("Pending");
        }

        holder.remarks.setText(model.getRemarks());
        holder.senior_name.setText(model.getSenior_name());

        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (open_details == false) {
                    holder.details.setVisibility(View.VISIBLE);
                    open_details = true;
                } else {
                    holder.details.setVisibility(View.GONE);
                    open_details = false;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorReport.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, status, show, remarks, senior_name;

        ImageView down_details;
        GridLayout gridLayout;
        LinearLayout details, mr_details;

        public ViewHolder(View list) {
            super(list);
//            gridLayout = list.findViewById(R.id.staff_rec_design);
            date = list.findViewById(R.id.date);
            status = list.findViewById(R.id.status);
            show = list.findViewById(R.id.show);

            details = list.findViewById(R.id.details);

            remarks = list.findViewById(R.id.remarks);
            senior_name = list.findViewById(R.id.senior_name);
        }
    }


}
