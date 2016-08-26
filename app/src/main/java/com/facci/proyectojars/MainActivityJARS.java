package com.facci.proyectojars;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityJARS extends AppCompatActivity {
    DBHelper dbSQLITE;
    EditText txtIDJARS,txtNombreJARS,txtApellidoJARS,txtRecintoJARS,txtfechaJARS;
    Button btnInsertar,btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_jars);
        dbSQLITE = new DBHelper(this);
    }
    public void InsertarClick(View V){
        txtNombreJARS = (EditText)findViewById(R.id.txtNombreJARS);
        txtApellidoJARS = (EditText)findViewById(R.id.txtApellidoJARS);
        txtRecintoJARS = (EditText)findViewById(R.id.txtRecintoJARS);
        txtfechaJARS = (EditText)findViewById(R.id.txtfechaJARS);
        boolean insertado =dbSQLITE.insertar(txtNombreJARS.getText().toString(),txtApellidoJARS.getText().toString(),txtRecintoJARS.getText().toString(),Integer.parseInt(txtfechaJARS.getText().toString()));
        if(insertado)
            Toast.makeText(MainActivityJARS.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityJARS.this,"Lo sentimos ocurrió un error",Toast.LENGTH_SHORT).show();
    }
    public void verTodosClick(View v){

        Cursor res = dbSQLITE.selectVerTodos();
        if(res.getCount() == 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("ID : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getInt(3)+"\n");
            buffer.append("Año de nacimiento : "+res.getInt(4)+"\n\n");
        }

        mostrarMensaje("Registros",buffer.toString());
    }
   public void mostrarMensaje(String titulo, String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
   public void modificarRegistroClick(View v){
        txtNombreJARS = (EditText) findViewById(R.id.txtNombreJARS);
        txtApellidoJARS = (EditText) findViewById(R.id.txtApellidoJARS);
        txtRecintoJARS = (EditText) findViewById(R.id.txtRecintoJARS);
        txtfechaJARS=(EditText)findViewById(R.id.txtfechaJARS);
        txtIDJARS = (EditText) findViewById(R.id.txtIDJARS);
        boolean estaAcutalizado = dbSQLITE.modificarRegistro(txtIDJARS.getText().toString(),txtNombreJARS.getText().toString(),txtApellidoJARS.getText().toString(),txtRecintoJARS.getText().toString(),Integer.parseInt(txtfechaJARS.getText().toString()));
        if (estaAcutalizado == true){
            Toast.makeText(MainActivityJARS.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityJARS.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarRegistroClick(View v){
        txtIDJARS = (EditText) findViewById(R.id.txtIDJARS);
        Integer registrosEliminados = dbSQLITE.eliminarRegistro(txtIDJARS.getText().toString());
        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityJARS.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityJARS.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }

    }


}
