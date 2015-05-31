package com.example.MyToDo;

import android.app.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.Calendar;
import java.util.Random;

public class TodoActivity extends Activity{
    private ToDoList toDoList;
    private ListView lvTodoList;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);
        toDoList = ToDoList.getInstance(this);
        lvTodoList = (ListView) findViewById(R.id.llTodoList);
        createTasks();
        fillList();
        lvTodoList.setOnCreateContextMenuListener(this);

    }

    public void fillList(){
        adapter = TodoAdapter.getInstance(this, ToDoList.getInstance(this));
        lvTodoList.setAdapter(adapter);
    }

    public void createTasks(){
        Random r = new Random();
        Calendar c = Calendar.getInstance();
        c.roll(Calendar.YEAR, -2);
        long date = c.getTimeInMillis();
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            task.setTitle("Купить хлеб");
            task.setDate(r.nextLong() % date);
            task.setId(i);
            toDoList.addTask(task);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo aMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final View view = getLayoutInflater().inflate(R.layout.edit_task_dialog, null);
        int position = aMenuInfo.position;
        adapter = TodoAdapter.getInstance(this, ToDoList.getInstance(this));
        final Task task = (Task) adapter.getItem(position);

        menu.add(R.string.edit).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle(R.string.edit)
                        .setView(view)
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which1) {
                                EditText editTask = (EditText) view.findViewById(R.id.editTaskDialog);
                                String chngTask = editTask.getText().toString();
                                task.setTitle(chngTask);
                            }
                        })
                        .create()
                        .show();
                return true;
            }
        });
    }
}
