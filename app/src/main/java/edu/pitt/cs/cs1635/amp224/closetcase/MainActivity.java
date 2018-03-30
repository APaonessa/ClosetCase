package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    //when find outfit and build outfit buttons are clicked, this method "activates"
    //and the screen changes from home to outfit screen
    public void goToOutfitScreen(View view){
        Intent intent = new Intent(this, OutfitScreen.class);
        startActivity(intent);
    }

    //when the manage closet button is clicked
    //method activates and screen changes from home to closet screen
    public void goToClosetScreen(View view){
        Intent intent = new Intent(this, ClosetScreen.class);
        startActivity(intent);
    }


}

//The home page