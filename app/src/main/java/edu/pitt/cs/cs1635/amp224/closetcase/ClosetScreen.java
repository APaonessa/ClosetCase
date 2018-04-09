package edu.pitt.cs.cs1635.amp224.closetcase;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ClosetScreen extends AppCompatActivity {

    Button filter;
    Button addNew;
    SearchView search;
    //ImageButton back;
    GridView gridView;
    ArrayList<Clothes> clothes;
    DBHelper dbHelper;
    String allClothes;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet_screen);

        filter = findViewById(R.id.Filter);
        addNew = findViewById(R.id.AddNew);
        search = findViewById(R.id.searchView);


        dbHelper = new DBHelper(this);
        dbHelper.getReadableDatabase();
        //clothes = new ArrayList<Clothes>();


        gridView = (GridView) findViewById(R.id.gridView);
        clothes = dbHelper.getAllClothes();

        adapter = new MyAdapter(ClosetScreen.this, clothes);
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

    @Override
    protected void onResume()
    {
        super.onResume();
        setPictures();
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

    private void setPictures()
    {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);
        String path;
        Drawable pic;
        ImageView image = null;

        for(int i = 0; i < clothes.size(); i++)
        {
            path = pref.getString(getString(R.string.picture_path_key) + clothes.get(i).getId(), "");
            pic = Drawable.createFromPath(path);
            image = (ImageView)adapter.getView(i, image, gridView);
            if(pic != null)
                image.setImageDrawable(pic);
            else
                image.setImageDrawable(getResources().getDrawable(R.drawable.camera_stock));
            image = null;
        }
    }

}
