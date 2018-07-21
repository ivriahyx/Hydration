package com.example.a16023018.hydration;

import android.util.Log;

public class Data {
    long id;
    int cupnumber;
    //String date;

    public Data(long id, int cupnumber) {
        this.id = id;
        this.cupnumber = cupnumber;
        //this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCupnumber() {
        return cupnumber;
    }

    public void setCupnumber(int cupnumber) {
        this.cupnumber = cupnumber;
    }
/*
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
*/
    @Override
    public String toString() {
        return "" + id + "\nNumber of Cup: " + cupnumber;
        //+ "\nDate: " + date
    }

    public  void printData(){
        Log.d("Data class", toString());
    }
}
