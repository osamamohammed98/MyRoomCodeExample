package com.example.myroomcodeexample.activitys.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myroomcodeexample.R;
import com.example.myroomcodeexample.activitys.adabter.NoteRecyclerViewAdabter;
import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;
import com.example.myroomcodeexample.activitys.viewModels.NoteViewMode;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * Created by : Eng.OsaMa M AL_Dasoqi
     * 2/3/2020
     * 07:09 pm
     */

    private NoteViewMode noteViewMode;
    private RecyclerView recyclerView;
    private NoteRecyclerViewAdabter adabter;
    public static final String para1 = "para1";
    public static final String para2 = "para2";
    public static final String para3 = "para3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Note List");
        init();
        viewModelWork();
        recyclerWork();
        removItemWork();
        floatingActionBtn();
    }//end onCreate

    private void floatingActionBtn() {

        findViewById(R.id.floating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , AddNoteActivty.class));

            }// end on clcik
        }); // end btn

    }//end method

    private void removItemWork() {

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int index = viewHolder.getAdapterPosition();
                noteViewMode.delete(adabter.getItemAtPostion(index));
            }
        }).attachToRecyclerView(recyclerView);

    }//end method

    private void viewModelWork() {
        noteViewMode = ViewModelProviders.of(this).get(NoteViewMode.class);
        noteViewMode.listLiveData().observe(this, new Observer<List<NoteSimpleModel>>() {
            @Override
            public void onChanged(List<NoteSimpleModel> noteSimpleModels) {
                adabter.addList(noteSimpleModels);
            }
        });
    }//end method

    private void recyclerWork() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adabter);
        adabter.onItemClickLestner(new NoteRecyclerViewAdabter.onItemClickLestener() {
            @Override
            public void onItemClick(NoteSimpleModel model) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivty.class);
                intent.putExtra(para1 ,model.getId());
                intent.putExtra(para2 ,model.getTaskName());
                intent.putExtra(para3 , model.getTaskDescription());
                startActivity(intent);
            }
        }); // end interface adabter
    }//end method

    private void init() {

        recyclerView = findViewById(R.id.rec);
        adabter = new NoteRecyclerViewAdabter();

    }//end method


}
