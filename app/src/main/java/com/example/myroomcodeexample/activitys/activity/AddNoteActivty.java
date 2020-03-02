package com.example.myroomcodeexample.activitys.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.myroomcodeexample.R;
import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;
import com.example.myroomcodeexample.activitys.viewModels.AddViewModel;

public class AddNoteActivty extends AppCompatActivity {
    /**
     * Created by : Eng.OsaMa M AL_Dasoqi
     * 2/3/2020
     * 08:40 pm
     */

    private EditText editTextName, editTextDescrpiton;
    private AddViewModel addViewModel;
    private int id;
    private boolean type;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_activty);
        setTitle("Add Note");
        init();
        checkType();
        viewModelWork();
        saveData();
    }//end onCreate

    private void checkType() {
        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.para1)) {
            type = true;
            findViewById(R.id.save_btn).setBackgroundColor(Color.parseColor(getString(R.string.color_update)));
            id = intent.getIntExtra(MainActivity.para1, -1);
            setTitle("Update");
            editTextName.setText(intent.getStringExtra(MainActivity.para2));
            editTextDescrpiton.setText(intent.getStringExtra(MainActivity.para3));
        } else {
            type = false;
        }
    }

    private void saveData() {

        findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = editTextName.getText().toString().trim();
                final String desc = editTextDescrpiton.getText().toString().trim();
                final NoteSimpleModel model = new NoteSimpleModel(name, desc);
                dialog.setMessage("pleas white while we processing...");
                dialog.setCanceledOnTouchOutside(false);
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc)) {
                    editTextName.setError("pleas enter Note Name !");
                    editTextDescrpiton.setError("pleas enter Note Description !");
                } else {
                    if (type == true) {
                        model.setId(id);
                        addViewModel.update(model);
                        dialog.dismiss();
                        finish();
                    } else {
                        addViewModel.insert(model);
                        dialog.dismiss();
                        finish();
                    }
                }
            }
        });
    }

    private void viewModelWork() {
        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
    }

    private void init() {
        editTextName = findViewById(R.id.editTextName);
        editTextDescrpiton = findViewById(R.id.editTextDescription);
        dialog = new ProgressDialog(this);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);
    }


}//end class
