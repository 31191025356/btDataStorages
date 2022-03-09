package com.example.btDataStorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String Count_number = "Count_number";
    private static final String BLACK = "BLACK";
    private static final String RED = "RED";
    private static final String BLUE = "BLUE";
    private static final String GREEN = "GREEN";

    private SharedPreferences sharedPref;
    private TextView textCount;
    private String colorCheck = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences(Count_number, MODE_PRIVATE);

        textCount = findViewById(R.id.textCount);

        TextView btnBlack = findViewById(R.id.btnBlack);
        TextView btnRed = findViewById(R.id.btnRed);
        TextView btnBlue = findViewById(R.id.btnBlue);
        TextView btnGreen = findViewById(R.id.btnGreen);
        TextView btnCount = findViewById(R.id.btnCount);
        TextView btnClear = findViewById(R.id.btnClear);


        btnBlack.setOnClickListener(view -> pickColor(BLACK));
        btnRed.setOnClickListener(view -> pickColor(RED));
        btnBlue.setOnClickListener(view -> pickColor(BLUE));
        btnGreen.setOnClickListener(view -> pickColor(GREEN));
        btnCount.setOnClickListener(view -> countNumber());
        btnClear.setOnClickListener(view -> clearNumber());
    }


    private void pickColor(String COLOR_KEY) {
        colorCheck = COLOR_KEY;
        int numberInSharedPref = sharedPref.getInt(COLOR_KEY, 0);
        textCount.setTextColor(Color.parseColor(colorCheck));
        textCount.setText(String.valueOf(numberInSharedPref));
    }
    private void countNumber() {
        if (colorCheck.isEmpty()) {
            return;
        }
        int increase = Integer.parseInt(textCount.getText().toString()) + 1;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(colorCheck, increase);
        editor.apply();
        textCount.setText(String.valueOf(increase));
    }

    private void clearNumber() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        colorCheck = "";
        textCount.setText("Clear Success");
    }
}