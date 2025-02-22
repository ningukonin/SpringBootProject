package com.springboot.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.Documents.StudentDocuments;

public interface StudentRepository extends MongoRepository<StudentDocuments, Integer> {

}
