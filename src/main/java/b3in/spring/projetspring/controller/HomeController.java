package b3in.spring.projetspring.controller;

import b3in.spring.projetspring.entity.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import b3in.spring.projetspring.service.AnimeService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Anime> animes = animeService.getAllAnime();
        model.addAttribute("animes", animes);
        return "List";
    }

}
