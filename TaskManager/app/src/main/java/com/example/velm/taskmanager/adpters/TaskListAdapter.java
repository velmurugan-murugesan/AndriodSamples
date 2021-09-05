package com.example.velm.taskmanager.adpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.velm.taskmanager.R;
import com.example.velm.taskmanager.interfaces.TaskInterface;
import com.example.velm.taskmanager.models.Task;

import java.util.List;

/**
 * Created by velm on 9/8/2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder>{

    Context context;
    List<Task> taskList;
    TaskInterface taskInterface;

    public TaskListAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.taskInterface = (TaskInterface)context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tasklist_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Task task = taskList.get(position);
        holder.textViewTaskName.setText(task.getName());
        holder.textViewTaskDesc.setText(task.getDescription());
        holder.textViewDateCreated.setText(task.getDateCreated());
        holder.textViewDateUpdated.setText(task.getDateUpdated());

        holder.buttonEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskInterface.updatedTask(task);
            }
        });

        holder.buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                taskInterface.deleteTask(task.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTaskName,textViewTaskDesc,textViewDateUpdated,textViewDateCreated;
        Button buttonEditTask,buttonDeleteTask;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTaskName = (TextView)itemView.findViewById(R.id.textViewTaskName);
            textViewTaskDesc = (TextView)itemView.findViewById(R.id.textViewTaskDesc);
            textViewDateCreated = (TextView)itemView.findViewById(R.id.textViewDateCreated);
            textViewDateUpdated = (TextView)itemView.findViewById(R.id.textViewDateUpdated);

            buttonEditTask = (Button)itemView.findViewById(R.id.buttonEditTask);
            buttonDeleteTask = (Button)itemView.findViewById(R.id.buttonDeleteTask);



        }
    }
}
