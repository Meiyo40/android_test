package com.example.eval.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vehicle")
public class Vehicle {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo
    private String model;

    @NonNull
    @ColumnInfo
    private String brand;

    @ColumnInfo
    private float price;

    @ColumnInfo
    private int km;

    public Vehicle(@NonNull String model, @NonNull String brand, float price, int km) {
        this.model = model;
        this.brand = brand;
        this.price = price;
        this.km = km;
    }

//    public Vehicle(int id, @NonNull String model, @NonNull String brand, float price, int km) {
//        this.id = id;
//        this.model = model;
//        this.brand = brand;
//        this.price = price;
//        this.km = km;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    @NonNull
    public String getBrand() {
        return brand;
    }

    public void setBrand(@NonNull String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
