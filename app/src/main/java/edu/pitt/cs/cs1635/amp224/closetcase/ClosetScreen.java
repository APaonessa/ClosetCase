package edu.pitt.cs.cs1635.amp224.closetcase;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClosetScreen extends AppCompatActivity {

    Button filter;
    Button addNew;
    SearchView search;
    //ImageButton back;
    //GridView gridView;
    ArrayList clothes = new ArrayList<>();
    DBHelper dbHelper;
    String allClothes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet_screen);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        filter = findViewById(R.id.Filter);
        addNew = findViewById(R.id.AddNew);
        search = findViewById(R.id.searchView);
        //back = findViewById(R.id.imageButton);
        //gv = (GridView) findViewById(R.id.gridView);

        dbHelper = new DBHelper(this);
        ArrayList<Clothes> clothesList;
        clothesList = new ArrayList<Clothes>();
        MyAdapter adapter;



        GridView gridView = (GridView) findViewById(R.id.gridView);
        clothesList = dbHelper.getAllClothes();

        adapter = new MyAdapter(ClosetScreen.this, clothesList);
        gridView.setAdapter(adapter);


        /*
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ClosetScreen.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });*/


    }




    public void goToAddNew(View view){
        Intent intent = new Intent(this, ClothingScreen.class);
        startActivity(intent);
    }

    public void goToFilter(View view){
        Intent intent = new Intent(this, filter.class);
        startActivity(intent);
    }

    public void goToOutfitScreen(View view){
        Intent intent = new Intent(this, OutfitScreen.class);
        startActivity(intent);
    }

}
