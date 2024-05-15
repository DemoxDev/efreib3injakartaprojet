package b3in.spring.projetspring.service;

import b3in.spring.projetspring.entity.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import b3in.spring.projetspring.repository.AnimeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }


    @Transactional
    public String createAnime(Anime anime){
        try {
            if (animeRepository.existsByName(anime.getName())){
                return "Anime existe deja";
            }

            Integer maxId = animeRepository.findMaxId();
            if (maxId == null) {
                maxId = 0;
            }

            anime.setId(maxId + 1);
            animeRepository.save(anime);
            return "Anime cree avec succes";
        } catch (Exception e){
            throw e;
        }
    }

    public List<Anime> getAllAnime(){
        return animeRepository.findAll();
    }

    @Transactional
    public String updateAnime(Anime anime){
        if (animeRepository.existsByName(anime.getName())){
            try {
                List<Anime> animes = animeRepository.findByName(anime.getName());
                animes.stream().forEach(a -> {
                    Anime animeToUpdate = animeRepository.findById(a.getId()).get();
                    animeToUpdate.setName(anime.getName());
                    animeToUpdate.setGenre(anime.getGenre());
                    animeToUpdate.setEpisodes(anime.getEpisodes());
                    animeToUpdate.setStudio(anime.getStudio());
                    animeToUpdate.setYear(anime.getYear());
                    animeRepository.save(animeToUpdate);
                });
                return "Anime mis a jour avec succes";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "Anime non trouve";
        }
    }

    @Transactional
    public String deleteAnime(Anime anime){
        if (animeRepository.existsByName(anime.getName())){
            try {
                List<Anime> animes = animeRepository.findByName(anime.getName());
                animes.stream().forEach(a -> {
                    System.out.println(a.toString());
                    animeRepository.deleteById(a.getId());
                });
                return "Anime supprime avec succes";
            } catch (Exception e){
                throw e;
            }
        } else {
            return "Anime non trouve";
        }
    }
}
