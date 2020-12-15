package com.leanix.todo.ToDoApplication.representations;

import javax.validation.constraints.NotNull;

 
import org.hibernate.validator.constraints.Length;



public class Tasks {
	
	
//	@NotNull
//	@JsonProperty
	private Integer id;
	
	@NotNull @Length(min=2, max=5000)
//	@JsonProperty
	private String Description;
	
	@NotNull @Length(min=2, max=255)
//	@JsonProperty
	private String Tag;
	
	@NotNull @Length(min=2, max=255)
//	@JsonProperty
	private String Status;
	
	@NotNull @Length(min=2, max=255)
//	@JsonProperty
	private String StartDate;
	
	@NotNull @Length(min=2, max=255)
//	@JsonProperty
	private String EndDate;
	
	public Tasks() {
		
	}
	
	public Tasks(String Description, String Tag, String Status, String StartDate, String EndDate) {
		this.Description = Description;
		this.Tag = Tag;
		this.Status = Status;
		this.StartDate = StartDate;
		this.EndDate = EndDate;
		this.id=0;
	}
	
	public Tasks(Integer id, String Description, String Tag, String Status, String StartDate, String EndDate) {
		this.id = id;
		this.Description = Description;
		this.Tag = Tag;
		this.Status = Status;
		this.StartDate = StartDate;
		this.EndDate = EndDate;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	public String getTag() {
		return Tag;
	}
	
	public void setTag(String Tag) {
		this.Tag = Tag;
	}
	
	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String Status) {
		this.Status = Status;
	}
	
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}
	
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String EndDate) {
		this.EndDate = EndDate;
	}
	
	
	
//	 @Override
//	 public String toString() {
//        return "Tasks [id=" + id + ", description=" + Description + ", Tag="
//                + Tag+ ", Status="+ Status + ",StartDate=" + StartDate + ", EndDate=" + EndDate + "]";
//	 }
	
}