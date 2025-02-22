package com.springboot.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Documents.StudentDocuments;
import com.springboot.Repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	/*
	 * @PostMapping("/insert") public String addStudents(@RequestBody
	 * StudentDocuments documents) { studentRepository.save(documents); return
	 * "data inserted successfully....!"; }
	 */

	@PostMapping("/insert")
	public ResponseEntity<String> addStudent(@RequestBody StudentDocuments documents) {
		if (studentRepository.existsById(documents.getId())) {
			return ResponseEntity.badRequest().body("ID is already taken.");
		}
		studentRepository.save(documents);
		return ResponseEntity.ok("Data inserted successfully!");
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<StudentDocuments> fetchStudents(@PathVariable int id) {
		Optional<StudentDocuments> studentOpt = studentRepository.findById(id);
		if (studentOpt.isPresent()) {
			return new ResponseEntity<>(studentOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/fetchall")
	public List<StudentDocuments> fetchAllStudents() {
		return studentRepository.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
		Optional<StudentDocuments> studentOpt = studentRepository.findById(id);
		if (studentOpt.isPresent()) {
			studentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updatestudents")
	public ResponseEntity<String> updateStudent(@RequestBody StudentDocuments documents) {
		if (studentRepository.existsById(documents.getId())) {
			studentRepository.save(documents);
			return ResponseEntity.ok("Student data updated successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with the given ID does not exist.");
		}
	}

	@DeleteMapping("/deleteall")
	public ResponseEntity<String> deleteAllStudents() {
		studentRepository.deleteAll();
		return ResponseEntity.ok("All student records have been deleted successfully.");
	}
}
