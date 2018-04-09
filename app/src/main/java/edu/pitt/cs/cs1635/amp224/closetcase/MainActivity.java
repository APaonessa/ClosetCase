package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.position.ShowCasePosition;
import ru.dimorinny.showcasecard.position.ViewPosition;
import ru.dimorinny.showcasecard.radius.Radius;


public class MainActivity extends AppCompatActivity{

    Button findOutfit;
    Button buildOutfit;
    Button manageCloset;
    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONATACT_ID";
    private ListView listView;
    DBHelper dbHelper;

    Button foHelpButton;
    Button boHelpButton;
    Button mcHelpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findOutfit = findViewById(R.id.button);
        buildOutfit = findViewById(R.id.button2);
        manageCloset = findViewById(R.id.button3);

        foHelpButton = findViewById(R.id.foHelpButton);
        boHelpButton = findViewById(R.id.boHelpButton);
        mcHelpButton = findViewById(R.id.mcHelpButton);


        dbHelper = new DBHelper(this);
        dbHelper.getReadableDatabase();

       /* final Cursor cursor = dbHelper.getAllClothes();
        String[] columns = new String[]{
                DBHelper.CLOTHES_COLUMN_ID,
                DBHelper.CLOTHES_COLUMN_NAME
        };*/

       foHelpButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               showTooltip1(new ViewPosition(view));
           }
       });

        boHelpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showTooltip2(new ViewPosition(view));
            }
        });

        mcHelpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showTooltip3(new ViewPosition(view));
            }
        });

    }

    private void showTooltip1(ShowCasePosition position){
        new ShowCaseView.Builder(MainActivity.this)
                .withTypedPosition(position)
                .withTypedRadius(new Radius(186F))
                .withContent("Find Outfit Button generates a custom outfit right away for you! For a quick option, click 'Find Outfit' button!")
                .build()
                .show(MainActivity.this);
    }

    private void showTooltip2(ShowCasePosition position){
        new ShowCaseView.Builder(MainActivity.this)
                .withTypedPosition(position)
                .withTypedRadius(new Radius(186F))
                .withContent("Build Outfit Button lets you take control and create your own outfit and look through options of possible outfits! Want to decide on your own? Click 'Build Outfit' button!")
                .build()
                .show(MainActivity.this);
    }

    private void showTooltip3(ShowCasePosition position){
        new ShowCaseView.Builder(MainActivity.this)
                .withTypedPosition(position)
                .withTypedRadius(new Radius(186F))
                .withContent("Manage Closet Button takes you straight to your closet where all your pieces you stored into the app are located!")
                .build()
                .show(MainActivity.this);
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
