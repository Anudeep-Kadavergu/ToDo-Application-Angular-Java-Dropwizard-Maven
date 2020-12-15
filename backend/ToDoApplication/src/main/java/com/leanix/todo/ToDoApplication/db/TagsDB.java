package com.leanix.todo.ToDoApplication.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.leanix.todo.ToDoApplication.representations.Tags;

public class TagsDB {
	public static HashMap<Integer, Tags> tags = new HashMap<>();
	public static Integer Size = 1;
	public static List <Tags> getTags(){
		return new ArrayList<Tags>(tags.values());
	}
	
	public static Tags getTags(Integer id) {
		return tags.get(id);
	}
	
	public static void updateTags(Integer id, Tags tag) {
		tags.put(id, tag);
	}
	
	public static void removeTask(Integer id) {
		tags.remove(id);
	}
	
	public Integer getSize() {
		return Size;
	}
	public void setSize() {
		Size+=1;
	}

}
