package org.example.demorabbitmq.repositories;

import org.example.demorabbitmq.entities.PasswordResetHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordResetHistory, Long> {
}

