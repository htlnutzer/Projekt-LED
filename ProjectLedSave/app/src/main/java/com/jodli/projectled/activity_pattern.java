package com.jodli.projectled;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Debug;
import android.os.PatternMatcher;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jodli.projectled.connectivity.RpcHandler;
import com.jodli.projectled.storage.LedPattern;

import java.util.Map;

public class activity_pattern extends AppCompatActivity {

    private TextView tb = null;
    public activity_pattern myself;

    private LedPattern pattern = null;

    SharedPreferences sharedPreferences;

    ToggleButton tb1;
    ToggleButton tb2;
    ToggleButton tb3;
    ToggleButton tb4;
    ToggleButton tb5;
    ToggleButton tb6;
    ToggleButton tb7;
    ToggleButton tb8;
    ToggleButton tb9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);
         tb1 = (ToggleButton)findViewById(R.id.tb1);
         tb2 = (ToggleButton)findViewById(R.id.tb2);
         tb3 = (ToggleButton)findViewById(R.id.tb3);
         tb4 = (ToggleButton)findViewById(R.id.tb4);
         tb5 = (ToggleButton)findViewById(R.id.tb5);
         tb6 = (ToggleButton)findViewById(R.id.tb6);
         tb7 = (ToggleButton)findViewById(R.id.tb7);
         tb8 = (ToggleButton)findViewById(R.id.tb8);
         tb9 = (ToggleButton)findViewById(R.id.tb9);

         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Load pattern
        pattern = LoadPattern();
        Log.d("LEDVAL","PATTERN LOAD FINISHED: "+pattern.toString());

        Button send = (Button) findViewById(R.id.btnSend);
        Button save = (Button) findViewById(R.id.btnSave);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleButton btn = (ToggleButton)v;
                //anhang Pbjekt abrufen und zurück zu int konvertiren weil set Tag nimmt Objekt als Parameter mit
                String ip = tb.getText().toString();

                for (Map.Entry<Integer,Boolean> x: pattern.Leds.entrySet()) {
                    if(x.getValue()){
                        RpcHandler.CallRPCLedOn(ip,x.getKey(),myself);
                    }else{
                        RpcHandler.CallRPCLedOff(ip,x.getKey(),myself);
                    }
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePattern();
            }
        });

        /*
        //  Numbers here contain the pins...
        */
        //anhang zu Button hinzufügen mit setTag
        tb1.setTag(2);
        tb2.setTag(3);
        tb3.setTag(4);
        tb4.setTag(17);
        tb5.setTag(27);
        tb6.setTag(22);
        tb7.setTag(10);
        tb8.setTag(9);
        tb9.setTag(11);

        activity_pattern.ClickListener clickListener = new activity_pattern.ClickListener();

        tb1.setOnClickListener(clickListener);
        tb2.setOnClickListener(clickListener);
        tb3.setOnClickListener(clickListener);
        tb4.setOnClickListener(clickListener);
        tb5.setOnClickListener(clickListener);
        tb6.setOnClickListener(clickListener);
        tb7.setOnClickListener(clickListener);
        tb8.setOnClickListener(clickListener);
        tb9.setOnClickListener(clickListener);

        tb = (TextView)findViewById(R.id.tbIPAddr);

        Log.d("LED",pattern.toString());

        myself = this;


        SetButtons();
    }

    public void SetButtons(){
        Log.d("LEDVAL","SETTING BUTTONS");
        SetState(tb1,pattern.GetLed((Integer)tb1.getTag()));
        SetState(tb2,pattern.GetLed((Integer)tb2.getTag()));
        SetState(tb3,pattern.GetLed((Integer)tb3.getTag()));
        SetState(tb4,pattern.GetLed((Integer)tb4.getTag()));
        SetState(tb5,pattern.GetLed((Integer)tb5.getTag()));
        SetState(tb6,pattern.GetLed((Integer)tb6.getTag()));
        SetState(tb7,pattern.GetLed((Integer)tb7.getTag()));
        SetState(tb8,pattern.GetLed((Integer)tb8.getTag()));
        SetState(tb9,pattern.GetLed((Integer)tb9.getTag()));
    }

    public void SetState(ToggleButton btn,boolean state){
        btn.setChecked(state);
    }

    public LedPattern LoadPattern(){
        LedPattern pat = new LedPattern();
        Map<String,?> map = sharedPreferences.getAll();
        for (Map.Entry<String,?> x: map.entrySet()) {
            Log.d("LEDVAL","LoadValue "+x.toString());
            String led = x.getKey();
            Log.d("LEDVAL",led);
            int ledint = Integer.parseInt(led);
            boolean value = Boolean.parseBoolean(x.getValue().toString());
            pat.SetLed(ledint,value);
        }
        return pat;
    }

    public void SavePattern(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        for (Map.Entry<Integer,Boolean> x:pattern.Leds.entrySet()) {
            editor.putBoolean(x.getKey().toString(),x.getValue());
            Log.d("LEDVAL","PUT:" + x.getKey() + x.getValue() );
        }

        editor.commit();
        finish();
    }

    class ClickListener implements View.OnClickListener{

        //on click elemtne sind immer view

        @Override
        public void onClick(View v) {
            ToggleButton btn = (ToggleButton)v;
            //anhang Pbjekt abrufen und zurück zu int konvertiren weil set Tag nimmt Objekt als Parameter mit
            int led = (int)btn.getTag();
            Log.d("LEDVAL",led+" Value:"+btn.isChecked());
            pattern.SetLed(led,btn.isChecked());
            Log.d("LEDVAL",pattern.toString());
        }

    }


}
