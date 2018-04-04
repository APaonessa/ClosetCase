package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by abipa on 4/3/2018.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Clothes> clothesList;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, ArrayList<Clothes> clothesList){
        this.context = context;
        this.clothesList = clothesList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return clothesList.size();
    }

    @Override
    public Object getItem(int position){
        return position;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView=inflater.inflate(R.layout.activity_closet_screen, null);

        return convertView;

    }

}
