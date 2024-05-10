package org.example.naurok.repositories;

import org.example.naurok.tables.TestAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestAnswersRepository extends JpaRepository<TestAnswers, Long> {
}
