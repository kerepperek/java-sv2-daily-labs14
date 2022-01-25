package day02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    private List<Movie> movies=new ArrayList<>();

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public  List<Movie> getMoviesByActor(String actor){
        return movies.stream()
                .filter(o->o.getActors().contains(actor))
                .collect(Collectors.toList());
    }

    public long getLongestMovieLength(){
        return movies.stream()
                .mapToLong(m->m.getLength())
                .max().orElseThrow(()-> new IllegalArgumentException("Hiba"));
    }

}
