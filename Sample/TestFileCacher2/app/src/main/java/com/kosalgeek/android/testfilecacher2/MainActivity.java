package com.kosalgeek.android.testfilecacher2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kosalgeek.android.caching.FileCacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, SubActivity.class);
                startActivity(in);
            }
        });

        FileCacher<ArrayList<Product>> cacher = new FileCacher<>(MainActivity.this, "somearray.txt");

//        ArrayList<Product> products = new ArrayList<>();
//        products.add(new Product(1, "Milk"));
//        products.add(new Product(2, "Coffee"));
//
//        try {
//            cacher.writeCache(products);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

////        ArrayList<Product> products = new ArrayList<>();
////        products.add(new Product(1, "Cappuccino"));
////        products.add(new Product(2, "Americano"));
//
//        try {
//            cacher.appendOrWriteCache(products);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if(cacher.hasCache()){
            try {
//                List<ArrayList<Product>> list = cacher.getAllCaches();
//
//                for(ArrayList<Product> productList: list){
//                    for(Product p: productList){
//                        Log.d(TAG, p.name);
//                    }
//                }

                ArrayList<Product> productList = getMergedList(cacher.getAllCaches());
                for(Product p: productList){
                    Log.d(TAG, p.name);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private ArrayList<Product> getMergedList(List<ArrayList<Product>> outerList){
        ArrayList<Product> mergedList = new ArrayList<>();
        for(ArrayList<Product> productList: outerList){
            for(Product p: productList){
                mergedList.add(p);
            }
        }
        return mergedList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
