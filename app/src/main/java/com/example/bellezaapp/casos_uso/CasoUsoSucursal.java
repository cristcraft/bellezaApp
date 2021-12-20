package com.example.bellezaapp.casos_uso;

import android.database.Cursor;

import com.example.bellezaapp.modelo.Sucursal;

import java.util.ArrayList;

public class CasoUsoSucursal {
    public ArrayList<Sucursal> llenarListaSucursales(Cursor cursor){
        ArrayList<Sucursal> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else{
            while (cursor.moveToNext()){
                Sucursal sucursal = new Sucursal(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getBlob(3)

                );
                list.add(sucursal);
            }
            return list;
        }
    }
}
