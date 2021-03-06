package com.example.ardent.myschoolbus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;


public class ProgramingAdapter extends RecyclerView.Adapter<ProgramingAdapter.ProgramingViewHolder>{



    List<StudentData> studentDataList;


    public ProgramingAdapter(ArrayList<StudentData>studentDataList) {
        this.studentDataList=new ArrayList<>();
        this.studentDataList = studentDataList;
    }


    @NonNull
    @Override
    public ProgramingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item_layout,viewGroup,false);
        return new ProgramingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramingViewHolder programingViewHolder, int i) {
        String name = studentDataList.get(i).name;
        programingViewHolder.textView.setText(name);
    }

    @Override
    public int getItemCount() {
        return studentDataList.size();
        // return  1;
    }

    public  class ProgramingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ProgramingViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageviewListItemLayout);
            textView = (TextView) itemView.findViewById(R.id.textviewListItemLayout);
        }
    }
}
