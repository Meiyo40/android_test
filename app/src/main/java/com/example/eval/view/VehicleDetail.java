package com.example.eval.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eval.R;
import com.example.eval.model.Vehicle;
import com.example.eval.viewmodel.VehicleViewModel;

import java.util.List;

public class VehicleDetail extends AppCompatActivity {

    private TextView vehicleBrand;
    private TextView vehicleModel;
    private TextView vehiclePrice;
    private TextView vehicleKm;
    private Button btnBuy;
    private Button btnDel;
    private VehicleViewModel viewModel;
    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        vehicleBrand = findViewById(R.id.detail_vehicle_brand);
        vehicleKm = findViewById(R.id.detail_vehicle_km);
        vehicleModel = findViewById(R.id.detail_vehicle_model);
        vehiclePrice = findViewById(R.id.detail_vehicle_price);
        btnBuy = findViewById(R.id.detail_vehicle_buy);
        btnDel = findViewById(R.id.detail_vehicle_del);

        viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
        int position = getIntent().getIntExtra("position_vehicle", 0);

        viewModel.getVehicles().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(List<Vehicle> vehicles) {
                vehicle = vehicles.get(position);
                vehicleBrand.setText(vehicle.getBrand());
                vehicleModel.setText(vehicle.getModel());
                vehiclePrice.setText(String.valueOf(vehicle.getPrice()));
                vehicleKm.setText(String.valueOf(vehicle.getKm()));
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = viewModel.addToPrice(vehicle.getPrice()) > 0;

                if(!result)
                    return;

                Intent backToMain = new Intent(VehicleDetail.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.delete(vehicle);

                Intent backToMain = new Intent(VehicleDetail.this, MainActivity.class);
                startActivity(backToMain);
            }
        });
    }
}