package com.arbud.backendapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.arbud.backendapi.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private ListView listView;
    private String JSON_STRING = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.listview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GETJSON getjson = new GETJSON();
        getjson.execute();
    }

    public class GETJSON extends AsyncTask<Void,Void,String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(HomeActivity.this,"Mengambil Data","Mohon Tunngu",false,false);

        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler rh = new RequestHandler();
            String s = rh.sendGetRequest(Konfigurasi.URL_GET_ALL);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            JSON_STRING = s;
            showEmployeee();
        }
    }
    private void showEmployeee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            for (int i = 0; i<result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Konfigurasi.TAG_ID);
                String name = jo.getString(Konfigurasi.TAG_NAME);
                HashMap<String,String > employes = new HashMap<>();
                employes.put(Konfigurasi.TAG_ID,id);
                employes.put(Konfigurasi.TAG_NAME,name);
                list.add(employes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.list_item_activity, new String[] {Konfigurasi.TAG_ID,Konfigurasi.TAG_NAME},new int[]{R.id.id,R.id.name});
        listView.setAdapter(adapter);
    }
}