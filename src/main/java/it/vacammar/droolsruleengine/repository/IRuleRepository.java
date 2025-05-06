package it.vacammar.droolsruleengine.repository;

import it.vacammar.droolsruleengine.entity.CustomRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRuleRepository extends JpaRepository<CustomRule, Integer> {
}
