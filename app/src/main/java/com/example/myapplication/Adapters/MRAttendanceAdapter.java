package com.example.myapplication.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.Model.MRModel;
import com.example.myapplication.Model.MrAttendanceModel;
import com.example.myapplication.Model.Result;
import com.example.myapplication.Mr_reports;
import com.example.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MRAttendanceAdapter extends RecyclerView.Adapter<MRAttendanceAdapter.ViewHolder> {
    ArrayList<MrAttendanceModel> mrModels;
    MrAttendanceModel model;
    boolean open_details = false;
    Context context;

    public MRAttendanceAdapter(ArrayList<MrAttendanceModel> mrModels, Context context) {
        this.mrModels = mrModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_attendance_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        model = mrModels.get(position);
        holder.item_name.setText("MR - "+model.getMr_name());
        holder.item_workplace.setText(model.getWork_place());

        holder.item_city.setText(model.getCity());
        holder.item_worktype.setText(model.getWork_type());
        holder.item_date.setText(model.getDate());

        if (model.getStatus().equals("Present")) {
            holder.item_status.setText("P");
            holder.item_status.setTextColor(context.getResources().getColor(R.color.green));
        }
        if (model.getStatus().equals("CL")) {
            holder.item_status.setText("CL");
            holder.item_status.setTextColor(context.getResources().getColor(R.color.red));
        }
        if (model.getStatus().equals("PL")) {
            holder.item_status.setText("PL");
            holder.item_status.setTextColor(context.getResources().getColor(R.color.red));
        }
        if (model.getStatus().equals("Other")) {
            holder.item_status.setText("A");
            holder.item_status.setTextColor(context.getResources().getColor(R.color.red));
        }

    }

    @Override
    public int getItemCount() {
        return mrModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_name, item_workplace, item_city, item_worktype, item_status,item_date;

        public ViewHolder(View list) {
            super(list);
            item_name = list.findViewById(R.id.item_name);
            item_workplace = list.findViewById(R.id.item_workplace);
            item_city = list.findViewById(R.id.item_city);
            item_worktype = list.findViewById(R.id.item_worktype);
            item_status = list.findViewById(R.id.item_status);
            item_date = list.findViewById(R.id.item_date);
        }
    }


}
