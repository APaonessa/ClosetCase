package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class filter extends AppCompatActivity {

    CheckBox color1;
    CheckBox color2;
    CheckBox color3;
    CheckBox color4;
    CheckBox type1;
    CheckBox type2;
    Button complete;


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
}
