package com.example.jodli.projektled;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        Button greenBtn1 = (Button) findViewById(R.id.greenBtn1);
        Button greenBtn2 = (Button) findViewById(R.id.greenBtn2);
        Button greenBtn3 = (Button) findViewById(R.id.greenBtn3);
        Button redBtn1 = (Button) findViewById(R.id.redBtn1);
        Button redBtn2 = (Button) findViewById(R.id.redBtn2);
        Button redBtn3 = (Button) findViewById(R.id.redBtn3);
        Button blueBtn1 = (Button) findViewById(R.id.blueBtn1);
        Button blueBtn2 = (Button) findViewById(R.id.blueBtn2);
        Button blueBtn3 = (Button) findViewById(R.id.blueBtn3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
