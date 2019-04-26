package com.example.arvth.letsgetfed;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class ShelfManager extends AppCompatActivity {
    int shelfID;
    public static ArrayList<Food> listOfFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        RecyclerViewShelf();
        shelfID = Integer.valueOf(getIntent().getStringExtra("id"));
        //load(shelfID);
        load(shelfID);
        Preferences.storeValues(this);
        Preferences.pullDirectory(this);
    }

    public void RecyclerViewShelf() {
        RecyclerView recyclerView2 = findViewById(R.id.shelf_recyclerView);
        RecyclerShelfAdapter adapter2 = new RecyclerShelfAdapter(listOfFoods, this);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
    }

    public void pantry(View view) {
        startActivity(new Intent(ShelfManager.this, Pantry.class));
    }

    public void load(int ID) {
//        TableLayout layout = findViewById(R.id.food_list);
//        int count = layout.getChildCount();
//        for(int i = 0; i < count; i++) {
//            if(layout.getChildAt(i) instanceof TableRow) {
//                ((ViewGroup) layout.getChildAt(i)).removeAllViews();
//            }
//        }
//        Shelf shelf = Pantry.shelves.get(ID);
//        for(int i = 0; i < shelf.getPopulation(); i++) {
//            TableRow row = new TableRow(this);
//            row.addView(getView(shelf, i));
//            layout.addView(row);
//        }
    }
//    public TextView getView(Shelf shelf, int ID) {
//        TextView view = new TextView(this);
//        TableRow.LayoutParams params = new TableRow.LayoutParams(
//                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
//        view.setLayoutParams(params);
//        view.setText(shelf.getFood(ID).getName());
//        view.setId(ID);
//        return view;
//    }
    public void addfood(View view) {
        Intent intent = new Intent(ShelfManager.this, AddFood.class);
        intent.putExtra("id", shelfID + "");
        startActivity(intent);
    }
    //store methods below

}