package ru.reybos.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.reybos.accident.model.AccidentType;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
