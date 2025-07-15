package com.data.service;

import com.data.model.entity.Student;
import com.data.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Integer id, Student student) {
        studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(Integer id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getFullname() != null && student.getFullname().contains(name))
                .toList();
    }

    @Override
    public List<Student> searchStudentsByClassName(String className) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getClassName() != null && student.getClassName().contains(className))
                .toList();
    }

    @Override
    public List<Student> searchStudentsByAddress(String address) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getAddress() != null && student.getAddress().contains(address))
                .toList();
    }
}
