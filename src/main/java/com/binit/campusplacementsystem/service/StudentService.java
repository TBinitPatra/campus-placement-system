package com.binit.campusplacementsystem.service;

import com.binit.campusplacementsystem.model.Student;
import org.springframework.stereotype.Service;
import com.binit.campusplacementsystem.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(int id, Student newStudent) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(newStudent.getName());
        existingStudent.setEmail(newStudent.getEmail());
        existingStudent.setCourse(newStudent.getCourse());
        existingStudent.setBranch(newStudent.getBranch());
        existingStudent.setCgpa(newStudent.getCgpa());
        existingStudent.setSkills(newStudent.getSkills());

        return studentRepository.save(existingStudent);
    }

    public String deleteStudent(int id) {
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
}
