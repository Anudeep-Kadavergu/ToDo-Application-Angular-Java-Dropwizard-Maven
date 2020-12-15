package com.leanix.todo.ToDoApplication.representations;

public class TaskTag {

	private Integer taskId;
	
	private Integer tagId;
	
	public TaskTag() {
		
	}
	
	public TaskTag(Integer taskid, Integer tagid) {
		this.taskId = taskid;
		this.tagId = tagid;	
	}
	
	public Integer getTaskId() {
		return taskId;
	}
	
	public Integer getTagId() {
		return tagId;
	}
	
	public void setTaskId(Integer id) {
		this.taskId=id;
	}
	
	public void setTagId(Integer id) {
		this.tagId=id;
	}
}
