package edu.pitt.cs.cs1635.amp224.closetcase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.sql.RowId;
import java.util.ArrayList;



public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Closet.db";
    private static final int DATABASE_VERSION = 4;

    public static final String CLOTHES_TABLE_NAME = "clothes";
    public static final String CLOTHES_COLUMN_ID = "_id";
    public static final String CLOTHES_COLUMN_NAME = "name";
    public static final String CLOTHES_COLUMN_COLOR = "color";
    public static final String CLOTHES_COLUMN_MATERIAL = "material";
    public static final String CLOTHES_COLUMN_PATTERN = "pattern";
    public static final String CLOTHES_COLUMN_TYPE = "type";
    public int id = 0;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + CLOTHES_TABLE_NAME);

        db.execSQL(
                "CREATE TABLE " + CLOTHES_TABLE_NAME +
                        "(" + CLOTHES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CLOTHES_COLUMN_NAME + " TEXT," +
                        CLOTHES_COLUMN_TYPE + " TEXT," +
                        CLOTHES_COLUMN_COLOR + " TEXT," +
                        CLOTHES_COLUMN_MATERIAL+ " TEXT," +
                CLOTHES_COLUMN_PATTERN + " TEXT" + ")"
		);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
       db.execSQL("DROP TABLE IF EXISTS " + CLOTHES_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertClothes( String name, String type, String color, String material, String pattern) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(CLOTHES_COLUMN_ID, id);
        contentValues.put(CLOTHES_COLUMN_NAME, name);
        contentValues.put(CLOTHES_COLUMN_TYPE, type);
        contentValues.put(CLOTHES_COLUMN_COLOR, color);
        contentValues.put(CLOTHES_COLUMN_MATERIAL, material);
        contentValues.put(CLOTHES_COLUMN_PATTERN, pattern);


        db.insert(CLOTHES_TABLE_NAME, null, contentValues) ;
        Log.v("INSERTING INTO DB...", CLOTHES_COLUMN_ID);
        Log.v("INSERTING INTO DB...", name);
        Log.v("INSERTING INTO DB...", type);
        Log.v("INSERTING INTO DB...", color);
        Log.v("INSERTING INTO DB...", material);
        Log.v("INSERTING INTO DB.", pattern);
        db.close();

        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CLOTHES_TABLE_NAME);
        return numRows;

    }

    public boolean updateClothes(Integer id, String name, String type, String color, String material, String pattern) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLOTHES_COLUMN_ID, id);
        contentValues.put(CLOTHES_COLUMN_NAME, name);
        contentValues.put(CLOTHES_COLUMN_TYPE, type);
        contentValues.put(CLOTHES_COLUMN_COLOR, color);
        contentValues.put(CLOTHES_COLUMN_MATERIAL, material);
        contentValues.put(CLOTHES_COLUMN_PATTERN, pattern);
        db.update(CLOTHES_TABLE_NAME, contentValues, CLOTHES_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        db.close();
        return true;
    }

    public Integer deleteClothes(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CLOTHES_TABLE_NAME,
                CLOTHES_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getClothes(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CLOTHES_TABLE_NAME + " WHERE " +
                CLOTHES_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        res.close();
        db.close();
        return res;
    }

    public ArrayList<Clothes> getAllClothes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + CLOTHES_TABLE_NAME, null );
        ArrayList<Clothes> clothes = new ArrayList<Clothes>();
        res.moveToFirst();
/*
        //if(res != null){
            while (res.isAfterLast() == false) {
                if (res.getInt(res.getColumnIndex(CLOTHES_COLUMN_ID)) == -1) {
                    return null;
                } else {
                    Integer id = res.getInt(res.getColumnIndex(CLOTHES_COLUMN_ID));
                    String name = res.getString(res.getColumnIndex(CLOTHES_COLUMN_NAME));
                    String type = res.getString(res.getColumnIndex(CLOTHES_COLUMN_TYPE));
                    String pattern = res.getString(res.getColumnIndex(CLOTHES_COLUMN_PATTERN));
                    String material = res.getString(res.getColumnIndex(CLOTHES_COLUMN_MATERIAL));

                    Clothes cts = new Clothes();
                    cts.setId(id);
                    cts.setName(name);
                    cts.setType(type);
                    cts.setPattern(pattern);
                    cts.setMaterial(material);

                    Log.v("DBHELPER: ", "ID: " + id);
                    Log.v("DBHELPER: ", "Name: " + name);
                    Log.v("DBHELPER: ", "Type: " + type);
                    Log.v("DBHELPER: ", "Pattern: " + pattern);
                    Log.v("DBHELPER: ", "Material: " + material);
                    clothes.add(cts);

                    res.moveToNext();

                }
            }
       // }

        */

    if(res.moveToFirst()){
        do{

            int id = res.getInt(res.getColumnIndex(CLOTHES_COLUMN_ID));
            String name = res.getString(res.getColumnIndex(CLOTHES_COLUMN_NAME));
            String color = res.getString(res.getColumnIndex(CLOTHES_COLUMN_COLOR));
            String type = res.getString(res.getColumnIndex(CLOTHES_COLUMN_TYPE));
            String pattern = res.getString(res.getColumnIndex(CLOTHES_COLUMN_PATTERN));
            String material = res.getString(res.getColumnIndex(CLOTHES_COLUMN_MATERIAL));
            Clothes newClothes = new Clothes();

            newClothes.setId(id);
            newClothes.setName(name);
            newClothes.setColor(color);
            newClothes.setType(type);
            newClothes.setPattern(pattern);
            newClothes.setMaterial(material);


            clothes.add(newClothes);
        }while(res.moveToNext());
    }
        res.close();
        return clothes;
    }

    public Cursor getBlackClothes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CLOTHES_TABLE_NAME + " WHERE " +
                CLOTHES_COLUMN_COLOR + "=Black",new String[]{String.valueOf(CLOTHES_COLUMN_COLOR)});
        return res;
    }

    public Cursor getBrownClothes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CLOTHES_TABLE_NAME + " WHERE " +
                CLOTHES_COLUMN_COLOR + "=Brown", new String[]{String.valueOf(CLOTHES_COLUMN_COLOR)});
        return res;
    }

    public Cursor getrRedClothes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CLOTHES_TABLE_NAME + " WHERE " +
                CLOTHES_COLUMN_COLOR + "=Red", new String[]{String.valueOf(CLOTHES_COLUMN_COLOR)});
        return res;
    }
    public Cursor getBlueClothes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CLOTHES_TABLE_NAME + " WHERE " +
                CLOTHES_COLUMN_NAME + "=Blue", new String[]{String.valueOf(CLOTHES_COLUMN_COLOR)});
        return res;
    }




}


