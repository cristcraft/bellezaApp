package com.example.bellezaapp.ui.slideshow;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bellezaapp.R;
import com.example.bellezaapp.casos_uso.CasoUsoProductos;
import com.example.bellezaapp.casos_uso.CasoUsoSucursal;
import com.example.bellezaapp.datos.DBHelper;
import com.example.bellezaapp.datos.DBHelper2;
import com.example.bellezaapp.modelo.Producto;
import com.example.bellezaapp.modelo.ProductoAdapter;
import com.example.bellezaapp.modelo.SucurlasAdapter;
import com.example.bellezaapp.modelo.Sucursal;
import com.example.bellezaapp.ui.ProductFrom;
import com.example.bellezaapp.ui.SucursalFrom;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private GridView gridView;
    private DBHelper2 dbHelper2;
    private ArrayList<Producto> producto = new ArrayList<>();
    private CasoUsoProductos casoUsoProductos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        try{
            dbHelper2 = new DBHelper2(getContext());
            casoUsoProductos = new CasoUsoProductos();
            Cursor cursor = dbHelper2.getProductos();
            producto = casoUsoProductos.llenarListaProductos(cursor);

            gridView = (GridView) root.findViewById(R.id.gridViewProductos);
            ProductoAdapter productoAdapter = new ProductoAdapter(root.getContext(), producto);
            gridView.setAdapter(productoAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            /*case R.id.add_product:
                Toast.makeText(getContext(), "Agregar Produto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ProductFrom.class);
                startActivity(intent);
                return true;*/

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}