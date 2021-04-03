package ru.reybos.accident.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.AccidentType;
import ru.reybos.accident.model.Rule;
import ru.reybos.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class AccidentControl {
    private static final Logger LOG = LoggerFactory.getLogger(AccidentControl.class.getName());
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = service.findAllAccidentType();
        model.addAttribute("types", types);
        List<Rule> rules = service.findAllRule();
        model.addAttribute("rules", rules);
        Accident accident = new Accident();
        model.addAttribute("accident", accident);
        return "accident/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("rIds");
        service.save(accident, ruleIds);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(@RequestParam("id") int id, Model model) {
        List<AccidentType> types = service.findAllAccidentType();
        model.addAttribute("types", types);
        List<Rule> rules = service.findAllRule();
        model.addAttribute("rules", rules);
        Optional<Accident> optionalAccident = service.findById(id);
        model.addAttribute("accident", optionalAccident.get());
        return "accident/form";
    }

    @GetMapping("/delete")
    public String edit(@RequestParam("id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}