package b3in.spring.projetspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "connexion";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "connexion";
    }

    @GetMapping("/inscription")
    public String inscription() {
        return "inscription";
    }

    @GetMapping("/mainpage")
    public String mainpage() {
        return "/mainpage";
    }
}
