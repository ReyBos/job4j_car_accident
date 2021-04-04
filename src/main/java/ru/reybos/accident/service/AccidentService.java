package ru.reybos.accident.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.Rule;
import ru.reybos.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentService.class.getName());
    private final AccidentRepository repository;

    @Autowired
    public AccidentService(AccidentRepository repository) {
        this.repository = repository;
    }

    public List<Accident> findAll() {
        List<Accident> rsl = new ArrayList<>();
        rsl.addAll(repository.findAll());
        return rsl;
    }

    public void save(Accident accident, String[] ruleIds) {
        for (String rId : ruleIds) {
            Rule rule = new Rule();
            rule.setId(Integer.parseInt(rId));
            accident.addRule(rule);
        }
        repository.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
