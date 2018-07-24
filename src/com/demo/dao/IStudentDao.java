package com.demo.dao;

import java.util.ArrayList;

import com.demo.entity.Student;

public interface IStudentDao {
	public Student findStudentByNumberAndPwd(String number,String password);
	public Student findStudentById(Integer id);
	public void modifyStudentPwd(String n_pwd,String pwd,Integer id);
	public void modifyStudentPhoto(String n_photo,Integer id);
	public void editStudent(Student student);
	public ArrayList<Student> findAllStudent();
	public ArrayList<Student> findSomeStudentByNumber(String find);
	public ArrayList<Student> findSomeStudentBySname(String find);
	public ArrayList<Student> findSomeStudentByAddress(String find);
	public void addOneStudent(Student student);
	public void deleteOne(int id);
	public void modifyOne(Student student);
	
}
