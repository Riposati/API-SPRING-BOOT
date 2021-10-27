package com.example.student.APIStudent.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//	@Query("select s from Student s where s.email = ?1") // SQL do Hibernate (HQL)
	Optional<Student> findStudentByEmail(String email);
}
