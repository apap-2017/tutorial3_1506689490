package com.example.tutorial3.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tutorial3.model.StudentModel;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;

import groovyjarjarantlr.collections.List;

@Controller
public class StudentController {
	private final InMemoryStudentService studentMapper;
	
	public StudentController(){
		studentMapper = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(
	@RequestParam(value = "npm", required = true) String npm,
	@RequestParam(value = "name",required = true) String name,
	@RequestParam(value = "gpa", required = true) double gpa) {
		StudentModel student = new StudentModel(npm, name, gpa);
		studentMapper.addStudent(student);
		return "add";
	}
	
	@RequestMapping("/student/viewall")
	public String studentAll(Model model){
		model.addAttribute("students", studentMapper.selectAllStudents());
		
		return "viewall";
	}
	
//	@RequestMapping("/student/view")
//	public String studentView(@RequestParam(value = "npm", required = true) String npm, Model model){
//		StudentModel s = studentMapper.selectStudent(npm);
//		model.addAttribute("student", s);
//		return "view";
//	}
	
	@RequestMapping(value= {"/student/view", "/student/view/{npm}"})
	public String student(@PathVariable Optional<String> npm, Model model){
		if(npm.isPresent()){
			StudentModel s = studentMapper.selectStudent(npm.get());
			if(s == null){
				return "errorpage";
			}
			model.addAttribute("student", s);
			return "view";
		}
		
		return "errorpage";

	}
	
	@RequestMapping(value= {"/student/delete", "/student/delete/{npm}" } )
	public String delete(@PathVariable Optional<String> npm){
		if(npm.isPresent()){
			boolean isDeleted = studentMapper.deleteStudent(npm.get());
			return isDeleted ? "delete" : "errorpage";
		}
		return "errorpage";
		
	}
}
