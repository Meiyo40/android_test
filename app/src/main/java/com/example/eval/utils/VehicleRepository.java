package com.example.eval.utils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.eval.dao.VehicleDAO;
import com.example.eval.model.Vehicle;

import java.util.List;

public class VehicleRepository {
    private VehicleDAO dao;
    private LiveData<List<Vehicle>> AllVehicles;

    public VehicleRepository(Application application)
    {
        VehicleRoomDatabase db = VehicleRoomDatabase.getDatabase(application);
        dao = db.vehicleDAO();
        AllVehicles = dao.getVehicles();
    }

    public LiveData<List<Vehicle>> getAllVehicles()
    {
        return AllVehicles;
    }

    public void insert(Vehicle vehicle)
    {
        VehicleRoomDatabase.databaseWriteExecutor.execute(() -> {
            dao.insert(vehicle);
        });
    }

    public void delete(Vehicle vehicle)
    {
        VehicleRoomDatabase.databaseWriteExecutor.execute(() -> {
            dao.delete(vehicle);
        });
    }

    public void update(Vehicle vehicle)
    {
        VehicleRoomDatabase.databaseWriteExecutor.execute(() -> {
            dao.update(vehicle);
        });
    }
}
