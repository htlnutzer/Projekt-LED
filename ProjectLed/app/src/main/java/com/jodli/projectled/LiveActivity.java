package com.jodli.projectled;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jodli.projectled.connectivity.RpcHandler;

public class LiveActivity extends Activity {

    private TextView tb = null;
    public LiveActivity myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        ToggleButton tb1 = (ToggleButton)findViewById(R.id.tb1);
        ToggleButton tb2 = (ToggleButton)findViewById(R.id.tb2);
        ToggleButton tb3 = (ToggleButton)findViewById(R.id.tb3);
        ToggleButton tb4 = (ToggleButton)findViewById(R.id.tb4);
        ToggleButton tb5 = (ToggleButton)findViewById(R.id.tb5);
        ToggleButton tb6 = (ToggleButton)findViewById(R.id.tb6);
        ToggleButton tb7 = (ToggleButton)findViewById(R.id.tb7);
        ToggleButton tb8 = (ToggleButton)findViewById(R.id.tb8);
        ToggleButton tb9 = (ToggleButton)findViewById(R.id.tb9);

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

        ClickListener clickListener = new ClickListener();

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

        myself = this;

    }

    class ClickListener implements View.OnClickListener{

    //on click elemtne sind immer view

        @Override
        public void onClick(View v) {
            ToggleButton btn = (ToggleButton)v;
            //anhang Pbjekt abrufen und zurück zu int konvertiren weil set Tag nimmt Objekt als Parameter mit
            int led = (int)btn.getTag();
            String ip = tb.getText().toString();

            if(btn.isChecked()){
                //Active LED
                Log.d("RPC","Calling LEDON - "+led+" over at "+ip);
                //Rpc handler bedeutet dass ich Methode an einem Remote system ausführe
                RpcHandler.CallRPCLedOn(ip,led,myself);
            }else{
                //Deactivate LED
                Log.d("RPC","Calling LEDOFF - "+led+" over at "+ip);
                RpcHandler.CallRPCLedOff(ip,led,myself);
            }
        }

    }

    public void HandleRPCResult(String message){
        // Handle the result here
        Log.d("RPC",message);
    }


}
