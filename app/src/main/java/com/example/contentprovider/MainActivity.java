package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre,et_apellido,et_telefono;
    Button bt_Guardar,bt_Mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre=(EditText) findViewById(R.id.et_nombre);
        et_apellido=(EditText) findViewById(R.id.et_apellido);
        et_telefono=(EditText) findViewById(R.id.et_telefono);

        bt_Guardar=(Button) findViewById(R.id.btn_guardar);
        bt_Mostrar=(Button) findViewById(R.id.bt_Mostrar);

        bt_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 guardar(et_nombre.getText().toString(),et_apellido.getText().toString(),et_telefono.getText().toString());
            }
        });
        bt_Mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Listado.class));
            }
        });

    }

    private void guardar(String nombre, String apellido, String telefono){
        BaseDATOS BaseD = new BaseDATOS(this,"Demo", null,1);
        SQLiteDatabase db = BaseD.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("Nombre",nombre);
            c.put("Apellido",apellido);
            c.put("Telefono",telefono);
            db.insert("CONTACTOS",null,c);
            db.close();
            Toast.makeText(this,"Registro insertado con exito",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Error al insertar",Toast.LENGTH_SHORT).show();
        }
    }
}