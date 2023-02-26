package org.softparadigm.bankbackend.repository;

import org.softparadigm.bankbackend.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentRepository extends JpaRepository<Agent, String> {
}
