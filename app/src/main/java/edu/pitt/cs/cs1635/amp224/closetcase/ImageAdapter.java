package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by abipa on 3/26/2018.
 */

public class ImageAdapter extends BaseAdapter {
        private DBHelper dbHelper;
        private Context mContext;
        private SharedPreferences sharedPreferences;
        private String picturePathKey;
        private ArrayList<Clothes> clothesList;// = new ArrayList<Clothes>();
        private int clothesID;

        public ImageAdapter(Context c, ArrayList<Clothes> clothesList) {
            mContext = c;
            this.clothesList = clothesList;
        }

        public void setSharedPreferences(SharedPreferences sharedPreferences) {
            this.sharedPreferences = sharedPreferences;
        }

        public void setPicturePathKey(String picturePathKey) {
            this.picturePathKey = picturePathKey;
        }

        public int getCount() {
            return clothesList.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            String path = "";
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(300,300));
                //imageView.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            Log.v("tester", "position: " + position);
            /*if(!clothesList.isEmpty())
               //path = sharedPreferences.getString(picturePathKey + clothesList.get(position).getId(), "");
                imageView.setImageResource(R.drawable.camera_stock);

            Log.v("tester", "getView path: " + path);
            Drawable pic = Drawable.createFromPath(path);

            if (pic != null) {
                imageView.setImageDrawable(pic);
            } else {
                imageView.setImageResource(R.drawable.camera_stock);
            }*/
            imageView.setImageResource(R.drawable.camera_stock);
            return imageView;
        }

/*
    private void setPicture(int id, ImageView v)
    {
        SharedPreferences pref = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE);
        String path = pref.getString(getString(R.string.picture_path_key) + id, "");
        //Log.d("OutfitScreen", path);
        Drawable image = Drawable.createFromPath(path);
        if(image != null)
            v.setImageDrawable(image);
        else
            v.setImageDrawable(getResources().getDrawable(R.drawable.camera_stock));
    }*/

        // references to our images
        private Integer[] mThumbIds = {
               R.drawable.bluepants, R.drawable.blueshirt, R.drawable.blackpants, R.drawable.blackshirt,
                R.drawable.brownshirt, R.drawable.brownpants, R.drawable.redshirt};

}
