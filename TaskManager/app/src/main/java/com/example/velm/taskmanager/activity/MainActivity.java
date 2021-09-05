package com.example.velm.taskmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.velm.taskmanager.R;
import com.example.velm.taskmanager.adpters.TaskListAdapter;
import com.example.velm.taskmanager.dbhandler.DatabaseHandler;
import com.example.velm.taskmanager.interfaces.TaskInterface;
import com.example.velm.taskmanager.models.Task;
import com.example.velm.taskmanager.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TaskInterface {

    Context context;
    RecyclerView recyclerView;
    List<Task> taskList;
    FloatingActionButton fabCreateTask;
    TaskListAdapter taskListAdapter;
    SharedPreferences sharedPreferences;
    DatabaseHandler db ;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        db = new DatabaseHandler(this);
        taskList = new ArrayList<>();
        sharedPreferences = getSharedPreferences(Utils.MyPREF, Context.MODE_PRIVATE);

        userId = sharedPreferences.getInt("user_id",0);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fabCreateTask = (FloatingActionButton)findViewById(R.id.fab);
        fabCreateTask.setOnClickListener(this);

        //Read Tasks from DB and Display
        displayTask();

    }


    private void createTaskDialog(final boolean isUpdatedTask, final Task currentTask){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.create_task_layout, null);

        Button buttonSaveTask = (Button)alertLayout.findViewById(R.id.buttonSaveTask);
        Button buttonCancelTask = (Button)alertLayout.findViewById(R.id.buttonCancel);

        final EditText editTextTaskName = (EditText)alertLayout.findViewById(R.id.editTextTaskName);
        final EditText editTextTaskDesc = (EditText)alertLayout.findViewById(R.id.editTextTaskDesc);

        if(isUpdatedTask){
            editTextTaskName.setText(currentTask.getName());
            editTextTaskDesc.setText(currentTask.getDescription());
        }

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();
        buttonSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taskName = editTextTaskName.getText().toString();
                String taskDesc = editTextTaskDesc.getText().toString();
                if(taskName != null && taskDesc != null){
                    if(taskName.trim().toString().length() > 0 && taskDesc.trim().toString().length() > 0){
                        DatabaseHandler db = new DatabaseHandler(context);
                        Task task = new Task(taskName,userId,taskDesc,new Date().toString(),new Date().toString());
                        if(isUpdatedTask){
                            task.setId(currentTask.getId());
                            task.setDateCreated(currentTask.getDateCreated());
                            task.setDateUpdated(new Date().toString());
                            db.updateTask(task);
                            displayTask();
                        } else {
                            db.addTask(task);
                            displayTask();
                        }

                        Toast.makeText(context,"Task created successfully ",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context,"Invalid Input ",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonCancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void displayTask(){
        List<Task> taskList = db.getAllTasks(userId);
        taskListAdapter = new TaskListAdapter(context,taskList);
        recyclerView.setAdapter(taskListAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fab:
                createTaskDialog(false,null);
                break;
            default:
                break;
        }
    }

    @Override
    public void updatedTask(Task task) {
        createTaskDialog(true,task);
    }

    @Override
    public void deleteTask(int id) {
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteTask(id);
        displayTask();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                sharedPreferences = getSharedPreferences(Utils.MyPREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(context,"Logout successful",Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(context,LoginActivity.class);
                startActivity(loginIntent);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
