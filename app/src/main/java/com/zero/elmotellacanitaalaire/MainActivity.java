package com.zero.elmotellacanitaalaire;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int Id=1;
    boolean Estado = true;
    TextView txtUsuario;
    EditText edtVelocidad, edtTemperatura;
    RadioButton rbAlfa, rbBeta, rbGamma;
    Button btnRegistrar, btnReporte,btnCerrarSesion;
    RadioGroup Combos;
    ArrayList<Clientes> listaClientes;

    ClientesAdapter adapter;
    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarios loggedInUser = getIntent().getParcelableExtra("loggedInUser");

        txtUsuario = findViewById(R.id.txtUsuario);
        edtVelocidad = findViewById(R.id.edtVelocidad);
        edtTemperatura = findViewById(R.id.edtTemperatura);
        rbAlfa = findViewById(R.id.rbAlfa);
        rbBeta = findViewById(R.id.rbBeta);
        rbGamma = findViewById(R.id.rbGamma);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnReporte = findViewById(R.id.btnReporte);
        if (!loggedInUser.getRol().equals("admin")) {
            btnReporte.setVisibility(View.GONE);
        }
        txtUsuario.setText("Bienvenido, " + loggedInUser.getUsuario());
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        Combos = findViewById(R.id.Combos);

                listaClientes = getIntent().getParcelableArrayListExtra("listaClientes");
        if(listaClientes == null)
        listaClientes = new ArrayList<>();

        adapter = new ClientesAdapter(this,listaClientes);
    }

    public void guardar(View v) {
        try {
            double velocidad = Double.parseDouble(edtVelocidad.getText().toString().trim());
            double Temperatura = Double.parseDouble(edtTemperatura.getText().toString().trim());
            if (velocidad < 0) {
                Toast.makeText(this, "Ingrese una velocidad válida ", Toast.LENGTH_LONG).show();
                return;
            }

            if (!rbAlfa.isChecked() && !rbBeta.isChecked() && !rbGamma.isChecked()){
                Toast.makeText(this, "Seleccione una zona", Toast.LENGTH_LONG).show();
                return;
            }
            String zona = "";

            if (rbAlfa.isChecked()) {
                zona = "Zona: Alfa";

            } else if (rbBeta.isChecked()) {
                zona = "Zona: Beta";

            } else if (rbGamma.isChecked()) {
                zona = "Zona: Gamma";

            }

            String fechaIngreso = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());


            listaClientes.add(new Clientes(Id, velocidad, Temperatura, Estado, zona, fechaIngreso ));
            Id++;

            Toast.makeText(this, "Se ha registrado con éxito!!", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese una temperatura o velocidad válida", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void reporte(View v) {
        try {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putParcelableArrayListExtra("listaClientes", (ArrayList<? extends Parcelable>) listaClientes);
            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(this, "Error inesperado", Toast.LENGTH_LONG).show();
        }
    }
    public void cerrarsession(View v) {
        try {
            Intent intent = new Intent(this, login.class);
            intent.putParcelableArrayListExtra("listaClientes", (ArrayList<? extends Parcelable>) listaClientes);
            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(this, "Error inesperado", Toast.LENGTH_LONG).show();
        }
    }


    public void mostrarClientesinactivos(View v) {

        try {
            for (int i = 0; i < listaClientes.size(); i++) {
                if (listaClientes.get(i).getEstado() == false)
                    adapter.notifyDataSetChanged();
            }
        }catch (Exception ex)
        {
            Toast.makeText(this, "Error inesperado", Toast.LENGTH_LONG).show();
        }
    }


}