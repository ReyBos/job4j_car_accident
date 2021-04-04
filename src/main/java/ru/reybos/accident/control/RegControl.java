package ru.reybos.accident.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.reybos.accident.model.Accident;
import ru.reybos.accident.model.User;
import ru.reybos.accident.repository.AuthorityRepository;
import ru.reybos.accident.repository.UserRepository;

@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public RegControl(
            PasswordEncoder encoder,
            UserRepository userRepository,
            AuthorityRepository authorityRepository
    ) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute Accident accident) {
        return "reg";
    }
}