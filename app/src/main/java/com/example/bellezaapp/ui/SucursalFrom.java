package com.example.bellezaapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bellezaapp.R;
import com.example.bellezaapp.datos.DBHelper;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SucursalFrom extends AppCompatActivity {
    private  String name, localization;
    private byte[] image;

    private final int REQUEST_CODE_GALLERY = 999;
    private Button btnInsertar, btnChoose;
    private EditText editName;
    private TextView tvLocalization;
    private ImageView imgSelected;
    private DBHelper dbHelper;

    private MapView mapView;
    private MapController mapController;

    public byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void llenarCampos(){
        name = editName.getText().toString().trim();
        localization = tvLocalization.getText().toString().trim();
        image = imageViewToByte(imgSelected);
    }

    private void limpiarCampos(){
        editName.setText("");
        tvLocalization.setText("");
        imgSelected.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionType = ev.getAction();
        switch (actionType){
            case MotionEvent.ACTION_UP:
                Projection pro = mapView.getProjection();
                GeoPoint loc = (GeoPoint) pro.fromPixels((int) ev.getX(), (int) ev.getY());
                String longitude = Double.toString((double) loc.getLongitudeE6()/1000000);
                String latitude = Double.toString((double) loc.getLatitudeE6()/1000000);

                tvLocalization.setText(latitude+","+ longitude);

                Toast.makeText(getApplicationContext(), "longitud: " + longitude + " latitud: " + latitude, Toast.LENGTH_SHORT).show();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_from);

        int zoom = 6;

        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        editName = (EditText) findViewById(R.id.editName);
        tvLocalization = (TextView) findViewById(R.id.tvLocalization);
        imgSelected = (ImageView) findViewById(R.id.imgSelected);
        dbHelper = new DBHelper(getApplicationContext());

        GeoPoint bogota = new GeoPoint(4.56, -76.50);
        mapView = (MapView) findViewById(R.id.openMap);
        mapView.setBuiltInZoomControls(true);
        mapController = (MapController) mapView.getController();
        mapController.setCenter(bogota);
        mapController.setZoom(zoom);
        mapView.setMultiTouchControls(true);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarCampos();
                dbHelper.insertSucursal(name, localization, image);
                limpiarCampos();
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        SucursalFrom.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(), "Sin permisos", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgSelected.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}