package com.example.bellezaapp.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bellezaapp.R;
import com.example.bellezaapp.casos_uso.CasoUsoSucursal;
import com.example.bellezaapp.databinding.FragmentHomeBinding;
import com.example.bellezaapp.datos.DBHelper;
import com.example.bellezaapp.modelo.SucurlasAdapter;
import com.example.bellezaapp.modelo.Sucursal;
import com.example.bellezaapp.ui.ProductFrom;
import com.example.bellezaapp.ui.SucursalFrom;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private GridView gridView;
    private DBHelper dbHelper;
    private ArrayList<Sucursal> sucursales = new ArrayList<>();
    private CasoUsoSucursal casoUsoSucursal;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        try{
            dbHelper = new DBHelper(getContext());
            casoUsoSucursal = new CasoUsoSucursal();
            Cursor cursor = dbHelper.getSucursales();
            sucursales = casoUsoSucursal.llenarListaSucursales(cursor);

            gridView = (GridView) root.findViewById(R.id.gridViewSucursales);
            SucurlasAdapter sucursalAdapter = new SucurlasAdapter(root.getContext(), sucursales);
            gridView.setAdapter(sucursalAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "Agregar Producto", Toast.LENGTH_SHORT).show();
                Intent intentProduct = new Intent(getContext(), ProductFrom.class);
                startActivity(intentProduct);
                return true;*/

            case R.id.add_sucursal:
                Toast.makeText(getContext(), "Agregar Sucursal", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SucursalFrom.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}