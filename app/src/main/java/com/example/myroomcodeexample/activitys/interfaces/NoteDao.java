package com.example.myroomcodeexample.activitys.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;

import java.util.List;
/**
 *
 * Created by : Eng.OsaMa M AL_Dasoqi
 * 2/3/2020
 * 07:09 pm
 *
 * */

@Dao
public interface NoteDao {


    @Insert
    void insert(NoteSimpleModel model);

    @Update
    void update(NoteSimpleModel model);

    @Delete
    void delete(NoteSimpleModel model);

    @Query("DELETE From NoteSimpleModel")
    void deleteAllNote();

    @Query("SELECT *  From NoteSimpleModel")
    LiveData<List<NoteSimpleModel>> getAllNote();

}
