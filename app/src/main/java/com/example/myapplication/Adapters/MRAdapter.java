package com.example.myapplication.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Apis.Api;
import com.example.myapplication.Apis.ApiServices;
import com.example.myapplication.MR;
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

public class MRAdapter extends RecyclerView.Adapter<MRAdapter.ViewHolder> {
    ArrayList<MRModel> mrModels;
    MRModel model;
    boolean open_details = false;
    Context context;

    public MRAdapter(ArrayList<MRModel> mrModels, Context context) {
        this.mrModels = mrModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_mr_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        model = mrModels.get(position);
        holder.doctor_name.setText(model.getMr_name());
        holder.hospital_name.setText(model.getContact_no());

        holder.contact_number.setText(model.getContact_no());
        holder.email_id.setText(model.getEmail_id());
        holder.address.setText(model.getArea_name() + ", " + model.getCity_name());
        holder.password.setText(model.getPassword());

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
        return mrModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctor_name, hospital_name, contact_number, email_id, address, password, reports, mr_details, delete;
        GridLayout gridLayout;
        LinearLayout details;

        public ViewHolder(View list) {
            super(list);
//            gridLayout = list.findViewById(R.id.staff_rec_design);
            doctor_name = list.findViewById(R.id.doctor_name);
            hospital_name = list.findViewById(R.id.hospital_name);
            reports = list.findViewById(R.id.reports);
            details = list.findViewById(R.id.details);
            mr_details = list.findViewById(R.id.mr_details);
            delete = list.findViewById(R.id.delete);
            contact_number = list.findViewById(R.id.contact_number);
            email_id = list.findViewById(R.id.email_id);
            address = list.findViewById(R.id.address);
            password = list.findViewById(R.id.password);

            reports.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Mr_reports.class);
                    intent.putExtra("mr_id", mrModels.get(getAdapterPosition()).getId());
                    intent.putExtra("mr_name", mrModels.get(getAdapterPosition()).getMr_name());
                    context.startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(context, R.style.MyAlertDialogStyle);
                    dialog.setContentView(R.layout.dialog_comfirmation);
                    dialog.show();
                    TextView cancel = dialog.findViewById(R.id.cancel);
                    TextView comfirm = dialog.findViewById(R.id.comfirm);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    comfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            delete_mr(mrModels.get(getAdapterPosition()).getId());
                        }
                    });
                }
            });

        }
    }


    void delete_mr(String id) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.delete_mr(id);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Somethings are Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
