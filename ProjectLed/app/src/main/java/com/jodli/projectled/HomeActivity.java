package com.jodli.projectled;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnLiveEdit = (Button)findViewById(R.id.btnLiveEdit);
        btnLiveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent liveView = new Intent(getApplicationContext(),LiveActivity.class);
                startActivity(liveView);

            }
        });

    }
}
