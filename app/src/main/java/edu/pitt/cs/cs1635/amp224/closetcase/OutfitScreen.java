package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    int topPosition;
    int bottomPosition;


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

        topPosition = 0;
        bottomPosition = 0;

        topImage.setImageResource(shirts[topPosition]);
        bottomImage.setImageResource(pants[bottomPosition]);

        topLeft.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if(topPosition == 0) {
                    topPosition = 3;
                    topImage.setImageResource(shirts[topPosition]);
                }
                else{
                    topPosition = topPosition -1;
                    topImage.setImageResource(shirts[topPosition]);
                }
            }
        });

        topRight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(topPosition==3){
                    topPosition = 0;
                    topImage.setImageResource(shirts[topPosition]);
                }
                else{
                    topPosition = topPosition + 1;
                    topImage.setImageResource(shirts[topPosition]);
                }
            }
        });

        bottomLeft.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(bottomPosition == 0){
                    bottomPosition = 2;
                    bottomImage.setImageResource(pants[bottomPosition]);
                }
                else{
                    bottomPosition = bottomPosition - 1;
                    bottomImage.setImageResource(pants[bottomPosition]);
                }
            }
        });


        bottomRight.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(bottomPosition == 2){
                    bottomPosition = 0;
                    bottomImage.setImageResource(pants[bottomPosition]);
                }
                else{
                    bottomPosition = bottomPosition + 1;
                    bottomImage.setImageResource(pants[bottomPosition]);
                }
            }
        });



    }//Close onCreate


    public void goToHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

} ///Close class

/* Flipping through pictures means they need to be in the drawable folder.
So to view pictures in this way select images from database (they are already in drawable folder?
OPTION1:Put pictures in drawable folder when they are taken from camera in ADD NEW)
 */
