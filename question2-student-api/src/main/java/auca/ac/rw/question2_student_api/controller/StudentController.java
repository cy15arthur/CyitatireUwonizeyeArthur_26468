package auca.ac.rw.question2_student_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.question2_student_api.model.Student;

@RestController
@RequestMapping("/api/students")

public class StudentController {
  List<Student> students = new ArrayList<>();

  public StudentController() {
    students.add(new Student(1L, "Arthur", "Cyitatire", "arthur@auca.ac.rw", "Software Engineering", 3.8));
    students.add(new Student(2L, "Alice", "Umuhoza", "alice@test.com", "Information Technology", 3.2));
    students.add(new Student(3L, "Bob", "Nshuti", "bob@test.com", "Computer Science", 3.6));
    students.add(new Student(4L, "Diane", "Keza", "diane@test.com", "Business Administration", 2.9));
    students.add(new Student(5L, "Eric", "Mugisha", "eric@test.com", "Software Engineering", 3.5));
  }
@GetMapping
public List<Student> getallStudents() {
    return students;
}
@GetMapping("/{studentId}")
public Student getStudentById(@PathVariable Long studentId) {
    for (Student s : students) {
        if (s.getStudentId().equals(studentId)) {
            return s;
        }
    }
    return null;
   }

   @GetMapping("/major/{major}")
public List<Student> getStudentsbyMajor(@PathVariable String major) {
    List<Student> foundStudents = new ArrayList<>();
    
    for (Student s : students) {
        if (s.getMajor().equalsIgnoreCase(major)) {
            foundStudents.add(s);
        }
    }
    return foundStudents;
  }
@GetMapping("/filter")
public List<Student> filterStudentsByGpa(@RequestParam Double gpa) {
    List<Student> results = new ArrayList<>();
    for (Student student : students) {
       
        if (student.getGpa() >= gpa) {
            results.add(student);
        }
    }
    return results;
}
@PostMapping
public Student registerStudent(@RequestBody Student newStudent) {
    students.add(newStudent);
    return newStudent;
}
@PutMapping("/{studentId}")
public Student updateStudent(@PathVariable Long studentId, @RequestBody Student updatedInfo) {
    for (Student s : students) {
        if (s.getStudentId().equals(studentId)) {
            s.setFirstName(updatedInfo.getFirstName());
            s.setLastName(updatedInfo.getLastName());
            s.setEmail(updatedInfo.getEmail());
            s.setMajor(updatedInfo.getMajor());
            s.setGpa(updatedInfo.getGpa());
            
            return s; 
        }
    }
    return null;
}

}
