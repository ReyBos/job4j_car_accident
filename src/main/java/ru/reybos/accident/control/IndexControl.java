package ru.reybos.accident.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.reybos.accident.service.AccidentService;

@Controller
public class IndexControl {
    private static final Logger LOG = LoggerFactory.getLogger(IndexControl.class.getName());
    private final AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        service.index(model);
        return "index";
    }
}