package com.example.myroomcodeexample.activitys.adabter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myroomcodeexample.R;
import com.example.myroomcodeexample.activitys.entity.NoteSimpleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : Eng.OsaMa M AL_Dasoqi
 * 2/3/2020
 * 08:08 pm
 */

public class NoteRecyclerViewAdabter extends RecyclerView.Adapter<NoteRecyclerViewAdabter.NoteViewHolder> {

    private List<NoteSimpleModel> modelList = new ArrayList<>();
    private onItemClickLestener lestener;

    public void addList(List<NoteSimpleModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        return new NoteViewHolder(rootView);
    }//end method

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteSimpleModel model = modelList.get(position);
        holder.textView.setText(model.getTaskName());

    }//end method

    @Override
    public int getItemCount() {
        return modelList.size();
    }//end method

    public NoteSimpleModel getItemAtPostion(int postiton) {
        return modelList.get(postiton);
    }//end method

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.taskName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if(lestener !=null && index != RecyclerView.NO_POSITION){
                        lestener.onItemClick(modelList.get(index));
                        Toast.makeText(v.getContext(), ""+index, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }// end class

    public interface onItemClickLestener {
        void onItemClick(NoteSimpleModel model);
    }//end class

    public void onItemClickLestner(onItemClickLestener lestener1){
        lestener = lestener1;
    }//end method
}// end class
