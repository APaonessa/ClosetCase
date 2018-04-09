package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class filter extends AppCompatActivity {

    CheckBox color1; //BLACK
    CheckBox color2;//BLUE
    CheckBox color3;//RED
    CheckBox color4;//BROWN
    CheckBox type1;
    CheckBox type2;
    Button complete;
    DBHelper dbHelper = new DBHelper(this);

    public DBHelper getDbHelper() {
        return dbHelper;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        color1 = findViewById(R.id.color1);
        color2 = findViewById(R.id.color2);
        color3 = findViewById(R.id.color3);
        color4 = findViewById(R.id.color4);
        type1 = findViewById(R.id.type1);
        type2 = findViewById(R.id.type2);
        complete = findViewById(R.id.Accept);



    }

    public void goToClosetScreen(View view){
        Intent intent = new Intent(this, ClosetScreen.class);
        startActivity(intent);
    }

    public void filter(){
        getDbHelper();
        dbHelper.getReadableDatabase();
        if(color1.isChecked() == true){
            dbHelper.getBlackClothes();

        }
        else if(color2.isChecked() == true){
            dbHelper.getBlueClothes();

        }
        else if(color3.isChecked() == true){
            dbHelper.getrRedClothes();
        }
        else if(color4.isChecked() == true){
            dbHelper.getBrownClothes();
        }
        else if((color1.isChecked() ==true)&& (color2.isChecked() == true)){
            dbHelper.getBlackClothes();
            dbHelper.getBlueClothes();
        }
        else if((color1.isChecked() == true) && (color3.isChecked() == true)){
            dbHelper.getBlackClothes();
            dbHelper.getrRedClothes();

        }
        else if((color1.isChecked() == true) && (color4.isChecked() == true)){
            dbHelper.getBlackClothes();
            dbHelper.getBrownClothes();

        }
        else if((color2.isChecked() == true) && (color3.isChecked() == true)){
            dbHelper.getBlueClothes();
            dbHelper.getrRedClothes();

        }
        else if((color2.isChecked() == true) && (color4.isChecked() == true)){
            dbHelper.getBlueClothes();
            dbHelper.getBrownClothes();

        }
        else{
            dbHelper.getrRedClothes();
            dbHelper.getBrownClothes();
        }
    }
}
