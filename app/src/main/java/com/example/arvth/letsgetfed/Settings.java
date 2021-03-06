package com.example.arvth.letsgetfed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * This class models the behavior of a setting being changed.
 */
public class Settings extends AppCompatActivity {

    /**
     * This method builds activity_settings with a given Bundle
     * @param savedInstanceState the Bundle of information being taken from the previous activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button apply = findViewById(R.id.set_button);
        apply.setOnClickListener(new View.OnClickListener() {

            /**
             *
             * @param view
             */
            @Override
            public void onClick(View view) {
                setSettings(view);
            }
        });

    }

    /**
     * This method allows a user to set how many days in advance they would like to be notified about food expiring.
     * @param view the button being clicked
     */
    public void setSettings(View view)
    {
        EditText text = findViewById(R.id.days_in_advance_fill);
        String buffer = text.getText().toString();
        Alert.ALERT_TIME_BUFFER = Integer.valueOf(buffer);
        startActivity(new Intent(Settings.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Pantry" screen/class from the "Settings" screen/class.
     * @param view the button being clicked
     */
    public void toPantryClickS(View view){
        startActivity(new Intent(Settings.this, Pantry.class));
    }

    /**
     * This method returns the user to the "Alert" screen/class from the "Settings" screen/class.
     * @param view the button being clicked
     */
    public void toAlertsClickS(View view){
        startActivity(new Intent(Settings.this, Alert.class));
    }

    /**
     * This method returns the user to the "Settings" screen/class from the "Settings" screen/class.
     * @param view the button being clicked
     */
    public void toSettingsClickS(View view){
        startActivity(new Intent(Settings.this, Settings.class));
    }
}