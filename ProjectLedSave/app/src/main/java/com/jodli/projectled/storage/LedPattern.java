package com.jodli.projectled.storage;

import android.content.Intent;
import android.util.SparseBooleanArray;

import java.util.HashMap;

public class LedPattern {

    public HashMap<Integer,Boolean> Leds;

    public LedPattern(){
        Leds = new HashMap<>();
    }


    public void SetLed(int pin,boolean value){
        Leds.put(pin,value);
    }

    public boolean GetLed(int pin){
        if(Leds.containsKey(pin)){
            return Leds.get(pin);
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "LedPattern{" +
                "Leds=" + Leds +
                '}';
    }
}
