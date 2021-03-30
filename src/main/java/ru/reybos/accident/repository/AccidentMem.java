package ru.reybos.accident.repository;

import org.springframework.stereotype.Repository;
import ru.reybos.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;
}
