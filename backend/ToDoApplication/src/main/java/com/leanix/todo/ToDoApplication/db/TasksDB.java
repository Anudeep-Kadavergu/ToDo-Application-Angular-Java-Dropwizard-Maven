package com.leanix.todo.ToDoApplication.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.leanix.todo.ToDoApplication.representations.Tasks;

public class TasksDB {
	
	public static HashMap<Integer, Tasks> tasks = new HashMap<>();
	public static Integer Size = 1;
	public static List <Tasks> getTasks(){
		return new ArrayList<Tasks>(tasks.values());
	}
	
	public static Tasks getTasks(Integer id) {
		return tasks.get(id);
	}
	
	public static void updateTasks(Integer id, Tasks task) {
		tasks.put(id, task);
	}
	
	public static void removeTask(Integer id) {
		tasks.remove(id);
	}
	
	public Integer getSize() {
		return Size;
	}
	public void setSize() {
		Size+=1;
	}
}
