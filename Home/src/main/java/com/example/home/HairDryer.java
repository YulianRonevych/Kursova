package com.example.home;

public class HairDryer extends Device{
    protected double FlowSpeed;
    protected double FlowTemperature;

    public HairDryer(double powerconsumptionhour, String name, boolean onoroff, double flowSpeed, double flowTemperature, int id) {
        super(powerconsumptionhour, name, onoroff, id);
        FlowSpeed = flowSpeed;
        FlowTemperature = flowTemperature;
    }

    protected static HairDryer[] addHairDryer(int n, HairDryer[] arr, HairDryer x)
    {
        int i;

        HairDryer[] newarr = new HairDryer[n + 1];

        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public double getFlowSpeed() {
        return FlowSpeed;
    }

    public void setFlowSpeed(double flowSpeed) {
        FlowSpeed = flowSpeed;
    }

    public double getFlowTemperature() {
        return FlowTemperature;
    }

    public void setFlowTemperature(double flowTemperature) {
        FlowTemperature = flowTemperature;
    }
}
