package com.example.eval.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eval.model.Vehicle;

import java.util.List;

@Dao
public interface VehicleDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Vehicle vehicle);

    @Query("DELETE FROM vehicle")
    void deleteAll();

    @Delete(entity = Vehicle.class)
    void delete(Vehicle vehicle);

    @Update(entity = Vehicle.class)
    void update(Vehicle vehicle);

    @Query("SELECT * FROM vehicle")
    LiveData<List<Vehicle>> getVehicles();

    @Query("SELECT * FROM vehicle ORDER BY km")
    LiveData<List<Vehicle>> getVehiclesByKm();

}
