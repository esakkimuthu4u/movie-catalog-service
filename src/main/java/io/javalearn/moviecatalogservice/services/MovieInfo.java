package io.javalearn.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javalearn.moviecatalogservice.models.Movie;

@Service
public class MovieInfo {
	@Autowired
	private RestTemplate restTemplate;
	
	
	private Movie getFallbackMovie() {
		return new Movie("0", "No Movie");
	}
	
	
	@HystrixCommand(fallbackMethod="getFallbackMovie")
	public Movie getMovie() {
		return restTemplate.getForObject("http://movie-info-service/movies/12345", Movie.class);
	}
}
