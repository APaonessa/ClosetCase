package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    public int _id = 0;

    //int pic_res_id;
   // int num_pictures;
    SharedPreferences pref;
    String picture_path;
    //boolean pic_success;
    static final int TAKE_PHOTO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clothesID = getIntent().getIntExtra(MainActivity.KEY_EXTRA_CONTACT_ID, 0);

        setContentView(R.layout.activity_clothing_screen);


        photo = findViewById(R.id.photoId);
        descriptorId = findViewById(R.id.articleId);


        // Drop Down Lists
        color = findViewById(R.id.colorDropDownId);

        //dbHelper = new DBHelper(this);

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
                R.array.materialArray, android.R.layout.simple_spinner_item);
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

        dbHelper = new DBHelper(this);




    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Resources res = getResources();
        pref = this.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);
        if(clothesID > 0)
            picture_path = pref.getString(getString(R.string.picture_path_key) + clothesID, "");
        else
            picture_path = pref.getString(getString(R.string.picture_path_key) + (_id + 1), "");
        //num_pictures = pref.getInt(getString(R.string.picture_total_key), -1);
        Drawable pic = Drawable.createFromPath(picture_path);
        //Log.d("ContentValues", picture_path);
        if(pic != null)
            photo.setImageDrawable(pic);
        else
            photo.setImageDrawable(res.getDrawable(R.drawable.camera_stock));
    }

    //Save button
    public void goToClosetScreen(View view) {
        //Intent intent = new Intent(this, ClosetScreen.class);
       //startActivity(intent);
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
    }


    public void persistClothes() {
        if(clothesID > 0) {
            if(dbHelper.updateClothes(_id, descriptorId.getText().toString(), article.getSelectedItem().toString(), color.getSelectedItem().toString(),
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
        _id++;
    }

    public void launchCamera(View view)
    {
        dispatchTakePictureIntent();
    }
/*
    @Override
    protected void onPause()
    {
        super.onPause();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(getString(R.string.picture_total_key), num_pictures);
        edit.commit();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(getString(R.string.picture_total_key), num_pictures);
        edit.commit();
    }
*/
    //Modified from online tutorial
    private File createImageFile() throws IOException
    {
        String fileName = "";
        if(clothesID > 0)
            fileName = "picture" + (clothesID);
        else
            fileName = "picture" + (_id + 1);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //Log.d("ContentValues", Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(fileName, ".jpg", storageDir);
        picture_path = image.getAbsolutePath();

        return image;
    }

    //Modified from online tutorial
    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            File imageFile = null;
            try
            {
                imageFile = createImageFile();
            }
            catch(Exception e)
            {
                Log.d("ContentValues", "Error creating file: " + e.getMessage());
                return;
            }

            if(imageFile != null)
            {
                Uri picUri = FileProvider.getUriForFile(this, "edu.pitt.cs.cs1635.amp224.closetcase.fileprovider", imageFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
                startActivityForResult(takePictureIntent, TAKE_PHOTO_REQUEST);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("ContentValues", "Result received.");
        if(resultCode == RESULT_OK)
        {
            SharedPreferences.Editor edit = pref.edit();
            if(clothesID > 0)
                edit.putString(getString(R.string.picture_path_key) + clothesID, picture_path);
            else
                edit.putString(getString(R.string.picture_path_key) + (_id + 1), picture_path);
            edit.commit();
        }

        else
            Log.d("ContentValues", "Error on capture.");
    }


    public void editTextDestroy(View v){
        descriptorId.setText("");
    }
}



