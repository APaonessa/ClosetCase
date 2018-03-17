package edu.pitt.cs.cs1635.amp224.closetcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button findOutfit;
    Button buildOutfit;
    Button manageCloset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findOutfit = findViewById(R.id.button);
        buildOutfit = findViewById(R.id.button2);
        manageCloset = findViewById(R.id.button3);




    }
}

//The home page