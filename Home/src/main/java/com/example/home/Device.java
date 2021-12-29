package com.example.home;

public class Device {
    protected double Power;
    protected String Name;
    protected boolean IsOn;
    protected  int Id;

    public Device(double powerconsumptionhour, String name, boolean onoroff, int id) {
        Power = powerconsumptionhour;
        Name = name;
        IsOn = onoroff;
        Id = id;
    }

    public Device() {
        Power = 0;
        Name = null;
        IsOn = false;
    }

    protected static Device[] addDevice(int n, Device[] arr, Device x)
    {
        int i;

        Device[] newarr = new Device[n + 1];

        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public double getPower() {
        return this.Power;
    }

    public void setPower(double power) {
        this.Power = power;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public boolean isIsOn() {
        return this.IsOn;
    }

    public void setIsOn(boolean onoroff) {
        this.IsOn = onoroff;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
