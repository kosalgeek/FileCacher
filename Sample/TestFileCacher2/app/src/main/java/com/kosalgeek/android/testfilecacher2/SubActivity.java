package com.kosalgeek.android.testfilecacher2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.kosalgeek.android.caching.FileCacher;

import java.io.IOException;
import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FileCacher<ArrayList<String>> cacher = new FileCacher<>(this, "sometext.txt");

        ArrayList<String> products = new ArrayList<>();
        products.add("Coke");
        products.add("Pepsi");
        products.add("Zenya");

        try {
            cacher.writeCache(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(cacher.hasCache()){
            try {
                ArrayList<String> cacheProduct = cacher.readCache();
                for(String p: cacheProduct){
                    Log.d(TAG, p);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch(ClassCastException e){
                e.printStackTrace();
            }
        }


    }

}
