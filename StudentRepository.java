package org.jsp.springdemo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByNameContainingIgnoreCase(String name);

	Optional<Student> findByemail(String email);

	Optional<Student> findByPhoneno(Long phoneno);

}