package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import me.toptas.fancyshowcase.FancyShowCaseView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button findOutfit;
    Button buildOutfit;
    Button manageCloset;
    
    Button foHelpButton;
    Button boHelpButton;
    Button mcHelpButton;
    
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONATACT_ID";
    private ListView listView;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findOutfit = findViewById(R.id.button);
        buildOutfit = findViewById(R.id.button2);
        manageCloset = findViewById(R.id.button3);
        
        foHelpButton = findViewById(R.id.button4);
        boHelpButton = findViewById(R.id.button5);
        mcHelpButton = findViewById(R.id.button6);

        dbHelper = new DBHelper(this);

       /* final Cursor cursor = dbHelper.getAllClothes();
        String[] columns = new String[]{
                DBHelper.CLOTHES_COLUMN_ID,
                DBHelper.CLOTHES_COLUMN_NAME
        };*/


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button4:
                new FancyShowCaseView.Builder(this)
                        .title("Find Outfit Button generates a custom outfit right away for you! For a quick option, click 'Find Outfit' button!")
                        .focusOn(v)
                        .build()
                        .show();
                break;
            case R.id.button5:
                new FancyShowCaseView.Builder(this)
                        .title("Build Outfit Button lets you take control and create your own outfit and look through options of possible outfits! Want to decide on your own? Click 'Build Outfit' button!")
                        .focusOn(v)
                        .build()
                        .show();
                break;
            case R.id.button6:
                new FancyShowCaseView.Builder(this)
                        .title("Manage Closet Button takes you straight to your closet where all your pieces you stored into the app are located!")
                        .focusOn(v)
                        .build()
                        .show();
                break;

        }

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
