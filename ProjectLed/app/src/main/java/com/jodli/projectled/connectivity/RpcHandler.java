package com.jodli.projectled.connectivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.jodli.projectled.LiveActivity;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RpcHandler {

    //url für webserver
    private static String url = "/api/jsonrpc.php";


    public static void CallRPCLedOn(String ip, int ledpin, final LiveActivity liveactivity){

        try{
            final OkHttpClient client = new OkHttpClient();

            //parameter werden in form format weitergegeben
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "secret=geheim&led="+ledpin+"&status=on");
            final Request request = new Request.Builder()
                    .url("http://"+ip+""+url)
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();
            //Aufgabe im Programm erstellen die Asyncron(wartet nicht auf erg.) ist
            AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    try {
                        //mehtode wird ausgeführt sobald der Task ausgeführt wird
                        Response response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            Log.d("RPC","ERROR - RPC CALL FAILED! NOT SUCCESSFULL -");
                            return null;
                        }

                        JSONObject json_response = new JSONObject(response.body().string());
                        String status = json_response.getString("status");
                        if(status.equals("success")){
                            liveactivity.HandleRPCResult("Pin switched state ON");
                        }
                        return "";
                    } catch (Exception e) {
                        Log.d("RPC",e.toString()+"\nStacktrace: "+e.getStackTrace());
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    if (s != null) {
                        //TODO continue the appflow
                        liveactivity.HandleRPCResult("Pin switched state to ON! Continue");
                    }
                }
            };

            asyncTask.execute();

        }catch (Exception ex){

            Log.d("RPC",ex.toString()+"\nStacktrace: "+ex.getStackTrace());

        }

    }


    public static void CallRPCLedOff(String ip, int ledpin, final LiveActivity liveactivity){

        try{
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "secret=geheim&led="+ledpin+"&status=off");
            final Request request = new Request.Builder()
                    .url("http://"+ip+""+url)
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();

            AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    try {
                        final OkHttpClient client = new OkHttpClient();

                        Response response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            Log.d("RPC","ERROR - RPC CALL FAILED! NOT SUCCESSFULL -");
                            return null;
                        }

                        JSONObject json_response = new JSONObject(response.body().string());
                        String status = json_response.getString("status");
                        if(status.equals("success")){
                            liveactivity.HandleRPCResult("Pin switched state OFF");
                        }
                        return "";
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    if (s != null) {
                        //TODO continue the appflow
                        liveactivity.HandleRPCResult("Pin switched state to OFF! Continue");
                    }
                }
            };

            asyncTask.execute();

        }catch (Exception ex){

        }

    }



}
