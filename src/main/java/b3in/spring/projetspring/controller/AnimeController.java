package b3in.spring.projetspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimeController {
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info() {
        return "L'appli tourne...";
    }
}
