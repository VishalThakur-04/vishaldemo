package com.example.dbdemo.data;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;



import com.example.dbdemo.params.Params;
import com.example.dbdemo.model.Coontact;
import com.google.android.material.shape.ShapePath;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {


    public MyDbHandler( Context context) {
        super(context , Params.DB_NAME, null, Params.DB_VERSION);

    }

    @Override
      public void onCreate(SQLiteDatabase db) {
        String Create = "CREATE TABLE " + Params.TABLE_NAME+ "("
                 + Params.KEY_ID + " INTEGER PRIMARY KEY, "  + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PHONE + "TEXT" + ")";

        Log.d("helloG" , "Query being run is :"+Create);
        db.execSQL(Create);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Coontact contact)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME , contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        db.insert(Params.TABLE_NAME,null,values);
        Log.d("helloG" , "successfully inserted" );
        db.close();


    }

    public List<Coontact> getAllCoontacts()
    {
        List<Coontact> coontactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        //generate the query to read from database

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);

        //loop through now

        if(cursor.moveToFirst()){

            do {
                Coontact contact = new Coontact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                coontactList.add(contact);

            }
            while (cursor.moveToNext());

            }

        return coontactList;
        }

        public int updateContact(Coontact contact)
        {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Params.KEY_NAME, contact.getName());
            values.put(Params.KEY_PHONE, contact.getPhoneNumber());


            // lets update now

            return  db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?",
                    new String[]{String.valueOf(contact.getId())});
        }



        public void deleteContactById(int id)
        {

            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Params.TABLE_NAME,Params.KEY_ID + "=?", new String[]{String.valueOf(id)});
            db.close();


        }
    public void deleteContact(Coontact contact)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();


    }

    public int getCount()
    {
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
    }



