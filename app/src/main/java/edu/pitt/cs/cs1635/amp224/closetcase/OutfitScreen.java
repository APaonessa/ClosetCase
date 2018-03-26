package edu.pitt.cs.cs1635.amp224.closetcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class OutfitScreen extends AppCompatActivity  { //Open class

    ImageButton topLeft;
    ImageButton topRight;
    ImageButton bottomLeft;
    ImageButton bottomRight;
    ImageButton up;
    Button complete;
    ImageView topImage;
    ImageView bottomImage;
    int[] shirts = {R.drawable.redshirt, R.drawable.brownshirt, R.drawable.blueshirt, R.drawable.blackshirt};
    int[] pants = {R.drawable.blackpants, R.drawable.brownpants, R.drawable.bluepants};
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //Open onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfit_screen);
        //getActionBar().setDisplayHomeAsUpEnabled(true);


        topLeft = findViewById(R.id.topLeftArrow);
        topRight = findViewById(R.id.topRightArrow);
        bottomLeft = findViewById(R.id.bottomLeftArrow);
        bottomRight = findViewById(R.id.bottomRightArrow);
        //up = findViewById(R.id.UpButton)
        topImage = findViewById(R.id.imageViewTop);
        bottomImage = findViewById(R.id.imageViewBottom);
        complete = findViewById(R.id.Complete);

        position = 0;
        topImage.setImageResource(shirts[position]);
        bottomImage.setImageResource(pants[position]);


    }//Close onCreate
} ///Close class

/* Flipping through pictures means they need to be in the drawable folder.
So to view pictures in this way select images from database (they are already in drawable folder?
OPTION1:Put pictures in drawable folder when they are taken from camera in ADD NEW)
 */
