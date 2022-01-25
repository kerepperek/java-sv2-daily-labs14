package day02;

import java.util.List;

public class Movie {

    private String title;
    private long length;
    private List<String> actors;

    public Movie(String title, long length, List<String> actors) {
        this.title = title;
        this.length = length;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public long getLength() {
        return length;
    }

    public List<String> getActors() {
        return actors;
    }
}
