package io.javalearn.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javalearn.moviecatalogservice.models.CatalogItem;
import io.javalearn.moviecatalogservice.models.Movie;
import io.javalearn.moviecatalogservice.models.Rating;
import io.javalearn.moviecatalogservice.services.MovieInfo;
import io.javalearn.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatlogs(@PathVariable("userId") String userId){		
		Movie movie = movieInfo.getMovie();
		List<Rating> rating = userRatingInfo.getRatings(userId);
		return rating.stream().map( r -> new CatalogItem(movie.getName(),"Best movie",r.getRating())).collect(Collectors.toList());
	}
	
}
