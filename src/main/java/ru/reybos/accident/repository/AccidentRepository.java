package ru.reybos.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.reybos.accident.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @EntityGraph(attributePaths = { "type", "rules" })
    List<Accident> findAll();

    @EntityGraph(attributePaths = { "type", "rules" })
    Optional<Accident> findById(int id);
}