package com.example.spendsmart.models;

public class Mot {
    private double motAmount;
    private String motName;

    public Mot() {

    }

    public Mot(double motAmount, String motName) {
        this.motAmount = motAmount;
        this.motName = motName;
    }

    public double getMotAmount() {
        return motAmount;
    }

    public void setMotAmount(double motAmount) {
        this.motAmount = motAmount;
    }

    public String getMotName() {
        return motName;
    }

    public void setMotName(String motName) {
        this.motName = motName;
    }
}
