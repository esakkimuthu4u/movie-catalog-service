package io.javalearn.moviecatalogservice.models;

public class Movie {
	private String movieId;
	private String name;
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	public Movie(String movieId, String name) {
		super();
		this.movieId = movieId;
		this.name = name;
	}
	
	public String getMovieId() {
		return movieId;
	}
	public String getName() {
		return name;
	}
}
