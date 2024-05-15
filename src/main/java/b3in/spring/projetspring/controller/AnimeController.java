package b3in.spring.projetspring.controller;

import b3in.spring.projetspring.entity.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import  b3in.spring.projetspring.service.AnimeService;

import java.util.List;

@RestController
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @RequestMapping(value = "infoAnime", method = RequestMethod.GET)
    public String infoAnime() {
        return "L'appli tourne...";
    }

    @RequestMapping(value = "createAnime", method = RequestMethod.POST)
    public String createAnime(@RequestBody Anime anime) {
        return animeService.createAnime(anime);
    }

    @RequestMapping(value = "getAllAnime", method = RequestMethod.GET)
    public List<Anime> getAllAnime() {
        return animeService.getAllAnime();
    }

    @RequestMapping(value = "updateAnime", method = RequestMethod.PUT)
    public String updateAnime(@RequestBody Anime anime) {
        return animeService.updateAnime(anime);
    }

    @RequestMapping(value = "deleteAnime", method = RequestMethod.DELETE)
    public String deleteAnime(@RequestBody Anime anime) {
        return animeService.deleteAnime(anime);
    }
}
