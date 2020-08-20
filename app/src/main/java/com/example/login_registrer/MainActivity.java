package com.example.login_registrer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView registrate;
    EditText usuario;
    EditText password;
    Button loginButton;
    databaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrate = findViewById(R.id.registrateMensajeView);
        loginButton = findViewById(R.id.loginButton);
        usuario = findViewById(R.id.userBox);
        password = findViewById(R.id.passwordBox);
        registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registrer.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean cheecker = db.cheecker(usuario.getText().toString(), password.getText().toString());
                if (cheecker == true){
                    Intent intent = new Intent(getApplicationContext(), logeruser.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Bienvenido, usuario.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
