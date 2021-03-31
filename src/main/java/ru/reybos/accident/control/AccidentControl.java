package ru.reybos.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.repository.AccidentMem;

import java.util.Optional;

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

    @GetMapping("/update")
    public String edit(@RequestParam("id") int id, Model model) {
        Optional<Accident> optionalAccident = store.findById(id);
        if (optionalAccident.isEmpty()) {
            throw new IllegalArgumentException("Инцидент не найден");
        }
        model.addAttribute("accident", optionalAccident.get());
        return "accident/update";
    }
}