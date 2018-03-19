package edu.pitt.cs.cs1635.amp224.closetcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class OutfitScreen extends AppCompatActivity {

    ImageButton topLeft;
    ImageButton topRight;
    ImageButton bottomLeft;
    ImageButton bottomRight;
    ImageButton up;
    ImageButton complete;
    ImageView topImage;
    ImageView bottomImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_screen);

        topLeft = findViewById(R.id.topLeftArrow);
        topRight = findViewById(R.id.topRightArrow);
        bottomLeft = findViewById(R.id.bottomLeftArrow);
        bottomRight = findViewById(R.id.bottomRightArrow);
        up = findViewById(R.id.UpButton);
        complete = findViewById(R.id.CompleteButton);
        topImage = findViewById(R.id.imageViewTop);
        bottomImage = findViewById(R.id.imageViewBottom);

    }
}
