package com.example.bellezaapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bellezaapp.R;
import com.example.bellezaapp.datos.DBHelper2;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.Projection;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProductFrom extends AppCompatActivity {
    private  String nameProduct, descripcionProduct;
    private byte[] image;

    private final int REQUEST_CODE_GALLERY = 999;
    private Button btnInsertarProduct, btnChooseProduct;
    private EditText editNameProduct;
    private TextView tvDescripcionProduct;
    private ImageView imgSelectedProduct;
    private DBHelper2 dbHelper2;

    private MapController mapController;

    public byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void llenarCampos(){
        nameProduct = editNameProduct.getText().toString().trim();
        descripcionProduct = tvDescripcionProduct.getText().toString().trim();
        image = imageViewToByte(imgSelectedProduct);
    }

    private void limpiarCampos(){
        editNameProduct.setText("");
        tvDescripcionProduct.setText("");
        imgSelectedProduct.setImageResource(R.mipmap.ic_launcher);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_from);

        int zoom = 6;

        btnInsertarProduct = (Button) findViewById(R.id.btnInsertarProduct);
        btnChooseProduct = (Button) findViewById(R.id.btnChooseProduct);
        editNameProduct = (EditText) findViewById(R.id.editNameProduct);
        tvDescripcionProduct = (TextView) findViewById(R.id.tvDescriptionProduct);
        imgSelectedProduct = (ImageView) findViewById(R.id.imgSelectedProduct);
        dbHelper2 = new DBHelper2(getApplicationContext());


        btnInsertarProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarCampos();
                dbHelper2.insertProductos(nameProduct, descripcionProduct, image);
                limpiarCampos();
            }
        });

        btnChooseProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        ProductFrom.this,
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
                imgSelectedProduct.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}