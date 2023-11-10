package com.zero.elmotellacanitaalaire;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClientesAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<Clientes> lista;
    public ClientesAdapter(Activity activity, ArrayList<Clientes> lista){
        this.activity = activity;
        this.lista = lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)  activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.lista, null);
        }
        TextView txtVelocidad =v.findViewById(R.id.txtVelocidad);
        TextView txtTemperatura =v.findViewById(R.id.txtTemperatura);
        TextView txtZona = v.findViewById(R.id.txtZona);
        TextView txtFechaIngreso =v.findViewById(R.id.txtFechaIngreso);



        txtVelocidad.setText("Velocidad: "+String.valueOf(lista.get(i).getVelocidad()));
        txtTemperatura.setText(String.valueOf("Temperatura: "+lista.get(i).getTemperatura()));
        txtZona.setText(lista.get(i).getZona());
        txtFechaIngreso.setText("Fecha de registro: "+lista.get(i).getFechaIngreso());


        return v;
    }
}
