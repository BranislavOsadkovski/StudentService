package com.school.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest; 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 

import com.school.objects.Student;
import com.school.util.StudentJDBCTemplate; 

@Path(value = "StudentService")
public class StudentService {
	 private List <Student> list;
	 @Inject HttpServletRequest request;
	 private StudentJDBCTemplate template;
	 
	@GET
	@Path(value="getallstudents")
	@Produces(MediaType.APPLICATION_XML)
	public List<Student> getAllStudents() {
		 list=null; 
		 template = (StudentJDBCTemplate)request.getServletContext().getAttribute("studentTemplate"); 
				list= template.getAllStudents();
	return list;
		
	}
	
	@GET
	@Path(value="student/{id}") 
	@Produces(MediaType.APPLICATION_XML)
	public Student getStudent(@PathParam(value = "id")Integer id) {
		template = (StudentJDBCTemplate)request.getServletContext().getAttribute("studentTemplate"); 
		Student s = template.getStudent(id);
		return s;
	}
	@GET
	@Path(value="student/{name: [a-zA-Z][a-zA-Z_0-9]*}") 
	@Produces(MediaType.APPLICATION_XML)
	public Student getStudent(@PathParam(value = "name")String name) {
		template = (StudentJDBCTemplate)request.getServletContext().getAttribute("studentTemplate"); 
		Student s = template.getStudentByName(name);
		return s;
	}
	
	@GET
	@Path(value="studentImage/{id}") 
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public byte[] getStudentImage(@PathParam(value = "id")Integer id) {
		template = (StudentJDBCTemplate)request.getServletContext().getAttribute("studentTemplate"); 
		byte[] image = template.getStudentImage(id);
		return image;
	}
	
}
