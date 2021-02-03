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


import com.example.myapplication.Model.SeniorMrListModel;
import com.example.myapplication.Mr_reports;
import com.example.myapplication.R;

import java.util.ArrayList;

public class SeniorMRListAdapter extends RecyclerView.Adapter<SeniorMRListAdapter.ViewHolder> {
    ArrayList<SeniorMrListModel> seniorMrListModels;
    SeniorMrListModel model;
    boolean open_details = false;
    Context context;

    public SeniorMRListAdapter(ArrayList<SeniorMrListModel> seniorMrListModels, Context context) {
        this.seniorMrListModels = seniorMrListModels;
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
        model = seniorMrListModels.get(position);
        holder.doctor_name.setText(model.getMr_name());
        holder.hospital_name.setText(model.getArea_name() + ", " + model.getCity_name());
    }

    @Override
    public int getItemCount() {
        return seniorMrListModels.size();
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
                    Intent intent = new Intent(context, Mr_reports.class);
                    intent.putExtra("mr_id", seniorMrListModels.get(getAdapterPosition()).getMr_id());
                    intent.putExtra("mr_name", seniorMrListModels.get(getAdapterPosition()).getMr_name());
                    context.startActivity(intent);
                }
            });

        }
    }


}
