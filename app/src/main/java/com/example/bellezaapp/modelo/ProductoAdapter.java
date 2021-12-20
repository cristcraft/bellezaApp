package com.example.bellezaapp.modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bellezaapp.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Producto> productos;
    LayoutInflater inflater;

    public ProductoAdapter(Context context, ArrayList<Producto> sucursales) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.sucursal_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.imgItemProduct);
        TextView tvIdItem = convertView.findViewById(R.id.tvIdItemProduct);
        TextView tvNameItem = convertView.findViewById(R.id.tvNameItemProduct);
        TextView tvDescripcionItem = convertView.findViewById(R.id.tvDescriptiontemProduct);

        Producto producto = productos.get(position);

        byte[] image = producto.getImageProducto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        imageView.setImageBitmap(bitmap);
        tvIdItem.setText(producto.getIdProducto());
        tvNameItem.setText(producto.getNameProducto());
        tvDescripcionItem.setText(producto.getDescripcionProducto());
        return convertView;
    }
}
