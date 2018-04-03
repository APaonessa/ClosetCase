package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import edu.pitt.cs.cs1635.amp224.closetcase.R;

/**
 * Created by apanz on 20-Mar-18.
 */

public class ClothingScreen extends AppCompatActivity {

    private DBHelper dbHelper;
    ImageButton up;
    ImageView photo;
    EditText descriptorId;
    /*
    TextView    colorId     patternId
                typeId      materialId
      ^^ these textViews should never change
     */
    Spinner color;
    Spinner article;//-type
    Spinner material;
    Spinner pattern;
    Button save;
    Button delete;
    AlertDialog deleteClothing;

    int clothesID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_screen);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //
        //up = findViewById(R.id.upButton);

        clothesID = getIntent().getIntExtra(MainActivity.KEY_EXTRA_CONTACT_ID, 0);

        photo = findViewById(R.id.photoId);
        descriptorId = findViewById(R.id.articleId);
        // Drop Down Lists
        color = findViewById(R.id.colorDropDownId);

        dbHelper = new DBHelper(this);

        //Colors - Black Red Blue Brown
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.colorArray, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color.setAdapter(colorAdapter);
        // -----
        article = findViewById(R.id.articleDropDownId);
        //Spinner - Shirt Pants
        ArrayAdapter<CharSequence> articleAdapter = ArrayAdapter.createFromResource(this,
                R.array.articleArray, android.R.layout.simple_spinner_item);
        articleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        article.setAdapter(articleAdapter);
        // -----
        material = findViewById(R.id.materialDropDownId);
        //Spinner - Cotton Denim
        ArrayAdapter<CharSequence> materialAdapter = ArrayAdapter.createFromResource(this,
                R.array.articleArray, android.R.layout.simple_spinner_item);
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        material.setAdapter(materialAdapter);
        // -----
        pattern = findViewById(R.id.patternDropDownId);
        //Spinner - Solid Plaid
        ArrayAdapter<CharSequence> patternAdapter = ArrayAdapter.createFromResource(this,
                R.array.patternArray, android.R.layout.simple_spinner_item);
        patternAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pattern.setAdapter(patternAdapter);
        //
        save = findViewById(R.id.saveButton);
        delete = findViewById(R.id.deleteButton);

        if (clothesID > 0) {
            save.setVisibility(View.GONE);

            Cursor rs = dbHelper.getClothes(clothesID);
            rs.moveToFirst();
            String clothesName = rs.getString(rs.getColumnIndex(DBHelper.CLOTHES_COLUMN_NAME));
            String clothesType = rs.getString(rs.getColumnIndex(DBHelper.CLOTHES_COLUMN_TYPE));
            String clothesColor = rs.getString(rs.getColumnIndex(DBHelper.CLOTHES_COLUMN_COLOR));
            String clothesMaterial = rs.getString(rs.getColumnIndex(DBHelper.CLOTHES_COLUMN_MATERIAL));
            String clothesPattern = rs.getString(rs.getColumnIndex(DBHelper.CLOTHES_COLUMN_PATTERN));

            if (!rs.isClosed()) {
                rs.close();
            }


        }

    }


    //Save button
    public void goToClosetScreen(View view) {
        Intent intent = new Intent(this, ClosetScreen.class);
        startActivity(intent);
        persistClothes();
        return;
    }

    public void onDelete(View view) {
        AlertDialog deleteClothing = new AlertDialog.Builder(ClothingScreen.this).create();
        deleteClothing.setTitle(R.string.Delete);
        deleteClothing.setMessage("Are you sure you want to delete this item?");

        deleteClothing.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteClothes(clothesID);
                        Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ClosetScreen.class);
                        startActivity(intent);
                    }
                });
        deleteClothing.setButton(AlertDialog.BUTTON_POSITIVE, "Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        deleteClothing.show();
        return;


    }





    public void persistClothes() {
        if(clothesID > 0) {
            if(dbHelper.updateClothes(clothesID, descriptorId.getText().toString(), article.getSelectedItem().toString(), color.getSelectedItem().toString(),
                    material.getSelectedItem().toString(), pattern.getSelectedItem().toString())) {
                Toast.makeText(getApplicationContext(), "Closet Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ClosetScreen.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Closet Update Failed", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if(dbHelper.insertClothes(descriptorId.getText().toString(), article.getSelectedItem().toString(), color.getSelectedItem().toString(),
                    material.getSelectedItem().toString(), pattern.getSelectedItem().toString())) {
                Toast.makeText(getApplicationContext(), "Clothes Inserted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Could not Insert clothes", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), ClosetScreen.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}

