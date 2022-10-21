package com.example.eval.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eval.R;
import com.example.eval.model.Vehicle;

public class VehicleViewHolder extends RecyclerView.ViewHolder {

    private Vehicle vehicle;
    private TextView priceTv;
    private TextView brandTv;
    private TextView modelTv;
    private TextView kmTv;

    public VehicleViewHolder(@NonNull View itemView) {
        super(itemView);
        priceTv = itemView.findViewById(R.id.pricetv);
        brandTv = itemView.findViewById(R.id.brandtv);
        modelTv = itemView.findViewById(R.id.modeltv);
        kmTv    = itemView.findViewById(R.id.kmtv);
    }

    static VehicleViewHolder create (ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicle_item, parent, false);

        return new VehicleViewHolder(view);
    }

    public void setVehicle(Vehicle pVehicle)
    {
        this.vehicle = pVehicle;
        this.setKmText();
        this.setPriceText();
        this.setBrandText();
        this.setModelText();
    }

    private void setBrandText()
    {
        brandTv.setText(vehicle.getBrand());
    }

    private void setModelText()
    {
        modelTv.setText(vehicle.getModel());
    }

    private void setKmText()
    {
        String text = vehicle.getKm() + "KM";
        kmTv.setText(text);
    }

    private void setPriceText()
    {
        String text = vehicle.getPrice() + "€";
        priceTv.setText(text);
    }
}
