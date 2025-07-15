package com.data.controller;

import com.data.model.dto.response.DataResponse;
import com.data.model.entity.Student;
import com.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Student>>> getStudents() {
        return new ResponseEntity<>(new DataResponse<>(studentService.getAllStudents(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Student>> getStudentById(@PathVariable int id) {
        return new ResponseEntity<>(new DataResponse<>(studentService.getStudentById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Student>> insertStudent(Student student) {
        return new ResponseEntity<>(new DataResponse<>(studentService.insertStudent(student), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DataResponse<Student>> updateStudent(@RequestParam int id, @RequestBody Student student) {
        return new ResponseEntity<>(new DataResponse<>(studentService.updateStudent(id, student), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DataResponse<Boolean>> deleteStudent(@RequestParam int id) {
        return new ResponseEntity<>(new DataResponse<>(studentService.deleteStudent(id), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/student-name/{fullname}")
    public ResponseEntity<DataResponse<List<Student>>> searchStudentsByName(@PathVariable String fullname) {
        return new ResponseEntity<>(new DataResponse<>(studentService.searchStudentsByName(fullname), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/class-name/{className}")
    public ResponseEntity<DataResponse<List<Student>>> searchStudentsByClassName(@PathVariable String className) {
        return new ResponseEntity<>(new DataResponse<>(studentService.searchStudentsByClassName(className), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<DataResponse<List<Student>>> searchStudentsByAddress(@PathVariable String address) {
        return new ResponseEntity<>(new DataResponse<>(studentService.searchStudentsByAddress(address), HttpStatus.OK), HttpStatus.OK);
    }
}
