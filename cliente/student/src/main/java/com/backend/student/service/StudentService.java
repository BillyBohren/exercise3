package com.backend.student.service;


import com.backend.student.model.Student;
import com.backend.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(int id) {
        return studentRepository.getOne(id);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

}
