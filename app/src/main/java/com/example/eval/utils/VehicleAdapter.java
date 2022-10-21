package com.example.eval.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eval.model.Vehicle;
import com.example.eval.view.VehicleDetail;

public class VehicleAdapter extends ListAdapter<Vehicle, VehicleViewHolder> {

    Context ctx;

    public VehicleAdapter(@NonNull DiffUtil.ItemCallback<Vehicle> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VehicleViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Vehicle current = getItem(position);
        ctx = holder.itemView.getContext();
        holder.setVehicle(current);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetails = new Intent(ctx, VehicleDetail.class);
                intentDetails.putExtra("position_vehicle", position);

                ctx.startActivity(intentDetails);

            }
        });
    }

    public static class VehicleDiff extends DiffUtil.ItemCallback<Vehicle> {
        @Override
        public boolean areItemsTheSame(@NonNull Vehicle oldItem,@NonNull Vehicle newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Vehicle oldItem,@NonNull Vehicle newItem) {
            return oldItem.getBrand().equals(newItem.getBrand()) &&
                    oldItem.getModel().equals(newItem.getModel()) &&
                    oldItem.getKm() == newItem.getKm() &&
                    oldItem.getPrice() == newItem.getPrice();
        }
    }
}
