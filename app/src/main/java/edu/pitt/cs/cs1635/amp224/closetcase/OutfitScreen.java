package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

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

    DBHelper dbHelper;
    ArrayList<Clothes> clothes;

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

  /*
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
*/


    }//Close onCreate

    @Override
    protected void onResume()
    {
        super.onResume();

        dbHelper = new DBHelper(this);
        clothes = dbHelper.getAllClothes();

        topPosition = -1;
        bottomPosition = -1;

        for(int i = 0; i < clothes.size(); i++)
            if(clothes.get(i).getType().equals("Shirt"))
            {
                topPosition = i;
                break;
            }

        for(int i = 0; i < clothes.size(); i++)
            if(clothes.get(i).getType().equals("Pants"))
            {
                bottomPosition = i;
                break;
            }

        setPicture(topPosition, topImage);
        setPicture(bottomPosition, bottomImage);
    }

    public void goToHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setPicture(int id, ImageView v)
    {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);
        String path = pref.getString(getString(R.string.picture_path_key) + id, "");
        Drawable image = Drawable.createFromPath(path);
        if(image != null)
            v.setImageDrawable(image);
        else
            v.setImageDrawable(getResources().getDrawable(R.drawable.camera_stock));
    }

    public void onTopRight(View view)
    {
        if(topPosition == -1)
            return;

        do {
            topPosition++;
            if (topPosition >= clothes.size())
                topPosition = 0;
        }
        while(!clothes.get(topPosition).getType().equals("Shirt"));

        setPicture(topPosition, topImage);
    }

    public void onTopLeft(View view)
    {
        if(topPosition == -1)
            return;

        do {
            topPosition--;
            if (topPosition < 0)
                topPosition = clothes.size() - 1;
        }
        while(!clothes.get(topPosition).getType().equals("Shirt"));

        setPicture(topPosition, topImage);
    }

    public void onBottomRight(View view)
    {
        if(bottomPosition == -1)
            return;

        do {
            bottomPosition++;
            if (bottomPosition >= clothes.size())
                bottomPosition = 0;
        }
        while(!clothes.get(bottomPosition).getType().equals("Pants"));

        setPicture(bottomPosition, bottomImage);
    }

    public void onBottomLeft(View view)
    {
        if(bottomPosition == -1)
            return;

        do {
            bottomPosition--;
            if (bottomPosition < 0)
                bottomPosition = clothes.size() - 1;
        }
        while(!clothes.get(bottomPosition).getType().equals("Pants"));

        setPicture(bottomPosition, bottomImage);
    }

} ///Close class

/* Flipping through pictures means they need to be in the drawable folder.
So to view pictures in this way select images from database (they are already in drawable folder?
OPTION1:Put pictures in drawable folder when they are taken from camera in ADD NEW)
 */
