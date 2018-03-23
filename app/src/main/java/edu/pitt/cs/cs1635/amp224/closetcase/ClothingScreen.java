package edu.pitt.cs.cs1635.amp224.closetcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import edu.pitt.cs.cs1635.amp224.closetcase.R;

/**
 * Created by apanz on 20-Mar-18.
 */

public class ClothingScreen extends AppCompatActivity {

    ImageButton up;
    ImageView photo;
    EditText descriptorId;
    /*
    TextView    colorId     patternId
                typeId      materialId
      ^^ these textViews should never change
     */
    ImageButton color;
    Spinner material;
    Spinner pattern;
    Spinner article;
    Button save;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_screen);
        //
        up = findViewById(R.id.upButton);
        photo = findViewById(R.id.photoId);
        descriptorId = findViewById(R.id.articleId);
        color = findViewById(R.id.colorPaletteId);
        material = findViewById(R.id.materialDropDownId);
        pattern = findViewById(R.id.patternDropDownId);
        article = findViewById(R.id.articleDropDownId);
        save = findViewById(R.id.saveButton);
        delete = findViewById(R.id.deleteButton);

    }
}
