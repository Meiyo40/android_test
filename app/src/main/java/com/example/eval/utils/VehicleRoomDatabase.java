package com.example.eval.utils;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.eval.dao.VehicleDAO;
import com.example.eval.model.Vehicle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Vehicle.class}, version = 1, exportSchema = false)
public abstract class VehicleRoomDatabase extends RoomDatabase{

    public abstract VehicleDAO vehicleDAO();

    private static volatile VehicleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static VehicleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VehicleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    VehicleRoomDatabase.class,
                                    "vehicle_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);

            databaseWriteExecutor.execute( () -> {
                VehicleDAO dao = INSTANCE.vehicleDAO();
                dao.deleteAll();

                Vehicle sample = new Vehicle("Puante", "Chaussette", 666, 3000);
                dao.insert(sample);
                sample = new Vehicle("Cepe", "Champimobile", 333333, 100000);
                dao.insert(sample);
            });
        }
    };
}
