package b3in.spring.projetspring.controller;

import b3in.spring.projetspring.entity.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import b3in.spring.projetspring.service.AnimeService;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping("/")
    public String index() {
        return "connexion";
    }

    @GetMapping("/mainpage")
    public String mainpage(Model model) {
        List<Anime> animes = animeService.getAllAnime();
        List<String> imagesBase64 = animes.stream()
                .map(anime -> Base64.getEncoder().encodeToString(anime.getImage()))
                .collect(Collectors.toList());
        model.addAttribute("animes", animes);
        model.addAttribute("imagesBase64", imagesBase64);
        return "mainpage";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "connexion";
    }

    @GetMapping("/inscription")
    public String inscription() {
        return "inscription";
    }

}
