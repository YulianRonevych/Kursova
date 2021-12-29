package com.example.home;

public class Speaker extends Device {
    protected double VolumeDz;

    public Speaker(double powerconsumptionhour, String name, boolean onoroff, double volumeDz, int id) {
        super(powerconsumptionhour, name, onoroff, id);
        VolumeDz = volumeDz;
    }

    protected static Speaker[] addSpeaker(int n, Speaker[] arr, Speaker x)
    {
        int i;

        Speaker[] newarr = new Speaker[n + 1];

        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public double getVolumeDz() {
        return VolumeDz;
    }

    public void setVolumeDz(double volumeDz) {
        VolumeDz = volumeDz;
    }
}
