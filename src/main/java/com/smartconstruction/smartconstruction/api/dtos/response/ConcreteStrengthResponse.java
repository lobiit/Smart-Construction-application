package com.smartconstruction.smartconstruction.api.dtos.response;

public class ConcreteStrengthResponse {
    final private double strength;

    public ConcreteStrengthResponse(double strength) {
        this.strength = strength;
    }

    public double getStrength() {
        return strength;
    }
}
