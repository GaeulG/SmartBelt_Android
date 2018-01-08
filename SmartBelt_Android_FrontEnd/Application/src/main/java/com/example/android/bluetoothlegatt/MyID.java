package com.example.android.bluetoothlegatt;

import android.app.Application;

public class MyID extends Application {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
