package com.cds.recyclerviewdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        new FetchDataAsyncTask().execute(NetworkUtils.buildUrl());
    }

    public class FetchDataAsyncTask extends AsyncTask<URL, Void, List<Data>>{

        List<Data> list_data = new ArrayList<>();

        @Override
        protected List<Data> doInBackground(URL... urls) {
            URL url = urls[0];
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(url);
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("todos");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject_data = jsonArray.getJSONObject(i);
                    URL image_url = new URL(jsonObject_data.getString("imagen"));
                    Bitmap bmp = BitmapFactory.decodeStream(image_url.openConnection().getInputStream());
                    Data data = new Data(jsonObject_data.getString("nombreproducto"),jsonObject_data.getString("descripcion"),
                            bmp);
                    list_data.add(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return list_data;
        }

        @Override
        protected void onPostExecute(List<Data> s) {
            super.onPostExecute(s);
            if(s!=null && s.size()>0){
                ListAdapter adapter = new ListAdapter(s);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
