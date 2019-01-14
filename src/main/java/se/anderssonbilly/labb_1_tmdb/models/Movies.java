package se.anderssonbilly.labb_1_tmdb.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Movies {

	private int page;
	private int total_results;
	private int total_pages;
	
	private List<Movie> movies;

	public List<Movie> getMovies() {
		return movies;
	}

	@JsonSetter("results")
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
		if(this.total_pages > 1000) this.total_pages = 1000;
	}

	public int getTotal_results() {
		return total_results;
	}

	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
