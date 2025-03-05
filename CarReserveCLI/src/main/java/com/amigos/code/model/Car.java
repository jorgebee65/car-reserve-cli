package com.amigos.code.model;

public class Car {
    private int id;
    private boolean electric;
    private String brand;
    private String model;

    public Car(int id, boolean electric, String brand, String model) {
        this.id = id;
        this.electric = electric;
        this.brand = brand;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isElectric() {
        return electric;
    }

    public void setElectric(boolean electric) {
        this.electric = electric;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "🚗 Car Info:" +
                "🔢 ID: " + id + ", " +
                (electric ? "⚡ Electric: Yes" : "⛽ Electric: No") + ", " +
                "🏷️ Brand: " + brand + ", " +
                "🚘 Model: " + model;
    }
}
