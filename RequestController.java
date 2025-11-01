package org.jsp.springdemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class RequestController {
	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping("/save")
	public Student saveStudent(@RequestBody Student std) {
		Student student = studentRepository.save(std);
		return student;
	}

	@RequestMapping("/saveAll")
	public List<Student> saveAllStudent(@RequestBody List<Student> std) {
		return studentRepository.saveAll(std);
	}

	@RequestMapping("/update")
	public Student update(@RequestBody Student std) {
		return studentRepository.save(std);
	}

	@RequestMapping("/find")
	public Student find(@RequestParam("id") Integer id) {
		Optional<Student> wrapper = studentRepository.findById(id);
		return wrapper.get();
	}

	@RequestMapping("/findall")
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@RequestMapping("/findallbyid")
	public List<Student> findAllById(@RequestBody List<Integer> sid) {
		return studentRepository.findAllById(sid);
	}

	@PutMapping("/updateMobile/{id}")
	public Student updateMobile(@PathVariable Integer id, @RequestBody Student s) {
		Student std = studentRepository.findById(id).get();
		std.setPhoneno(s.getPhoneno());
		return studentRepository.save(std);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		studentRepository.deleteById(id);
		return "Student Deleted Successfully with ID: " + id;
	}

	@RequestMapping("/deleteall")
	public String deleteAll() {
		studentRepository.deleteAll();
		return "all student deleted";
	}

	@RequestMapping("/deleteallbyid")
	public String deleteAllById(@RequestBody List<Integer> sids) {
		studentRepository.deleteAllById(sids);
		return "all given students are deleted";
	}

	@RequestMapping("/count")
	public long count() {
		return studentRepository.count();
	}

	@GetMapping("/searchByName")
	public List<Student> searchByName(@RequestParam String name) {
		return studentRepository.findByNameContainingIgnoreCase(name);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Student> findByEmail(@PathVariable String email) {
		return studentRepository.findByemail(email).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/phone/{phoneno}")
	public ResponseEntity<Student> findByPhone(@PathVariable Long phoneno) {
		return studentRepository.findByPhoneno(phoneno).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
