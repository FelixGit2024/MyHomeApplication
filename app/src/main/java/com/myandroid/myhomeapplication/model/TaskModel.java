package com.myandroid.myhomeapplication.model;

public class TaskModel {
    private String id;
    private String taskContent;

    public TaskModel(String id, String taskContent) {
        this.id = id;
        this.taskContent = taskContent;
    }

    public String getId() {
        return id;
    }

    public String getTaskContent() {
        return taskContent;
    }
}
