package se.anderssonbilly.labb_1_tmdb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.anderssonbilly.labb_1_tmdb.models.Movie;
import se.anderssonbilly.labb_1_tmdb.models.Movies;
import se.anderssonbilly.labb_1_tmdb.models.RateMovie;
import se.anderssonbilly.labb_1_tmdb.tmdb.TmdbService;

@Controller
public class HomeController {

	@Autowired
	public TmdbService tmdbService;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAllAttributes(getAttributes(null));
		return "index";
	}

	@RequestMapping("/genre")
	public String genre(Model model, String genre_id, String sortBy, int page) {
		Movies movies = tmdbService.discover(genre_id, sortBy, page);
		model.addAllAttributes(getAttributes(movies));
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("genre", tmdbService.getGenre(genre_id));
		return "index";
	}

	@RequestMapping("/search")
	public String search(Model model, String query, int page) {
		Movies movies = tmdbService.search(query, page);
		model.addAllAttributes(getAttributes(movies));
		model.addAttribute("query", query);
		return "index";
	}

	@RequestMapping(value = "/ratemovie", method = RequestMethod.POST)
	public ResponseEntity<Object> rateMovie(@RequestBody RateMovie rateMovie) {
		return tmdbService.rateMovie(rateMovie);
	}

	@RequestMapping(value = "/movie", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Movie movie(int movie_id) {
		return tmdbService.getMovie(movie_id);
	}

	@RequestMapping(value = "/similarmovies", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Movie> similarMovies(int movie_id) {
		return tmdbService.getSimilarMovies(movie_id).getMovies();
	}

	private Map<String, Object> getAttributes(Movies movies) {
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("genres", tmdbService.getGenres());
		modelMap.put("sortByOptions", tmdbService.getSortBy());
		modelMap.put("imgUrl", tmdbService.getImgUrl());
		if (movies != null) {
			modelMap.put("movies", movies.getMovies());
			modelMap.put("page", movies.getPage());
			modelMap.put("total_pages", movies.getTotal_pages());
			modelMap.put("results", movies.getTotal_results());
		}
		return modelMap;
	}
}
