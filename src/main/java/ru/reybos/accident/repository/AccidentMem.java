package ru.reybos.accident.repository;

import org.springframework.stereotype.Repository;
import ru.reybos.accident.model.Accident;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private AtomicInteger accidentsId = new AtomicInteger(1);

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentsId.getAndIncrement());
        }
        accidents.put(accident.getId(), accident);
    }

    public Map<Integer, Accident> findAllAccident() {
        return accidents;
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }
}
