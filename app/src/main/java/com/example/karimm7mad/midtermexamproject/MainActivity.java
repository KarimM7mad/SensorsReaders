package com.example.karimm7mad.midtermexamproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public Button selectBtn;
    public String sensorChosen = "";
    public TextView txt = null;
    public Intent in = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.in = new Intent(this,resultPage.class);
        this.selectBtn = findViewById(R.id.selectBtn);
        this.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.putExtra("sensorName",sensorChosen);
                startActivity(in);
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        this.sensorChosen = ((RadioButton) view).getText().toString();
    }


}
