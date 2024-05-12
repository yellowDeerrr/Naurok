package org.example.naurok.repositories;

import org.example.naurok.tables.TestQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionsRepository extends JpaRepository<TestQuestions, Long> {
    List<TestQuestions> findByTestUUID(String testUUID);
}
