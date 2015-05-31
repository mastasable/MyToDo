package com.example.MyToDo;

/**
 * Created by admin on 30.05.2015.
 */
public class Task {
    private String title;
    private long date;
    private boolean achieved;
    private int id;

    public Task() {
        this.achieved = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String task) {
        this.title = task;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
