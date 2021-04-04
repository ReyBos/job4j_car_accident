package ru.reybos.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reybos.accident.model.Rule;
import ru.reybos.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    private final RuleRepository repository;

    @Autowired
    public RuleService(RuleRepository repository) {
        this.repository = repository;
    }

    public List<Rule> findAll() {
        List<Rule> rsl = new ArrayList<>();
        repository.findAll().forEach(rsl::add);
        return rsl;
    }
}
