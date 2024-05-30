package b3in.spring.projetspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Anime {
    @Id
    private int id;

    private String name;

    private String genre;

    private int episodes;

    private String studio;

    private int year;

    private byte[] image; // Données images converties en binaire

    public Anime(int id, String name, String genre, int episodes, String studio, int year, byte[] image) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.episodes = episodes;
        this.studio = studio;
        this.year = year;
        this.image = image;
    }

    public Anime() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
