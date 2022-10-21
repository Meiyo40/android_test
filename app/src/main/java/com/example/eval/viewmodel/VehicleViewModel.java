package com.example.eval.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eval.model.Vehicle;
import com.example.eval.utils.VehicleRepository;
import com.example.eval.utils.VehicleRoomDatabase;

import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

    private VehicleRepository repository;
    private LiveData<List<Vehicle>> vehicles;
    private static float totalPrice = 0;

    public VehicleViewModel(@NonNull Application application) {
        super(application);
        repository = new VehicleRepository(application);
        vehicles = repository.getAllVehicles();
    }

    public float getTotalPrice()
    {
        return totalPrice;
    }

    public float setTotalPrice(float val)
    {
        totalPrice = val;
        return totalPrice;
    }

    public float addToPrice(float val)
    {
        totalPrice += val;
        return totalPrice;
    }

    public LiveData<List<Vehicle>> getVehicles() {
        return vehicles;
    }

    public void insert(Vehicle vehicle)
    {
        repository.insert(vehicle);
    }

    public void delete(Vehicle vehicle)
    {
        repository.delete(vehicle);
    }

    public void update(Vehicle vehicle)
    {
        repository.update(vehicle);
    }
}
