package com.example.myroomcodeexample.activitys.repostry;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;
import com.example.myroomcodeexample.activitys.interfaces.NoteDao;
import com.example.myroomcodeexample.activitys.room.NoteRoomDB;

import java.util.List;

/**
 *
 * Created by : Eng.OsaMa M AL_Dasoqi
 * 2/3/2020
 * 07:23 pm
 *
 **/

public class NoteRepostry {

    private NoteDao noteDao;
    private LiveData<List<NoteSimpleModel>> listLiveData;

    public NoteRepostry(Application application) {

        //get singleton object from db
        //then equation interface
        //then equation list with method

        NoteRoomDB noteRoomDB = NoteRoomDB.getInstance(application);
        noteDao = noteRoomDB.noteDao();
        listLiveData = noteDao.getAllNote();
    }//end constrictor


    public void Insert(NoteSimpleModel model) {
        new InsertThread(noteDao).execute(model);
    }//end method

    public void Update(NoteSimpleModel model) {
        new UpdateThread(noteDao).execute(model);
    }//end method

    public void Delete(NoteSimpleModel model) {
        new DeleteThread(noteDao).execute(model);
    }//end method

    public void DeleteAllNote() {
        new DeleteAllThread(noteDao).execute();
    }//end method

    public LiveData<List<NoteSimpleModel>> listLiveData() {
        return listLiveData;
    }//end method

    private static class InsertThread extends AsyncTask<NoteSimpleModel, Void, Void> {
        private NoteDao noteDao;

        public InsertThread(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteSimpleModel... noteSimpleModels) {
            noteDao.insert(noteSimpleModels[0]);
            return null;
        }
    }// end thread

    private static class UpdateThread extends AsyncTask<NoteSimpleModel, Void, Void> {

        private NoteDao noteDao;

        public UpdateThread(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteSimpleModel... noteSimpleModels) {
            noteDao.update(noteSimpleModels[0]);
            return null;
        }
    }//end thread

    private static class DeleteThread extends AsyncTask<NoteSimpleModel, Void, Void> {
        private NoteDao noteDao;

        public DeleteThread(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteSimpleModel... noteSimpleModels) {
            noteDao.delete(noteSimpleModels[0]);
            return null;
        }
    }//end thread


    private static class DeleteAllThread extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public DeleteAllThread(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNote();
            return null;
        }
    }//end thread

}//end class
