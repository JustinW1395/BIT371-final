package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements TaskDialog.TaskDialogListener{

    RecyclerView ToDo;
    DBtodo dbHelp;
    ListAdapter adapter;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToDo = findViewById(R.id.todo_list);
        ToDo.setLayoutManager(new LinearLayoutManager(this));

        dbHelp = new DBtodo(getApplicationContext(), DBtodo.DATABASE_NAME, null, DBtodo.VERSION);

        adapter = new ListAdapter( dbHelp.getAllItems(),getSupportFragmentManager(), dbHelp);

        ToDo.setAdapter(adapter);

        TaskDialog taskDialog = new TaskDialog();

        FloatingActionButton fab = findViewById(R.id.add_item_btn);
        fab.setOnClickListener((v) -> taskDialog.show(getSupportFragmentManager(), "Create a to do item"));
    }


    @Override
    public void setItem(String task, String date) {
        if(TextUtils.isEmpty(task) || TextUtils.isEmpty(date)){
            Toast.makeText(this, "Nothing was submitted", Toast.LENGTH_SHORT).show();
            Log.i("INFO", "Nothing inserted");
        } else {
            int id = dbHelp.InsertItem(task, date);
            adapter.toDoItems.add(new ToDoItem(id,task, date, false));
            adapter.notifyItemInserted(adapter.toDoItems.size());

        }
    }
}