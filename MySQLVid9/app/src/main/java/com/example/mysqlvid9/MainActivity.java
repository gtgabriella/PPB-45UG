package com.example.mysqlvid9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        tampilkanBarang("http://192.168.1.12/service_data.php");
    }

    private void tampilkanBarang(String s) {
        class tampilkanBarang extends AsyncTask<Void, Void, String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    bacadatakelist(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(s);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null){
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        }
        tampilkanBarang tampil = new tampilkanBarang(); //class bukan method
        tampil.execute();
    }

    private void bacadatakelist(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String stok[] = new String[jsonArray.length()];
        for (int i = 0; i<jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            stok[i] =
                    obj.getString("kode") + " " +
                    obj.getString("nama_barang") + " " +
                    obj.getString("harga");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, stok);
        listView.setAdapter(arrayAdapter);
    }
}