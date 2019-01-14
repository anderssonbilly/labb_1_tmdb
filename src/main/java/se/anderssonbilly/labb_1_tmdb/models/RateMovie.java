package se.anderssonbilly.labb_1_tmdb.models;

public class RateMovie {
	private int movieId;
	private double rating;
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
}
