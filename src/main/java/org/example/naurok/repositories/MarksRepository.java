package org.example.naurok.repositories;

import org.example.naurok.tables.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {
    Marks findByStudentUUID(String UUID);
}
