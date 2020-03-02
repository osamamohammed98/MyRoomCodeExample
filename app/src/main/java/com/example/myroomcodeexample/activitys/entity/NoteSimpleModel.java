package com.example.myroomcodeexample.activitys.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/**
 *
 * Created by : Eng.OsaMa M AL_Dasoqi
 * 2/3/2020
 * 07:09 pm
 *
 * */

@Entity(tableName = "NoteSimpleModel")
public class NoteSimpleModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "taskName")
    private String taskName;
    @ColumnInfo(name = "taskDescription")
    private String taskDescription;


    public NoteSimpleModel(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
