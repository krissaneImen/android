package com.example.speciset;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="user";
    public static final String TABLE_NAME ="table_user";
    public static final String COL_1 ="id";
    public static final String COL_2 ="nom";
    public static final String COL_3 ="prenom";
    public static  final String COL_4 ="email";
    public static  final String COL_5 ="password";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase bd) {
        String usercreate="CREATE TABLE table_user( id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT , prenom TEXT , email TEXT , password TEXT)";
        bd.execSQL(usercreate);
    }
    public long insertion(user user) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", user.getNom());
        values.put("prenom", user.getPrenom());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        bd.insert("table_user", "", values);
        return 0;
    }
    public List<user> getAllusers() {
        List<user> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex(COL_2));
                @SuppressLint("Range") String prenom = cursor.getString(cursor.getColumnIndex(COL_3));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COL_4));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COL_5));
                user user = new user(nom, prenom, email, password);
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    public void deleteUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_4 + "=?", new String[]{email});
        db.close();
    }
    public void updateUser(user user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, user.getId());
        values.put(COL_2, user.getNom());
        values.put(COL_3, user.getPrenom());
        values.put(COL_4, user.getEmail());
        values.put(COL_5, user.getPassword());

        db.update("table_user", values, COL_1+ " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }


    @SuppressLint("Range")
    public String getStoredEmail() {
        SQLiteDatabase db = this.getReadableDatabase();
        String email = "";

        Cursor cursor = db.rawQuery("SELECT " + COL_4 + " FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndex(COL_4));
        }

        cursor.close();
        db.close();

        return email;
    }

    @SuppressLint("Range")
    public String getStoredPassword() {
        SQLiteDatabase db = this.getReadableDatabase();
        String password = "";

        Cursor cursor = db.rawQuery("SELECT " + COL_5 + " FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(COL_5));
        }

        cursor.close();
        db.close();

        return password;
    }


    public user getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        user userData = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + "=?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String nom = cursor.getString(cursor.getColumnIndex(COL_2));
            @SuppressLint("Range") String prenom = cursor.getString(cursor.getColumnIndex(COL_3));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(COL_4));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COL_5));

            userData = new user(nom, prenom, email, password);
            userData.setId(userId); // Définir l'ID de l'utilisateur
        }

        cursor.close();
        db.close();

        return userData;
    }

}

