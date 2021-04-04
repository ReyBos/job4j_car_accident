package ru.reybos.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reybos.accident.model.AccidentType;
import ru.reybos.accident.repository.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentTypeService {
    private final AccidentTypeRepository accidentTypeRepository;

    @Autowired
    public AccidentTypeService(AccidentTypeRepository accidentTypeRepository) {
        this.accidentTypeRepository = accidentTypeRepository;
    }

    public List<AccidentType> findAll() {
        List<AccidentType> rsl = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(rsl::add);
        return rsl;
    }
}
