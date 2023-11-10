package com.zero.elmotellacanitaalaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ArrayList<Clientes> listaClientes;
    ListView Listado;
    ClientesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Listado = findViewById(R.id.LIstado);

        listaClientes = getIntent().getParcelableArrayListExtra("listaClientes");
        adapter = new ClientesAdapter(this,listaClientes);
        Listado.setAdapter(adapter);


            for (int i = 0; i < listaClientes.size(); i++) {
                if (listaClientes.get(i).getEstado() == true)
                    adapter.notifyDataSetChanged();
            }
    }

}