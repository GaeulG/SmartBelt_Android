package com.example.android.bluetoothlegatt;

/**
 * Created by kobot on 2017-10-10.
 */

public class ListItem {

    private String[] mData;

    public ListItem(String[] data ){


        mData = data;
    }

    public ListItem(String id, String casenum, String year, String month, String day, String hour, String minute, String second){

        mData = new String[8];
        mData[0] = id;
        mData[1] = casenum;
        mData[2] = year;
        mData[3] = month;
        mData[4] = day;
        mData[5] = hour;
        mData[6] = minute;
        mData[7] = second;
    }

    public String[] getData(){
        return mData;
    }

    public String getData(int index){
        return mData[index];
    }

    public void setData(String[] data){
        mData = data;
    }



}