package se.anderssonbilly.labb_1_tmdb.models;

import java.util.ArrayList;

public class Movie {

	private int budget;
	private ArrayList<Genre> genres;
	private int id;
	private String imdb_id;
	private String original_language;
	private String original_title;
	private String overview;
	private double popularity;
	private String poster_path;
	private ArrayList<ProductionCompany> production_companies;
	private String release_date;
	private int revenue;
	private int runtime;
	private String status;
	private String tagline;
	private String title;
	private double vote_average;
	private int vote_count;

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImdb_id() {
		return imdb_id;
	}

	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}

	public String getOriginal_language() {
		return original_language;
	}

	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public ArrayList<ProductionCompany> getProduction_companies() {
		return production_companies;
	}

	public void setProduction_companies(ArrayList<ProductionCompany> production_companies) {
		this.production_companies = production_companies;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	public int getVote_count() {
		return vote_count;
	}

	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}


}
