package com.leanix.todo.ToDoApplication.representations;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class Tags {

	
	
//	@NotNull
//	@JsonProperty
	private Integer id;
	
	@NotNull @Length(min=2, max=5000)
//	@JsonProperty
	private String Tag;
	
	
	public Tags() {
		
	}
	
	public Tags(String Tag) {
		this.Tag = Tag;
	}
	
	public Tags(Integer id, String Tag) {
		this.id = id;
		this.Tag = Tag;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTag() {
		return Tag;
	}
	public void setTag(String Tag) {
		this.Tag = Tag;
	}
	
	
	
//	 @Override
//	 public String toString() {
//        return "Tasks [id=" + id + ", description=" + Description + ", Tag="
//                + Tag+ ", Status="+ Status + ",StartDate=" + StartDate + ", EndDate=" + EndDate + "]";
//	 }

}