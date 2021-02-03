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


import com.example.myapplication.Model.SeniorModel;
import com.example.myapplication.R;
import com.example.myapplication.SeniorMRList;

import java.util.ArrayList;

public class SeniorAdapter extends RecyclerView.Adapter<SeniorAdapter.ViewHolder> {
    ArrayList<SeniorModel> seniorModels;
    SeniorModel model;
    boolean open_details = false;
    Context context;

    public SeniorAdapter(ArrayList<SeniorModel> seniorModels, Context context) {
        this.seniorModels = seniorModels;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_senior_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        model = seniorModels.get(position);
        holder.doctor_name.setText(model.getSenior_name());
        holder.emp_post.setText(model.getPost_name());


        holder.contact_number.setText(model.getContact_no());
        holder.email_id.setText(model.getEmail());
        holder.details.setVisibility(View.GONE);
        holder.mr_details.setOnClickListener(new View.OnClickListener() {
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
        return seniorModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctor_name, emp_post, contact_number, email_id, address, password;
        ImageView down_details;
        GridLayout gridLayout;
        LinearLayout details;
        TextView reports, mr_details;

        public ViewHolder(View list) {
            super(list);
//            gridLayout = list.findViewById(R.id.staff_rec_design);
            doctor_name = list.findViewById(R.id.doctor_name);
            emp_post = list.findViewById(R.id.hospital_name);
            reports = list.findViewById(R.id.reports);
            down_details = list.findViewById(R.id.down_details);
            details = list.findViewById(R.id.details);

            mr_details = list.findViewById(R.id.mr_details);


            contact_number = list.findViewById(R.id.contact_number);
            email_id = list.findViewById(R.id.email_id);
            address = list.findViewById(R.id.address);
            password = list.findViewById(R.id.password);

            reports.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SeniorMRList.class);
                    intent.putExtra("mr_id", seniorModels.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }


}
