package com.example.jodli.projektlederklrt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private MainActivity myself;
    private TextView tb = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton tb1 = (ToggleButton)findViewById(R.id.LEDBtn);

        tb1.setTag(27);

        tb = (TextView)findViewById(R.id.IPView);

        myself = this;

        tb1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       ToggleButton tb = (ToggleButton)v;
                                       int pin = (int)tb.getTag();
                                       String ip = tb.getText().toString();

                                       if(tb.isChecked()) {
                                           SendRequest.LEDOn(pin, ip, myself);

                                       }else{
                                           SendRequest.LEDOff(pin,ip,myself);
                                       }
                                   }
                               });



    }
}