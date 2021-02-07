package com.example.myapplication.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.MRModel;
import com.example.myapplication.Model.Result;
import com.example.myapplication.Mr_reports;
import com.example.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnalysisAdapter extends RecyclerView.Adapter<AnalysisAdapter.ViewHolder> {
    ArrayList<MRModel> mrModels;
    MRModel model;
    boolean open_details = false;
    Context context;

    public AnalysisAdapter(ArrayList<MRModel> mrModels, Context context) {
        this.mrModels = mrModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_analysis, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        model = mrModels.get(position);
        holder.doctor_name.setText(model.getMr_name());
        holder.hospital_name.setText(model.getContact_no());


    }

    @Override
    public int getItemCount() {
        return mrModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctor_name, hospital_name;
        GridLayout gridLayout;
        LinearLayout details;
        ImageView reports;

        public ViewHolder(View list) {
            super(list);
//            gridLayout = list.findViewById(R.id.staff_rec_design);
            doctor_name = list.findViewById(R.id.doctor_name);
            hospital_name = list.findViewById(R.id.hospital_name);
            reports = list.findViewById(R.id.reports);
            details = list.findViewById(R.id.details);

            reports.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Mr_reports.class);
                    intent.putExtra("mr_id", mrModels.get(getAdapterPosition()).getId());
                    intent.putExtra("mr_name", mrModels.get(getAdapterPosition()).getMr_name());
                    context.startActivity(intent);
                }
            });


        }
    }


}
