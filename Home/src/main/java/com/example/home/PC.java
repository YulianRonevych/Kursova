package com.example.home;

public class PC extends Device {
    protected String PCName;
    protected double RAM;
    protected String ProccesorName;

    public PC(double powerconsumptionhour, String name, boolean onoroff, String PCname, double ram, String proccesorName,int id) {
        super(powerconsumptionhour, name, onoroff,id);
        PCName = PCname;
        RAM = ram;
        ProccesorName = proccesorName;
    }

    protected static PC[] addPC(int n, PC[] arr, PC x)
    {
        int i;

        PC[] newarr = new PC[n + 1];

        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public String getPCName() {
        return PCName;
    }

    public void setPCName(String PCName) {
        this.PCName = PCName;
    }

    public double getRAM() {
        return RAM;
    }

    public void setRAM(double RAM) {
        this.RAM = RAM;
    }

    public String getProccesorName() {
        return ProccesorName;
    }

    public void setProccesorName(String proccesorName) {
        ProccesorName = proccesorName;
    }
}
