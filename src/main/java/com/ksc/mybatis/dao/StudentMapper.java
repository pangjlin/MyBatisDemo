package com.ksc.mybatis.dao;

import com.ksc.mybatis.controller.Student;

public interface StudentMapper {
	Student findStudentById(Integer id); 
	void insertStudent(Student student); 
}
