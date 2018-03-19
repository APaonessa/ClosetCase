package edu.pitt.cs.cs1635.amp224.closetcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class OutfitScreen extends AppCompatActivity {

    Button topLeft;
    Button topRight;
    Button bottomLeft;
    Button bottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_screen);

        topLeft = findViewById(R.id.topLeftArrow);
        topRight = findViewById(R.id.topRightArrow);
        bottomLeft = findViewById(R.id.bottomLeftArrow);
        bottomRight = findViewById(R.id.bottomRightArrow);

    }
}
