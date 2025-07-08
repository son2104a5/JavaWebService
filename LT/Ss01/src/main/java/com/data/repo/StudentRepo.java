package com.data.repo;

import com.data.entity.Student;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRepo {
    @Getter
    private static final List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("1", "Nguyễn Đắc Sơn", "son@gmail.com", "0123456789", "Hà Nội"));
        students.add(new Student("2", "Nguyễn Văn A", "a@gmail.com", "0987654321", "Hải Phòng"));
        students.add(new Student("3", "Trần Thị B", "b@gmail.com", "0123456789", "Đà Nẵng"));
        students.add(new Student("4", "Lê Văn C", "c@gmail.com", "0987654321", "Hồ Chí Minh"));
    }

    public static boolean addStudent(Student student) {
        return students.add(student);
    }

    public static boolean updateStudent(Student student) {
        boolean b = students.stream().anyMatch(s -> Objects.equals(s.getId(), student.getId()));
        if (b) {
            return false;
        }
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                students.remove(s);
                students.add(student);
                break;
            }
        }
        return true;
    }

    public static Student getStudentById(int id) {
        return students.stream().filter(s -> s.getId().equals(String.valueOf(id))).
                findFirst().orElse(null);
    }
}
