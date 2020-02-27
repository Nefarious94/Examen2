package com.example.todo;

import java.io.Serializable;

public class Todo implements Serializable {

    private String name;
    private String date;
    private Integer complete;

    public Todo(String name, String date, Integer complete) {
        this.name = name;
        this.date = date;
        this.complete = complete;
    }

    public Todo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }
}
