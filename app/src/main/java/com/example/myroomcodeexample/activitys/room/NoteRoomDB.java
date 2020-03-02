package com.example.myroomcodeexample.activitys.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;
import com.example.myroomcodeexample.activitys.interfaces.NoteDao;

/**
 * Created by : Eng.OsaMa M AL_Dasoqi
 * 2/3/2020
 * 07:09 pm
 */

@Database(entities = NoteSimpleModel.class, version = 1, exportSchema = false)
public abstract class NoteRoomDB extends RoomDatabase {

    private static NoteRoomDB instance;

    public abstract NoteDao noteDao();

    //Singlton
    public static synchronized NoteRoomDB getInstance(Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext()
                    , NoteRoomDB.class
                    , "NoteSimpleModel")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();

        }
        return instance;
    }


    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new ThreadForRoom(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };


    private static class ThreadForRoom extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public ThreadForRoom(NoteRoomDB noteDaos) {
            noteDao = noteDaos.noteDao();
        }

        // for testing it work or note
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new NoteSimpleModel("task Name 1", "bla bla bla bla bla bla bla \n" +
                    "bla bla bla bla bla bla bla \n" +
                    "bla bla bla bla bla bla bla"));
            noteDao.insert(new NoteSimpleModel("task Name 2", "bla bla bla bla bla bla bla \n" +
                    "bla bla bla bla bla bla bla \n" +
                    "bla bla bla bla bla bla bla"));
            noteDao.insert(new NoteSimpleModel("task Name 3", "bla bla bla bla bla bla bla \n" +
                    "bla bla bla bla bla bla bla \n" +
                    "bla bla bla bla bla bla bla"));
            return null;
        }
    }
}
