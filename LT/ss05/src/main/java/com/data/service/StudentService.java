package com.data.service;

import com.data.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student insertStudent(Student student);
    Student updateStudent(Integer id, Student student);
    boolean deleteStudent(Integer id);
    List<Student> searchStudentsByName(String name);
    List<Student> searchStudentsByClassName(String className);
    List<Student> searchStudentsByAddress(String address);
}
