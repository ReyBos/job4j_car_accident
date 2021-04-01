package ru.reybos.accident.repository;

import org.springframework.stereotype.Repository;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private AtomicInteger accidentsId = new AtomicInteger(1);
    private Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentsId.getAndIncrement());
        }
        AccidentType newType = types.get(accident.getType().getId());
        accident.setType(newType);
        accidents.put(accident.getId(), accident);
    }

    public Map<Integer, Accident> findAllAccident() {
        return accidents;
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    public Collection<AccidentType> findAllAccidentType() {
        return types.values();
    }
}
