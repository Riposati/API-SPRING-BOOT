package com.example.student.APIStudent.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
//		return List.of(new Student(1L, "Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 5), 21));
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}

		System.out.println(student);
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		boolean exists = studentRepository.existsById(id);

		if (!exists) {
			throw new IllegalStateException("Student has been not found!");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Student with id = " + id + " doesn't exists"));

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}
	}
}
