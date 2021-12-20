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

public class SucurlasAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sucursal> sucursales;
    LayoutInflater inflater;

    public SucurlasAdapter(Context context, ArrayList<Sucursal> sucursales) {
        this.context = context;
        this.sucursales = sucursales;
    }

    @Override
    public int getCount() {
        return sucursales.size();
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

        ImageView imageView = convertView.findViewById(R.id.imgItem);
        TextView tvIdItem = convertView.findViewById(R.id.tvIdItem);
        TextView tvNameItem = convertView.findViewById(R.id.tvNameItem);
        TextView tvLocalizationItem = convertView.findViewById(R.id.tvLocalizationItem);

        Sucursal sucursal = sucursales.get(position);

        byte[] image = sucursal.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        imageView.setImageBitmap(bitmap);
        tvIdItem.setText(sucursal.getId());
        tvNameItem.setText(sucursal.getName());
        tvLocalizationItem.setText(sucursal.getLacalization());
        return convertView;
    }
}
