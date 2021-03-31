package ru.reybos.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem store;

    public AccidentControl(AccidentMem store) {
        this.store = store;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        store.save(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit() {
        return "accident/edit";
    }

    @GetMapping("/update")
    public String update() {
//        store.update(int id, Accident accident);
        return "redirect:/";
    }
}