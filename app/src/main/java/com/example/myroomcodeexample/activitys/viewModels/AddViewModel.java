package com.example.myroomcodeexample.activitys.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;
import com.example.myroomcodeexample.activitys.repostry.NoteRepostry;

/**
 * Created by : Eng.OsaMa M AL_Dasoqi
 * 2/3/2020
 * 08:51 pm
 */
public class AddViewModel extends AndroidViewModel {

    private NoteRepostry noteRepostry;

    public AddViewModel(@NonNull Application application) {
        super(application);
        noteRepostry = new NoteRepostry(application);
    }//end cons

    public void insert(NoteSimpleModel model) {
        noteRepostry.Insert(model);
    }//end method

    public void update(NoteSimpleModel model) {
        noteRepostry.Update(model);
    }//end method

}//end class
