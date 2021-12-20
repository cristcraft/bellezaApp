package com.example.bellezaapp.casos_uso;

import android.database.Cursor;

import com.example.bellezaapp.modelo.Producto;
import com.example.bellezaapp.modelo.Sucursal;

import java.util.ArrayList;

public class CasoUsoProductos {
    public ArrayList<Producto> llenarListaProductos(Cursor cursor){
        ArrayList<Producto> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else{
            while (cursor.moveToNext()){
                Producto producto = new Producto(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getBlob(3)

                );
                list.add(producto);
            }
            return list;
        }
    }
}
