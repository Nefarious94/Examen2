package com.example.todo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TodoViewHolder>
        implements View.OnClickListener {

    ArrayList<Todo> lista;
    private View.OnClickListener listener;

    public RecyclerAdapter(ArrayList<Todo> lista) {
        this.lista = lista;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null, false);
        view.setOnClickListener(this);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.name.setText(lista.get(position).getName());
        holder.date.setText(lista.get(position).getDate());
        holder.complete.setText(lista.get(position).getComplete().toString());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView name, date, complete;

        public TodoViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            date = (TextView) itemView.findViewById(R.id.Date);
            complete = itemView.findViewById(R.id.Complete);
        }
    }
}