package com.example.myroomcodeexample.activitys.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;
import com.example.myroomcodeexample.activitys.repostry.NoteRepostry;

import java.util.List;

/**
 *
 * Created by : Eng.OsaMa M AL_Dasoqi
 * 2/3/2020
 * 07:40 pm
 *
 **/
public class NoteViewMode extends AndroidViewModel {

    private NoteRepostry noteRepostry;
    private LiveData<List<NoteSimpleModel>> listLiveData;

    public NoteViewMode(@NonNull Application application) {
        super(application);

        noteRepostry = new NoteRepostry(application);
        listLiveData = noteRepostry.listLiveData();

    }//end constructor

    public void insert(NoteSimpleModel model) {
        noteRepostry.Insert(model);
    }//end method

    public void update(NoteSimpleModel model) {
        noteRepostry.Update(model);
    }//end method

    public void delete(NoteSimpleModel model) {
        noteRepostry.Delete(model);
    }//end method

    public void deleteAllNote() {
        noteRepostry.DeleteAllNote();
    }//end method

    public LiveData<List<NoteSimpleModel>> listLiveData() {
        return listLiveData;
    }
}//end class
