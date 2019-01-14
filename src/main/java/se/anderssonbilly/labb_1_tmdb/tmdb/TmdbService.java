package se.anderssonbilly.labb_1_tmdb.tmdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.UriEncoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import se.anderssonbilly.labb_1_tmdb.models.Genre;
import se.anderssonbilly.labb_1_tmdb.models.Genres;
import se.anderssonbilly.labb_1_tmdb.models.Movie;
import se.anderssonbilly.labb_1_tmdb.models.Movies;
import se.anderssonbilly.labb_1_tmdb.models.RateMovie;
import se.anderssonbilly.labb_1_tmdb.models.TmdbSession;

@Service
@ConfigurationProperties(prefix = "tmdb-api")
public class TmdbService {

	private final Map<String, String> sortBy = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("popularity.asc", "Popularity Ascending");
			put("popularity.desc", "Popularity Descending");
			put("release_date.asc", "Release Date Ascending");
			put("release_date.desc", "Release Date Descending");
			put("revenue.asc", "Revenue Ascending");
			put("revenue.desc", "Revenue Descending");
			put("primary_release_date.asc", "Primary Release Date Ascending");
			put("primary_release_date.desc", "Primary Resease Date Descending");
			put("original_title.asc", "Title Ascending");
			put("original_title.desc", "Title Descending");
			put("vote_average.asc", "Average Vote Ascending");
			put("vote_average.desc", "Average Vote Descending");
			put("vote_count.asc", "Votes Ascending");
			put("vote_count.desc", "Votes Descending");
		}
	};

	private TmdbSession tmdbSession;
	private String key;
	private String url;
	private String imgUrl;
	private Genres genres;

	public List<Genre> getGenres() {
		if (genres == null)
			setGenres();

		return genres.getGenres();
	}

	private void setGenres() {
		this.genres = (Genres) jsonToJava(getJson("genre/movie/list", null), new Genres());
	}

	public String getGenre(String genre_id) {
		if (genres.getGenres() != null)
			for (Genre genre : genres.getGenres())
				if (genre.getId() == Integer.valueOf(genre_id))
					return genre.getName();
		return null;
	}

	public Movie getMovie(int id) {
		return (Movie) jsonToJava(getJson("movie/" + id, null), new Movie());
	}

	public Movies getSimilarMovies(int id) {
		return ((Movies) jsonToJava(getJson("movie/" + id + "/similar", null), new Movies()));
	}

	public Movies search(String query, int page) {
		return ((Movies) jsonToJava(
				getJson("search/movie", new String[] { "query=" + query, "include_adult=true", "page=" + page }),
				new Movies()));
	}

	public Movies discover(String genreId, String sortBy, int page) {
		return ((Movies) jsonToJava(getJson("discover/movie",
				new String[] { "with_genres=" + genreId, "sort_by=" + sortBy, "include_adult=true", "page=" + page }),
				new Movies()));
	}

	public ResponseEntity<Object> rateMovie(RateMovie rateMovie) {
		if (checkSession()) {
			String uri = url + "movie/" + rateMovie.getMovieId() + "/rating?api_key=" + key + "&guest_session_id="
					+ tmdbSession.getSessionId();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			HttpEntity<String> request = new HttpEntity<String>("{\"value\":" + rateMovie.getRating() + "}", headers);

			RestTemplate restTemplate = new RestTemplate();

			return restTemplate.postForEntity(uri, request, Object.class);
		}
		return null;
	}

	private boolean checkSession() {
		if (tmdbSession == null)
			tmdbSession = getNewSession();
		else {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date expires = format.parse(tmdbSession.getExpires_at());
				if (expires.compareTo(new Date()) < 1)
					tmdbSession = getNewSession();

			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private TmdbSession getNewSession() {
		return ((TmdbSession) jsonToJava(getJson("authentication/guest_session/new", null), new TmdbSession()));
	}

	private String getJson(String method, String[] args) {
		String json = "";
		try {
			String targetUrl = method + "?";

			if (args != null)
				for (String arg : args)
					targetUrl += UriEncoder.encode(arg) + "&";
			targetUrl += "api_key=" + key;
		
			BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url + targetUrl).openStream()));
			String str = "";
			while (null != (str = br.readLine()))
				json += str;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return json;
	}

	private Object jsonToJava(String json, Object o) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			o = mapper.readValue(json, o.getClass());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return o;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Map<String, String> getSortBy() {
		return sortBy.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey,
				Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

}
