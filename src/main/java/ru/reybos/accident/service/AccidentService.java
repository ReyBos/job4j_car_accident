package ru.reybos.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.AccidentType;
import ru.reybos.accident.model.Rule;
import ru.reybos.accident.repository.AccidentMem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public void index(Model model) {
        List<Accident> accidents = store.findAllAccident();
        model.addAttribute("accidents", accidents);
    }

    public void create(Model model) {
        List<AccidentType> types = store.findAllAccidentType();
        List<Rule> rules = store.findAllRule();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
    }

    public void save(Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        List<Rule> rules = store.findAllRule();
        for (String rId : rIds) {
            Rule rule = new Rule();
            rule.setId(Integer.parseInt(rId));
            int index = rules.indexOf(rule);
            accident.addRule(rules.get(index));
        }
        store.save(accident);
    }

    public void edit(int id, Model model) {
        Optional<Accident> optionalAccident = store.findById(id);
        if (optionalAccident.isEmpty()) {
            throw new IllegalArgumentException("Инцидент не найден");
        }
        List<AccidentType> types = store.findAllAccidentType();
        List<Rule> rules = store.findAllRule();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        model.addAttribute("accident", optionalAccident.get());
    }
}
