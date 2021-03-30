package ru.reybos.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.reybos.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        Map<Integer, Accident> accidents = new HashMap<>();
        accidents.put(1, Accident.of(
                "Превышение скорости",
                "Превышение разрешенной скорости на 20км/ч",
                "Санкт-Петербург, Пулковское шоссе"
        ));
        accidents.put(2, Accident.of(
                "Красный свет",
                "Проезд на запрещающий сигнал светофора",
                "Москва, Красная площадь"
        ));
        accidents.put(3, Accident.of(
                "Пересечение сплошной",
                "Пересечение двойной сплошной линии",
                "Новосибирск, Красных партизан"
        ));
        model.addAttribute("accidents", accidents);
        return "index";
    }
}