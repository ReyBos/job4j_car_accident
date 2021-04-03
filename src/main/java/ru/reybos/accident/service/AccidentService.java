package ru.reybos.accident.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.AccidentType;
import ru.reybos.accident.model.Rule;
import ru.reybos.accident.repository.AccidentHibernate;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentService.class.getName());
    private final AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    public List<Accident> findAllAccident() {
        return store.findAllAccident();
    }

    public List<AccidentType> findAllAccidentType() {
        return store.findAllAccidentType();
    }

    public List<Rule> findAllRule() {
        return store.findAllRule();
    }

    public void save(Accident accident, String[] ruleIds) {
        store.save(accident, ruleIds);
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public void delete(int id) {
        store.delete(id);
    }
}
