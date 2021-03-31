package ru.reybos.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.repository.AccidentMem;

import java.util.Map;

@Controller
public class IndexControl {
    private final AccidentMem store;

    public IndexControl(AccidentMem store) {
        this.store = store;
    }

    @GetMapping("/")
    public String index(Model model) {
        Map<Integer, Accident> accidents = store.findAllAccident();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}