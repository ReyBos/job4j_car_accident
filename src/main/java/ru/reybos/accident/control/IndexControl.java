package ru.reybos.accident.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.service.AccidentService;

import java.util.List;

@Controller
public class IndexControl {
    private static final Logger LOG = LoggerFactory.getLogger(IndexControl.class.getName());
    private final AccidentService service;

    @Autowired
    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = service.findAll();
        model.addAttribute("accidents", accidents);
        model.addAttribute(
                "user", SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
        return "index";
    }
}