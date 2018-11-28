package com.example.jodli.projektlederklrt;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SendRequest {

    private static String url = "/api/jsonrpc.php";

    public static void LEDOn(int nummer, String ip, final MainActivity mainactivity) {
        MediaType mediaType = MediaType.parse("application//x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, "secret=geheim&led=" + nummer + "&status=on");

        Request request = new Request.Builder()
                .url("http://" + ip + "" + url)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();


        client.newCall(request).enqueue(new Callback() {
        @Override public void onFailure(Call call, IOException e) {
        e.printStackTrace();

        }

        @Override public void onResponse(Call call, Response response) throws IOException {
        System.out.print("Funz");

        }


        });
    }

        public static void LEDOff(int nummer, String ip, final MainActivity mainactivity) {
            MediaType mediaType = MediaType.parse("application//x-www-form-urlencoded");
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, "secret=geheim&led="+nummer+"&status=off");

            Request request = new Request.Builder()
                    .url("http://"+ip+""+url)
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();

                client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(Call call, IOException e) {
                    e.printStackTrace();

                }

                @Override public void onResponse(Call call, Response response) throws IOException {


                }


            });
    }
}
