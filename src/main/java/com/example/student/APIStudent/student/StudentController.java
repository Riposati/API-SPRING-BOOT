package com.example.student.APIStudent.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStudent(@PathVariable("id") Long id) {
		studentService.deleteStudent(id);
	}

	@PutMapping("/edit/{id}")
	// localhost:8080/api/v1/student/edit/1?name=Maria Jose&email=maria@gmail.com // EXEMPLO DE URL PRA TESTAR
	public void updateStudent(@PathVariable("id") Long id, @RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {

		studentService.updateStudent(id, name, email);
	}
}
