package com.leanix.todo.ToDoApplication.representations;

public class TaskID {
	
	private Integer id;
	private String status;
	
	public TaskID() {
		
	}
	public TaskID(Integer idtag, String status) {
		this.id=idtag;
		this.status=status;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
