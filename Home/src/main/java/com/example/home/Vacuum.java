package com.example.home;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Vacuum extends Device {
    protected boolean IsDry;
    protected  int Id;

    public Vacuum(double powerconsumptionhour, String name, boolean onoroff, boolean isdrycleaner, int id) {
        super(powerconsumptionhour, name, onoroff,id);
        IsDry = isdrycleaner;
    }


    public static Vacuum[] addVacuum(int n, Vacuum[] arr, Vacuum x)
    {
        int i;

        Vacuum[] newarr = new Vacuum[n + 1];

        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public boolean isIsDry() {
        return this.IsDry;
    }

    public void setIsDry(boolean isdry) {
        this.IsDry = isdry;
    }

}
