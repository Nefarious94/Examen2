package com.example.todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Todo> listaTodo;
    RecyclerView recyclerView;

    SQLHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn = new SQLHelper(getApplicationContext(), "bd_todo", null, 1);

        listaTodo = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.todo_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        consultarListaTodo();

        final RecyclerAdapter adapter = new RecyclerAdapter(listaTodo);

        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String informacion="Name: "+listaTodo.get(recyclerView.getChildAdapterPosition(v)).getName()+"\n";
                informacion+= "Date: "+listaTodo.get(recyclerView.getChildAdapterPosition(v)).getDate()+"\n";
                informacion+="Complete: "+listaTodo.get(recyclerView.getChildAdapterPosition(v)).getComplete()+"\n";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

                //para hacer que cuando se clique se ponga una nueva actividad con los datos
                Todo todo=listaTodo.get(recyclerView.getChildAdapterPosition(v));

                Intent intent = new Intent(MainActivity.this,Description.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("todo",todo);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void consultarListaTodo() {
        SQLiteDatabase db= conn.getReadableDatabase();

        Todo Todo=null;

        Cursor cursor =db.rawQuery("SELECT * FROM "+ Tabla.TABLA_TODO,null);

        while (cursor.moveToNext()){
            Todo=new Todo();
            Todo.setName(cursor.getString(0));
            Todo.setDate(cursor.getString(1));
            Todo.setComplete(cursor.getInt(2));

            listaTodo.add(Todo);

        }
    }

    public void onClick(View view){
        Intent miIntent=null;
        switch(view.getId()){
            case R.id.floatingActionButton:
                fabalert();
                break;
        }
        if(miIntent !=null ){
            startActivity(miIntent);
        }
    }

    public void fabalert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add todo task");

        final EditText input = new EditText(this);
        final EditText input2 = new EditText(this);
        final EditText input3 = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        input2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        input3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        input.setHint("Name");
        layout.addView(input);

        input2.setHint("Date");
        layout.addView(input2);

        input3.setHint("is Complete");
        layout.addView(input3); // Another add method

        // Again this is a set method, not add
        builder.setView(layout);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SQLHelper conn = new SQLHelper(getApplicationContext(), "bd_todo", null, 1);

                SQLiteDatabase db = conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Tabla.CAMPO_NAME, input.getText().toString());
                values.put(Tabla.CAMPO_DATE, input2.getText().toString());
                values.put(Tabla.CAMPO_COMPLETE, input3.getText().toString());

                Long idResultante = db.insert(Tabla.TABLA_TODO, Tabla.CAMPO_COMPLETE, values);
                Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
                db.close();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
