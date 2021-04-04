package ru.reybos.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.reybos.accident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
