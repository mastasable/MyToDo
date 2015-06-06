package com.example.MyToDo;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by admin on 30.05.2015.
 */
public class ToDoList {
    private static ToDoList sToDoList;
    private ArrayList<Task> todoList;
    private Context context;

    public ToDoList(Context appContext) {
        this.todoList = new ArrayList<>();
        this.context = appContext;
    }

    public static ToDoList getInstance(Context c){
        if(sToDoList == null){
            sToDoList = new ToDoList(c.getApplicationContext());
        }
        return sToDoList;
    }

    public ArrayList<Task> getTasks(){
        return todoList;
    }

    public int getCount(){
        return todoList.size();
    }

    public Task getTask(int position){
        return todoList.get(position);
    }

    public void addTask(Task task){
        todoList.add(task);
    }

    public void deleteTask(int position){
        todoList.remove(position);
    }
}
