package com.leanix.todo.ToDoApplication.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.leanix.todo.ToDoApplication.db.TasksDB;
import com.leanix.todo.ToDoApplication.representations.Tasks;

import com.leanix.todo.ToDoApplication.representations.TaskTag;
import com.leanix.todo.ToDoApplication.representations.TaskID;

import com.leanix.todo.ToDoApplication.db.TagsDB;
import com.leanix.todo.ToDoApplication.representations.Tags;


@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class ToDoController {
	
	private final Validator validator;
	public ToDoController(Validator validator) {
		this.validator = validator;
	}
	
	@GET
	public Response getTasks() {
		return Response.ok(TasksDB.getTasks()).build();
	}
	

	@GET
	@Path("/{id}")
	public Response getTaskById(@PathParam("id") Integer id) {
		Tasks task = TasksDB.getTasks(id);
		if(task!=null)
			return Response.ok(task).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public Response createTask(Tasks task) throws URISyntaxException{
		System.out.println(task.getDescription());
		TasksDB.Size+=1;
		Integer id = TasksDB.Size;
		task.setId(id);
		Tasks t = TasksDB.getTasks(task.getId());
		System.out.println(t==null);
		if(t==null) {
			TasksDB.updateTasks(task.getId(), task);
			return Response.ok(task.getId()).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
    @Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateTasksById(@PathParam("id") Integer id, Tasks task) {
        // validation
//        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Tasks e = TasksDB.getTasks(task.getId());
//        if (violations.size() > 0) {
//            ArrayList<String> validationMessages = new ArrayList<String>();
//            for (ConstraintViolation<Employee> violation : violations) {
//                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
//            }
//            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
//        }
        if (e != null) {
            task.setId(id);
            TasksDB.updateTasks(id, task);
            return Response.ok(task).build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
 
    @DELETE
    @Path("/{id}")
    public Response removeTasksById(@PathParam("id") Integer id) {
        Tasks task = TasksDB.getTasks(id);
        if (task != null) {
            TasksDB.removeTask(id);
            return Response.ok().header("Access-Control-Allow-Origin", "*").build();
        } else
            return Response.status(Status.NOT_FOUND).build();

    }
    
    @GET
    @Path("/tags")
    public Response getTags() {
    	return Response.ok(TagsDB.getTags()).build();
    }
    
    @POST
    @Path("/tags")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTags(Tags tag) throws URISyntaxException{
		TagsDB.Size+=1;
		Integer id = TagsDB.Size;
		tag.setId(id);
		Tags t = TagsDB.getTags(tag.getId());
		System.out.println(t==null);
		if(t==null) {
			TagsDB.updateTags(tag.getId(), tag);
			return Response.created(new URI("/todos/tags/"+tag.getId())).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
    }
    
    @POST
    @Path("/assigntag")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignTags(TaskTag tasktag) throws URISyntaxException{
			Tasks t1 = TasksDB.getTasks(tasktag.getTaskId());
			System.out.println(tasktag.getTaskId());
			Tags t2 = TagsDB.getTags(tasktag.getTagId());
			if(t1 == null || t2 == null) {
				return Response.status(Status.NOT_FOUND).build();	
			}
			else {
				t1.setTag(t2.getTag());
				System.out.println(t1.getTag());
				return Response.ok(tasktag.getTaskId()).build();
			}
    }
    
  
    
    @POST
    @Path("/markcomplete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response markComplete(Tasks task) throws URISyntaxException{
			Tasks t1 = TasksDB.getTasks(task.getId());
			if(t1 == null ) {
				return Response.status(Status.NOT_FOUND).build();	
			}
			else {
				t1.setStatus("completed");
				return Response.created(new URI("/todos/tags/")).build();
			}
    }
    
    @POST
    @Path("/updatestatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatus(TaskID taskid) throws URISyntaxException{
		System.out.println(taskid.getId());
		Tasks t1 = TasksDB.getTasks(taskid.getId());
		t1.setStatus(taskid.getStatus());
		return Response.created(new URI("/todos/tags/")).build();
    }
}
