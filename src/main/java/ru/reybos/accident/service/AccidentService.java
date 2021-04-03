package ru.reybos.accident.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.AccidentType;
import ru.reybos.accident.model.Rule;
import ru.reybos.accident.repository.AccidentHibernate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentService {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentService.class.getName());
    private final AccidentHibernate store;

    public AccidentService(AccidentHibernate store) {
        this.store = store;
    }

    public void index(Model model) {
        List<Accident> accidents = store.findAllAccident();
        model.addAttribute("accidents", accidents);
    }

    public void create(Model model) {
        List<AccidentType> types = store.findAllAccidentType();
        List<Rule> rules = store.findAllRule();
        Accident accident = new Accident();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        model.addAttribute("accident", accident);
    }

    public void save(Accident accident, HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("rIds");
        store.save(accident, ruleIds);
    }

    public void edit(int id, Model model) {
        List<AccidentType> types = store.findAllAccidentType();
        List<Rule> rules = store.findAllRule();
        Optional<Accident> optionalAccident = store.findById(id);
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        model.addAttribute("accident", optionalAccident.get());
    }

    public void delete(int id) {
        store.delete(id);
    }
}
