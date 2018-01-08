package com.example.android.bluetoothlegatt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
    Button btnBluetoothConnect, btnShowStatistics, btnTutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        btnBluetoothConnect = (Button)findViewById(R.id.btnBluetoothConnect);
        btnShowStatistics = (Button)findViewById(R.id.btnShowStatistics);
        btnTutorial = (Button)findViewById(R.id.btnTutorialConnect);

        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), tutorialActivity.class);
                startActivity(intent);
            }
        });


        btnBluetoothConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeviceScanActivity.class);
                startActivity(intent);
            }
        });

        btnShowStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
                startActivity(intent);
            }
        });
    }
}
