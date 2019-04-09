package com.example.arvth.letsgetfed;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class ShelfManager extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        int ID = savedInstanceState.getInt("id");
        load(ID);
    }

    public void pantry(View view) {
        startActivity(new Intent(ShelfManager.this, Pantry.class));
    }

    public void load(int ID) {
        TableLayout layout = findViewById(R.id.food_list);
        int count = layout.getChildCount();
        for(int i = 0; i < count; i++) {
            if(layout.getChildAt(i) instanceof TableRow) {
                ((ViewGroup) layout.getChildAt(i)).removeAllViews();
            }
        }
        Shelf shelf = Pantry.shelves.get(ID);
        for(int i = 0; i < shelf.getPopulation(); i++) {
            TableRow row = new TableRow(this);
            row.addView(getView(shelf, i));
            layout.addView(row);
        }
    }
    public TextView getView(Shelf shelf, int ID) {
        TextView view = new TextView(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        view.setText(shelf.getLabel());
        view.setId(ID);
        return view;
    }
    //store methods below
    public static void storeValues(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putInt("shelf_population", Pantry.shelves.size());
        for(int i = 0; i < Pantry.shelves.size(); i++) {
            storeShelf(editor, i);
        }
    }
    public static void storeShelf(SharedPreferences.Editor editor, int ID) {
        editor.putInt("shelf_" + ID + "_type", Pantry.shelves.get(ID).getType());
        editor.putInt("shelf_" + ID + "_size", Pantry.shelves.get(ID).getPopulation());
        editor.putString("shelf_" + ID + "_label", Pantry.shelves.get(ID).getLabel());
        for(int i = 0; i < Pantry.shelves.get(ID).getPopulation(); i++) {
            storeFood(editor, ID, i);
        }
    }
    public static void storeFood(SharedPreferences.Editor editor, int shelfID, int foodID) {
        editor.putString("shelf_" + shelfID + "_" + foodID + "_label",
                Pantry.shelves.get(shelfID).getFood(foodID).getName());
        editor.putString("shelf_" + shelfID + "_" + foodID + "_date",
                Pantry.shelves.get(shelfID).getFood(foodID).getPurchaseDate().toString());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_minExp", 0);
                //Pantry.shelves[shelfID].getFood(foodID).getMinExpirationTime());
        editor.putInt("shelf_" + shelfID + "_" + foodID + "_maxExp", 0);
                //Pantry.shelves[shelfID].getFood(foodID).getMaxExpirationTime());
    }
    //get methods below
    public static void pullDirectory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("shelves", Context.MODE_PRIVATE);
        int size = preferences.getInt("shelf_population", 0);
        Pantry.shelves = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Pantry.shelves.add(pullShelf(preferences, i));
        }
    }
    public static Shelf pullShelf(SharedPreferences preferences, int ID) {
        Shelf shelf = new Shelf(preferences.getString("shelf_" + ID + "_label", ""),
                preferences.getInt("shelf_" + ID + "_type", 0));
        int size = preferences.getInt("shelf_" + ID + "_size", 0);

        for(int i = 0; i < size; i++) {
            shelf.addFood(pullFood(preferences, ID, i));
        }
        return shelf;
    }
    public static Food pullFood(SharedPreferences preferences, int shelfID, int foodID) {
//        return new Food(
//                preferences.getString("shelf_" + shelfID + "_" + foodID + "_label", ""),
//                Date.valueOf(preferences.getString("shelf_" + shelfID + "_" + foodID + "_date", "")),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExp", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExp", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExpFridge", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExpFridge", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_minExpFreezer", 0),
//                preferences.getInt("shelf_" + shelfID + "_" + foodID + "_maxExpFreezer", 0)
//        );
        return new Food("", "", 0,0,0,0,0,0);
    } //this method was return errors - will consult with team for Food() constructor
}