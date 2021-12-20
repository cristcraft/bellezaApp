package com.example.bellezaapp.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private  SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "belleza.db", null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE PRODUCTOS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nameProduct VARCHAR, " +
                "descriptionProduct VARCHAR," +
                "imageProduct BLOB)");

    db.execSQL("CREATE TABLE SUCURSALES(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name VARCHAR, " +
            "lacalization VARCHAR," +
            "image BLOB)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SUCURSALES");
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
    }

    public void insertSucursal(String name, String localization, byte[] image){
        String sql = "INSERT INTO SUCURSALES VALUES(null, ?, ?, ?)";

        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, localization);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    public Cursor getSucursales(){
        Cursor cursor =  sqLiteDatabase.rawQuery("SELECT * FROM SUCURSALES", null);
        return cursor;
    }

    public void insertProductos(String nameProduct, String descriptionProduct, byte[] imageProduct){
        String sql = "INSERT INTO Productos VALUES(null, ?, ?, ?)";

        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, nameProduct);
        statement.bindString(2, descriptionProduct);
        statement.bindBlob(3, imageProduct);

        statement.executeInsert();
    }

    public Cursor getProductos(){
        Cursor cursor =  sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS", null);
        return cursor;
    }


}
