package com.example.todolist;

public class ToDoItem {
    private int id;
    private String item;
    private String date;
    private boolean done;


    public ToDoItem(int id, String item, String date, boolean done) {
        this.id = id;
        this.item = item;
        this.date = date;
        this.done = done;
    }

    public String getItem(){return item;}

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

    public boolean isDone(){return done;}

    public int getId(){return id;}
}
