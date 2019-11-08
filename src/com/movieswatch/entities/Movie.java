package com.movieswatch.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the films database table.
 * 
 */
@Entity
@Table(name="movies")
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private int id;

	@Column(name="production_year", length=45)
	private String productionYear;

	@Column(length=45)
	private String budget;

	@Column(length=1)
	private String metrage;

	@Column(name="num_isan", length=45)
	private String numIsan;

	@Lob
	private String synopsis;

	@Column(name="title", length=255)
	private String title;

	@Column(name="poster_url", length=255)
	private String posterUrl;

	//bi-directional many-to-one association to OrderMovie
	@OneToMany(mappedBy="movie")
	private List<OrderMovie> orderMovies;

	//bi-directional many-to-one association to Csa
	@ManyToOne
	@JoinColumn(name="ID_CSA", nullable=false)
	private Csa csa;

	//bi-directional many-to-one association to MovieGenre
	@OneToMany(mappedBy="movie")
	private List<MovieGenre> movieGenres;

	//bi-directional many-to-one association to MovieCountry
	@OneToMany(mappedBy="movie")
	private List<MovieCountry> movieCountry;

	//bi-directional many-to-one association to MovieCharacter
	@OneToMany(mappedBy="movie")
	private List<MovieCharacter> movieCharacters;

	//bi-directional many-to-one association to MoviePerson
	@OneToMany(mappedBy="movie")
	private List<MoviePerson> moviePersons;

	//bi-directional many-to-one association to MovieUser
	@OneToMany(mappedBy="movie")
	private List<MovieUser> movieUsers;

	public Movie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductionYear() {
		return this.productionYear;
	}

	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}

	public String getBudget() {
		return this.budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
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

	public String getPosterUrl() {
		return this.posterUrl;
	}

	public void setPosterUrl(String urlPoster) {
		this.posterUrl = urlPoster;
	}

	public List<OrderMovie> getOrderMovies() {
		return this.orderMovies;
	}

	public void setOrderMovies(List<OrderMovie> orderMovies) {
		this.orderMovies = orderMovies;
	}

	public OrderMovie addOrderMovie(OrderMovie orderMovie) {
		getOrderMovies().add(orderMovie);
		orderMovie.setMovie(this);

		return orderMovie;
	}

	public OrderMovie removeOrderMovie(OrderMovie orderMovie) {
		getOrderMovies().remove(orderMovie);
		orderMovie.setMovie(null);

		return orderMovie;
	}

	public Csa getCsa() {
		return this.csa;
	}

	public void setCsa(Csa csa) {
		this.csa = csa;
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

	public MovieGenre removeMovieGenre(MovieGenre MovieGenre) {
		getMovieGenres().remove(MovieGenre);
		MovieGenre.setMovie(null);

		return MovieGenre;
	}

	public List<MovieCountry> getMovieCountries() {
		return this.movieCountry;
	}

	public void setMovieCountries(List<MovieCountry> movieCountry) {
		this.movieCountry = movieCountry;
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

	public List<MovieCharacter> getMovieCharacter() {
		return this.movieCharacters;
	}

	public void setMovieCharacter(List<MovieCharacter> movieCharacter) {
		this.movieCharacters = movieCharacter;
	}

	public MovieCharacter addMovieCharacter(MovieCharacter movieCharacter) {
		getMovieCharacter().add(movieCharacter);
		movieCharacter.setMovie(this);

		return movieCharacter;
	}

	public MovieCharacter removeMovieCharacter(MovieCharacter movieCharacter) {
		getMovieCharacter().remove(movieCharacter);
		movieCharacter.setMovie(null);

		return movieCharacter;
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

	public List<MovieUser> getMovieUsers() {
		return this.movieUsers;
	}

	public void setMovieUsers(List<MovieUser> movieUsers) {
		this.movieUsers = movieUsers;
	}

	public MovieUser addMovieUser(MovieUser movieUser) {
		getMovieUsers().add(movieUser);
		movieUser.setMovie(this);

		return movieUser;
	}

	public MovieUser removeMovieUser(MovieUser movieUser) {
		getMovieUsers().remove(movieUser);
		movieUser.setMovie(null);

		return movieUser;
	}

}