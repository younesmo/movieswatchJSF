package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;


/**
 * The persistent class for the movies database table.
 * 
 */
@Entity
@Table(name="movies")
@NamedQueries({
	@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m"),
	@NamedQuery(name="Movie.findByCriteriaTitlePaysGenre", query="SELECT m FROM Movie m where m.title= :title AND m.genre = :genre AND m.pays = :pays"),
	@NamedQuery(name="Movie.findByCriteriaTitlePays", query="SELECT m FROM Movie m where m.title= :title AND m.pays = :pays"),
	@NamedQuery(name="Movie.findByCriteriaTitle", query="SELECT m FROM Movie m where m.title= :title"),
	
	@NamedQuery(name="Movie.findByCriteriaPaysGenre", query="SELECT m FROM Movie m where m.genre= :genre AND m.pays = :pays"),
	@NamedQuery(name="Movie.findByCriteriaPaysTitle", query="SELECT m FROM Movie m where m.title= :title AND m.pays = :pays"),
	@NamedQuery(name="Movie.findByCriteriaPays", query="SELECT m FROM Movie m where m.pays = :pays"),	
	
	@NamedQuery(name="Movie.findByCriteriaGenreTitle", query="SELECT m FROM Movie m where m.title= :title AND m.genre = :genre"),
	@NamedQuery(name="Movie.findByCriteriaGenrePays", query="SELECT m FROM Movie m where m.pays= :pays AND m.genre = :genre"),
	@NamedQuery(name="Movie.findByCriteriaGenre", query="SELECT m FROM Movie m where m.genre = :genre")
	})
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=45)
	private String budget;

	@JoinColumn(name="ID_CSA", nullable=false)
	private Csa csa;

	@Column(length=1)
	private String metrage;

	@Column(name="num_isan", length=45)
	private String numIsan;

	@Column(name="poster_url", length=255)
	private String posterUrl;

	@Column(name="production_year", length=45)
	private String productionYear;

	@Column(name="genre", length=255)
	private String genre;
	
	@Column(name="pays", length=255)
	private String pays;
	
	@Lob
	private String synopsis;

	@Column(length=255)
	@NotNull
	private String title;

	//bi-directional many-to-one association to MoviesFormat
	@OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
	private List<MoviesFormat> moviesFormats;
	
	//bi-directional many-to-one association to MovieCharacter
	@OneToMany(mappedBy="movie")
	private List<MovieCharacter> movieCharacters;

	//bi-directional many-to-one association to MovieCountry
	@OneToMany(mappedBy="movie", cascade = CascadeType.REMOVE)
	private List<MovieCountry> movieCountries;

	//bi-directional many-to-one association to MovieGenre
	@OneToMany(mappedBy="movie", cascade = CascadeType.REMOVE)
	private List<MovieGenre> movieGenres;

	//bi-directional many-to-one association to MoviePerson
	@OneToMany(mappedBy="movie", cascade= CascadeType.ALL)
	private List<MoviePerson> moviePersons;
		

	public Movie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBudget() {
		return this.budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public Csa getCsa() {
		return this.csa;
	}

	public void setCsa(Csa csa) {
		this.csa = csa;
	}

	public String getMetrage() {
		return this.metrage;
	}

	public void setMetrage(String metrage) {
		this.metrage = metrage;
	}

	public String getNumIsan() {
		return this.numIsan;
	}

	public void setNumIsan(String numIsan) {
		this.numIsan = numIsan;
	}

	public String getPosterUrl() {
		return this.posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getProductionYear() {
		return this.productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<MovieCharacter> getMovieCharacters() {
		return this.movieCharacters;
	}

	public void setMovieCharacters(List<MovieCharacter> movieCharacters) {
		this.movieCharacters = movieCharacters;
	}

	public MovieCharacter addMovieCharacter(MovieCharacter movieCharacter) {
		getMovieCharacters().add(movieCharacter);
		movieCharacter.setMovie(this);

		return movieCharacter;
	}

	public MovieCharacter removeMovieCharacter(MovieCharacter movieCharacter) {
		getMovieCharacters().remove(movieCharacter);
		movieCharacter.setMovie(null);

		return movieCharacter;
	}

	public List<MovieCountry> getMovieCountries() {
		return this.movieCountries;
	}

	public void setMovieCountries(List<MovieCountry> movieCountries) {
		this.movieCountries = movieCountries;
	}

	public MovieCountry addMovieCountry(MovieCountry movieCountry) {
		getMovieCountries().add(movieCountry);
		movieCountry.setMovie(this);

		return movieCountry;
	}

	public MovieCountry removeMovieCountry(MovieCountry movieCountry) {
		getMovieCountries().remove(movieCountry);
		movieCountry.setMovie(null);

		return movieCountry;
	}

	public List<MovieGenre> getMovieGenres() {
		return this.movieGenres;
	}

	public void setMovieGenres(List<MovieGenre> movieGenres) {
		this.movieGenres = movieGenres;
	}

	public MovieGenre addMovieGenre(MovieGenre movieGenre) {
		getMovieGenres().add(movieGenre);
		movieGenre.setMovie(this);

		return movieGenre;
	}

	public MovieGenre removeMovieGenre(MovieGenre movieGenre) {
		getMovieGenres().remove(movieGenre);
		movieGenre.setMovie(null);

		return movieGenre;
	}

	public List<MoviePerson> getMoviePersons() {
		return this.moviePersons;
	}

	public void setMoviePersons(List<MoviePerson> moviePersons) {
		this.moviePersons = moviePersons;
	}

	public MoviePerson addMoviePerson(MoviePerson moviePerson) {
		getMoviePersons().add(moviePerson);
		moviePerson.setMovie(this);

		return moviePerson;
	}

	public MoviePerson removeMoviePerson(MoviePerson moviePerson) {
		getMoviePersons().remove(moviePerson);
		moviePerson.setMovie(null);

		return moviePerson;
	}


	public List<MoviesFormat> getMoviesFormats() {
		return this.moviesFormats;
	}

	public void setMoviesFormats(List<MoviesFormat> moviesFormats) {
		this.moviesFormats = moviesFormats;
	}

	public MoviesFormat addMoviesFormat(MoviesFormat moviesFormat) {
		getMoviesFormats().add(moviesFormat);
		moviesFormat.setMovie(this);

		return moviesFormat;
	}

	public MoviesFormat removeMoviesFormat(MoviesFormat moviesFormat) {
		getMoviesFormats().remove(moviesFormat);
		moviesFormat.setMovie(null);

		return moviesFormat;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
	
}