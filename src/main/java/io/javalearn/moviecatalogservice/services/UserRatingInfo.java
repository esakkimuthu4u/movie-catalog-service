package io.javalearn.moviecatalogservice.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javalearn.moviecatalogservice.models.Rating;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private List<Rating> getFallbackRatings(String userId) {
		return Arrays.asList(new Rating("0", 0));
	}

	@HystrixCommand(fallbackMethod="getFallbackRatings")
	public List<Rating> getRatings(String userId) {
		Rating[] ratings = restTemplate.getForObject("http://movie-rating-service/ratings/user/"+userId, Rating[].class);
		return  Arrays.asList(ratings);
	}

}
