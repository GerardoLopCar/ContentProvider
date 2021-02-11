package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    EditText et_nombre,et_apellido,et_telefono;
    Button btn_modificar,btn_Eliminar;
    int id;
    String nombre;
    String apellido;
    String telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b=getIntent().getExtras();
        if (b!=null){
            id=b.getInt("Id");
            nombre=b.getString("Nombre");
            apellido=b.getString("Apellido");
            telefono=b.getString("Telefono");
        }
        et_nombre=(EditText) findViewById(R.id.et_nombre);
        et_apellido=(EditText) findViewById(R.id.et_apellido);
        et_telefono=(EditText) findViewById(R.id.et_telefono);

        et_nombre.setText(nombre);
        et_apellido.setText(apellido);

        btn_modificar=(Button) findViewById(R.id.btn_guardar);
        btn_Eliminar=(Button) findViewById(R.id.btn_Eliminar);

        btn_Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();

            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id,et_nombre.getText().toString(),et_apellido.getText().toString(),et_telefono.getText().toString());
                onBackPressed();

            }
        });




    }

    private void Modificar(int Id, String Nombre, String Apellido, String Telefono){
        BaseDATOS BaseD = new BaseDATOS(this,"Demo", null,1);
        SQLiteDatabase db = BaseD.getWritableDatabase();

        String sql="update contactos set nombre'"+ Nombre + ", Apellido='"+Apellido+"Telefono='"+Telefono+"'where Id='"+Id;
        db.close();
    }
    private void Eliminar(int Id){
        BaseDATOS BaseD = new BaseDATOS(this,"Demo", null,1);
        SQLiteDatabase db = BaseD.getWritableDatabase();

        String sql="delete from contactos where Id="+Id;
        db.close();
    }
}