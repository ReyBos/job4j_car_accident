package ru.reybos.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.reybos.accident.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}