package com.example.karimm7mad.midtermexamproject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

public class resultPage extends AppCompatActivity implements SensorEventListener {

    public Button backbtn = null;
    public Intent recievingIntent = null;
    public TextView sensorNameTxtview, sensorReadingtxtView;
    private SensorManager mSensorManager = null;
    private Sensor mSensor;
    public String sensorName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.sensorNameTxtview = findViewById(R.id.sensorNametxtView);
        this.sensorReadingtxtView = findViewById(R.id.readingtxtView);

        this.sensorName = this.getIntent().getStringExtra("sensorName");
        this.sensorNameTxtview.setText(this.sensorName);


        this.backbtn = findViewById(R.id.backbtn);
        this.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.initializeSensors();
    }


    public void initializeSensors() {
        this.mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (this.sensorName.equalsIgnoreCase("")) {
            Toast.makeText(this.getBaseContext(), "You didn't choose a sensor , press back", Toast.LENGTH_SHORT).show();
        } else if (this.sensorName.equalsIgnoreCase("tempreature")) {
            //choose tempreature sensor
            mSensor = this.mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        } else if (this.sensorName.equalsIgnoreCase("Proximity")) {
            //choose proximity sensor
            mSensor = this.mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        } else if (this.sensorName.equalsIgnoreCase("Light")) {
            //choose light sensor
            mSensor = this.mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        } else if (this.sensorName.equalsIgnoreCase("pressure")) {
            //choose pressure sensor
            mSensor = this.mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        }


    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        this.sensorReadingtxtView.setText(String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(this, this.mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}
