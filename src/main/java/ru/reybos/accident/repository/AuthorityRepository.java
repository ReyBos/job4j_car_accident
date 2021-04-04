package ru.reybos.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.reybos.accident.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}