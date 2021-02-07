package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.WorkplaceModel;
import com.example.myapplication.Model.WorktypeModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class WorkTypeAdapter extends RecyclerView.Adapter<WorkTypeAdapter.ViewHolder> {
    ArrayList<WorktypeModel> mrModels;
    WorktypeModel model;
    boolean open_details = false;
    Context context;
    WorktypeInterface worktypeInterface;

    public WorkTypeAdapter(ArrayList<WorktypeModel> mrModels, Context context) {
        this.mrModels = mrModels;
        this.context = context;
        try {
            this.worktypeInterface = ((WorkTypeAdapter.WorktypeInterface) context);
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
        holder.item_workplace_name.setText(model.getWork_type());

    }

    public interface WorktypeInterface {
        public void delete_worktype(String id);

        //  public void edit_workplace(String id);
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
                    worktypeInterface.delete_worktype(mrModels.get(getAdapterPosition()).getId());
                }
            });
        }


    }
}
