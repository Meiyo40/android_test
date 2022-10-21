package com.example.eval.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.eval.R;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText vehicleBrand;
    private EditText vehicleModel;
    private EditText vehicleKm;
    private EditText vehiclePrice;
    private Button btnAddVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        vehicleBrand = findViewById(R.id.add_vehicle_brand);
        vehicleModel = findViewById(R.id.add_vehicle_model);
        vehicleKm = findViewById(R.id.add_vehicle_km);
        vehiclePrice = findViewById(R.id.add_vehicle_price);
        btnAddVehicle = findViewById(R.id.btn_vehicle_add);

        btnAddVehicle.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (hasFieldEmpty()) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String vehicle_brand = vehicleBrand.getText().toString();
                String vehicle_model = vehicleModel.getText().toString();
                String vehicle_price = vehiclePrice.getText().toString();
                String vehicle_km    = vehicleKm.getText().toString();

                replyIntent.putExtra("brand", vehicle_brand );
                replyIntent.putExtra("model", vehicle_model );
                replyIntent.putExtra("price", vehicle_price );
                replyIntent.putExtra("km", vehicle_km );

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }

    private boolean hasFieldEmpty()
    {
        return TextUtils.isEmpty(vehicleBrand.getText())
                || TextUtils.isEmpty(vehicleKm.getText())
                || TextUtils.isEmpty(vehicleModel.getText())
                || TextUtils.isEmpty(vehiclePrice.getText());
    }
}