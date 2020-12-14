package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ToDoListViewHolder>{

    ArrayList<ToDoItem> toDoItems;
    FragmentManager fragmentManager;
    DBtodo DBHelper;

    public ListAdapter(ArrayList<ToDoItem> toDoItems,
                       FragmentManager fragmentManager,
                       DBtodo DBHelper){
        this.toDoItems = toDoItems;
        this.fragmentManager = fragmentManager;
        this.DBHelper = DBHelper;

    }

    @NonNull
    @Override
    public ToDoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_layout, null);
        return new ToDoListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListViewHolder holder, int position) {
        ToDoItem item = toDoItems.get(position);
        holder.doneCheckBox.setChecked(item.isDone());
        holder.itemTextView.setText(item.getItem());
        holder.dateTextView.setText(item.getDate());

        holder.doneCheckBox.setOnCheckedChangeListener((v, checked)-> {
            DBHelper.setDone(toDoItems.get(holder.getAdapterPosition()).getId(), checked);
        });
    }

    @Override
    public int getItemCount() { return toDoItems.size(); }

    public class ToDoListViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        CheckBox doneCheckBox;
        TextView dateTextView;

        public ToDoListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.item_text);
            doneCheckBox = itemView.findViewById(R.id.item_checkbox);
            dateTextView = itemView.findViewById(R.id.item_date);
        }
    }
}
