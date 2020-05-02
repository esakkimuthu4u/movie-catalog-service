package io.javalearn.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javalearn.moviecatalogservice.models.CatalogItem;
import io.javalearn.moviecatalogservice.models.Movie;
import io.javalearn.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatlogs(@PathVariable("userId") String userId){
		
		Movie movie = getMovie();
		List<Rating> rating = getRatings(userId);
		return rating.stream().map( r -> new CatalogItem(movie.getName(),"Best movie",r.getRating())).collect(Collectors.toList());
	}

	private Movie getMovie() {
		return restTemplate.getForObject("http://movie-info-service/movies/12345", Movie.class);
	}

	private List<Rating> getRatings(String userId) {
		Rating[] ratings = restTemplate.getForObject("http://movie-rating-service/ratings/user/"+userId, Rating[].class);
		return  Arrays.asList(ratings);
	}
}
