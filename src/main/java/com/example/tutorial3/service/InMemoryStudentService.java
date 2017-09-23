package com.example.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService {
	
	private static List<StudentModel> studentList = new ArrayList<>();
	@Override
	public StudentModel selectStudent(String npm) {
		// TODO Auto-generated method stub
		for(StudentModel s : studentList){
			if(s.getNpm().equals(npm)){
				return s;
			}
		}
		return null;
	}

	@Override
	public List<StudentModel> selectAllStudents() {
		// TODO Auto-generated method stub
		return studentList;
	}

	@Override
	public void addStudent(StudentModel student) {
		studentList.add(student);
		
	}

	@Override
	public boolean deleteStudent(String npm) {
		for(int i = 0; i < studentList.size(); i++){
			StudentModel s = studentList.get(i);
			if(s.getNpm().equals(npm)){
				studentList.remove(i);
				return true;
			}
		}
		
		return false;
			
	}

}
