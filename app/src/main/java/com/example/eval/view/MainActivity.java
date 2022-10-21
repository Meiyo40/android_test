package com.example.eval.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eval.R;
import com.example.eval.model.Vehicle;
import com.example.eval.utils.VehicleAdapter;
import com.example.eval.viewmodel.VehicleViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvContainer;
    private TextView totalDisplay;
    private Button btnAdd;
    private Button btnDel;

    private VehicleViewModel vehicleViewModel;

    public static final int NEW_VEHICLE_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvContainer = findViewById(R.id.rvContainer);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnEmpty);
        totalDisplay = findViewById(R.id.main_total_cart);

        final VehicleAdapter adapter = new VehicleAdapter(new VehicleAdapter.VehicleDiff());
        rvContainer.setAdapter(adapter);
        rvContainer.setLayoutManager(new LinearLayoutManager(this));

        vehicleViewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
        vehicleViewModel.getVehicles().observe(this, vehicles -> {
            adapter.submitList(vehicles);
        });

        btnAdd.setOnClickListener( view -> {
            Intent addView = new Intent(MainActivity.this, AddVehicleActivity.class);
            startActivityForResult(addView, NEW_VEHICLE_ACTIVITY_REQUEST_CODE);
        });

        btnDel.setOnClickListener( view -> {
            vehicleViewModel.setTotalPrice(0);
            setPriceText();
        });

        setPriceText();
    }

    @Override
    protected void onResume() {
        super.onResume();

        totalDisplay = findViewById(R.id.main_total_cart);
        setPriceText();
        Log.d("DEBUG", "CHAUSSETTE TOTAL VALUE" + vehicleViewModel.getTotalPrice());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_VEHICLE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Vehicle vehicle = new Vehicle(
                    (String) data.getExtras().get("model"),
                    (String) data.getExtras().get("brand"),
                    Float.parseFloat(data.getExtras().get("price").toString()),
                    Integer.parseInt(data.getExtras().get("km").toString())
            );

            vehicleViewModel.insert(vehicle);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Not Saved",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void setPriceText()
    {
            String text = String.valueOf(vehicleViewModel.getTotalPrice()) + "€";
            totalDisplay.setText(text);
            setVisibility();
    }

    private void setVisibility()
    {
        if(vehicleViewModel.getTotalPrice() > 0)
        {
            totalDisplay.setVisibility(View.VISIBLE);
            btnDel.setVisibility(View.VISIBLE);
        }
        else
        {
            totalDisplay.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }
    }
}