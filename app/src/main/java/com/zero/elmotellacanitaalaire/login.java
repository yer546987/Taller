package com.zero.elmotellacanitaalaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    EditText edtUsuario,edtPass;
    Button btnIngresar;
    ArrayList<usuarios> listaUsuarios;
    ArrayList<Clientes> listaClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPass = findViewById(R.id.edtPass);
        btnIngresar = findViewById(R.id.btnIngresar);
        listaUsuarios = new ArrayList<>();

        listaUsuarios.add(new usuarios("Yeferson", "1234", "admin"));
        listaUsuarios.add(new usuarios("usuario", "1234", "user"));
        listaClientes = getIntent().getParcelableArrayListExtra("listaClientes");
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsuario.getText().toString();
                String password = edtPass.getText().toString();

               usuarios loggedInUser = isValidLogin(username, password);
                if (loggedInUser != null) {
                    if (loggedInUser.getRol().equals("admin")) {

                        Toast.makeText(login.this, "Inicio de sesión exitoso como administrador", Toast.LENGTH_SHORT).show();
                    } else if (loggedInUser.getRol().equals("user")) {

                        Toast.makeText(login.this, "Inicio de sesión exitoso como usuario", Toast.LENGTH_SHORT).show();
                    }



                    Intent intent = new Intent(login.this, MainActivity.class);
                    intent.putParcelableArrayListExtra("listaClientes", (ArrayList<? extends Parcelable>) listaClientes);
                    intent.putExtra("loggedInUser", loggedInUser);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(login.this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private usuarios isValidLogin(String username, String password) {
        for (usuarios user : listaUsuarios) {
            if (user.getUsuario().equals(username) && user.getPass().equals(password)) {
                return user; //
            }
        }
        return null;
    }


}