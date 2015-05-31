package com.example.MyToDo;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by admin on 30.05.2015.
 */
public class TodoAdapter extends BaseAdapter {
    private ToDoList toDoList;
    private Context context;
    private static TodoAdapter sTodoAdapter;

    public TodoAdapter(Context context, ToDoList toDoList) {
        this.toDoList = toDoList.getInstance(context);
        this.context = context;
    }

    public static TodoAdapter getInstance(Context c, ToDoList tdl){
        if(sTodoAdapter == null){
            sTodoAdapter = new TodoAdapter(c, tdl);
        }
        return sTodoAdapter;
    }

    @Override
    public int getCount() {
        return toDoList.getCount();
    }

    @Override
    public Object getItem(int position) {
        return toDoList.getTask(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_list_item, null);
        fillView(convertView, position);

        return convertView;
    }

    private void fillView(View v, int position){
        Task task = toDoList.getTask(position);
        View todoView = v;

        if (todoView == null){
            todoView = LayoutInflater.from(context).inflate(R.layout.todo_list_item, null);
        }

        TextView tvTask = (TextView) todoView.findViewById(R.id.tvTask);
        tvTask.setText(task.getTitle());

        if(task.isAchieved()){
            todoView.findViewById(R.id.imChecked).setVisibility(View.VISIBLE);
            todoView.setBackgroundColor(R.color.grey);
        } else {
            todoView.findViewById(R.id.imChecked).setVisibility(View.GONE);
        }

        TextView tvDate = (TextView) todoView.findViewById(R.id.tvDate);
        tvDate.setText(DateFormat.format("dd.MM.yyyy", task.getDate()));

    }
}
