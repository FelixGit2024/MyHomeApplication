package com.myandroid.myhomeapplication.adapt;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.myandroid.myhomeapplication.R;
import com.myandroid.myhomeapplication.model.TaskModel;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapt extends ArrayAdapter<TaskModel> {
    private Context context;
    private ArrayList<TaskModel> taskModels;
    private int viewResourceId;

    public TaskAdapt(@NonNull Context context, int resource, @NonNull List<TaskModel> objects) {
        super(context, resource, objects);
        this.context=context;
        this.taskModels= (ArrayList<TaskModel>) objects;
        this.viewResourceId=resource;
    }


    @Override
    public int getCount() {
        return taskModels.size();
    }

    @Override
    public TaskModel getItem(int position) {
        return taskModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView=inflater.inflate(viewResourceId,parent,false);
        }
        TaskModel task = taskModels.get(position);
        TextView tvId = convertView.findViewById(R.id.tv_id);
        TextView tvContent = convertView.findViewById(R.id.tv_content);
        tvId.setText(task.getId());
        tvContent.setText(task.getTaskContent());
        return convertView;
    }
}
