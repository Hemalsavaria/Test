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
import com.example.myapplication.Model.WorkplaceModel;
import com.example.myapplication.Mr_reports;
import com.example.myapplication.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkPlaceAdapter extends RecyclerView.Adapter<WorkPlaceAdapter.ViewHolder> {
    ArrayList<WorkplaceModel> mrModels;
    WorkplaceModel model;
    boolean open_details = false;
    Context context;
    WorkplaceInterface workplaceInterface;

    public WorkPlaceAdapter(ArrayList<WorkplaceModel> mrModels, Context context) {
        this.mrModels = mrModels;
        this.context = context;
        try {
            this.workplaceInterface = ((WorkPlaceAdapter.WorkplaceInterface) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Error");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_workplace, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        model = mrModels.get(position);
        holder.item_workplace_name.setText(model.getWorkplace_name());

    }

    public interface WorkplaceInterface {
        public void delete_workplace(String id);

      //  public void delete_workplace(String id);
    }

    @Override
    public int getItemCount() {
        return mrModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_workplace_name;

        ImageView item_delete;

        public ViewHolder(View list) {
            super(list);
//            gridLayout = list.findViewById(R.id.staff_rec_design);
            item_workplace_name = list.findViewById(R.id.item_workplace_name);
            item_delete = list.findViewById(R.id.item_delete);

            item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    workplaceInterface.delete_workplace(mrModels.get(getAdapterPosition()).getId());
                }
            });
        }





    }
}
