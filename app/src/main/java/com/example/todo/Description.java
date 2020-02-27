package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Description extends AppCompatActivity {

    TextView name, date, complete;
    SQLHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        conn = new SQLHelper(getApplicationContext(), "bd_todo", null, 1);

        name = findViewById(R.id.Name_task);
        date = findViewById(R.id.Date_task);
        complete = findViewById(R.id.checkBox2);

        Bundle objetoEnviado = getIntent().getExtras();
        Todo todo = null;

        if (objetoEnviado != null) {
            todo = (Todo) objetoEnviado.getSerializable("todo");
            name.setText(todo.getName());
            date.setText(todo.getDate());
            complete.setText(todo.getComplete().toString());

        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Actualizar:
                actualizarUsuario();
                break;
            case R.id.Eliminar:
                eliminarUsuario();
                break;
        }
    }


    private void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {date.getText().toString()};

        db.delete(Tabla.TABLA_TODO, Tabla.CAMPO_DATE + "=?", parametros);
        Toast.makeText(getApplicationContext(), "Se ha eliminado", Toast.LENGTH_LONG).show();
        db.close();
    }

    private void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {date.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Tabla.CAMPO_NAME, name.getText().toString());
        values.put(Tabla.CAMPO_COMPLETE, complete.getText().toString());

        db.update(Tabla.TABLA_TODO, values, Tabla.CAMPO_DATE + "=?", parametros);

        Toast.makeText(getApplicationContext(), "Se ha actualizado", Toast.LENGTH_LONG).show();
        db.close();
    }
}