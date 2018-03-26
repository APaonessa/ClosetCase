package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

public class ClosetScreen extends AppCompatActivity {

    Button filter;
    Button addNew;
    SearchView search;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet_screen);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        filter = findViewById(R.id.Filter);
        addNew = findViewById(R.id.AddNew);
        search = findViewById(R.id.searchView);
        //back = findViewById(R.id.imageButton);

    }

    public void goToAddNew(View view){
        Intent intent = new Intent(this, ClothingScreen.class);
        startActivity(intent);
    }
}