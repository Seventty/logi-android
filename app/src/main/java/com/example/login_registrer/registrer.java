package com.example.login_registrer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registrer extends AppCompatActivity {

    databaseHelper db;
    TextView iniciosesion;
    EditText usuario, correo, password, repassword;
    Button botonRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        db = new databaseHelper(this);
        usuario = findViewById(R.id.usuarioText);
        correo = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        repassword = findViewById(R.id.repasswordText);
        iniciosesion = findViewById(R.id.inicioSesionText);
        botonRegistro = findViewById(R.id.botonRegistro);
        iniciosesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuario.getText().toString() == null || correo.getText().toString() == null || password.getText().toString() == null || repassword.getText().toString() == null){
                    Toast.makeText(registrer.this, "Llena todos los campos primero", Toast.LENGTH_SHORT).show();
                }
                if (password.getText().toString() != repassword.getText().toString()){
                    Toast.makeText(registrer.this, "Las contraseñas deben de ser iguales.", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean usuariocheck = db.usuariocheck(usuario.getText().toString());
                    Boolean correocheck = db.correoCheck(correo.getText().toString());
                    if (usuariocheck == true && correocheck == true){
                        Boolean insert = db.insert(usuario.getText().toString(),correo.getText().toString(),password.getText().toString());
                        if (insert == true){
                            Intent intent = new Intent(getApplicationContext(), registro_completo.class);
                            Toast.makeText(registrer.this, "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }else{
                            Toast.makeText(registrer.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(registrer.this, "El usuario o el correo ya existen.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
