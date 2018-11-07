package com.example.jodli.projektled;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createNewSample = (Button) findViewById(R.id.createNewSample);
        Button loadExistingSample = (Button) findViewById(R.id.loadExistingSample);
        createNewSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createSample  = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(createSample);
            }
        });
    }
}
