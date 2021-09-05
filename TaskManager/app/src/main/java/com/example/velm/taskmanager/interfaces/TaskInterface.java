package com.example.velm.taskmanager.interfaces;

import com.example.velm.taskmanager.models.Task;

/**
 * Created by velm on 9/9/2017.
 */

public interface TaskInterface {

     public void updatedTask(Task task);

     public void deleteTask(int id);

}
