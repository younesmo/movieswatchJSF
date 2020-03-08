package com.movieswatch.managedBeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.movieswatch.entities.Csa;
import com.movieswatch.entities.Movie;
import com.movieswatch.services.MovieService;
import com.movieswatch.services.MovieServiceImpl;

@Named
@ViewScoped
public class AddMovieBean implements Serializable {

	private Movie movie;
	private UploadedFile videoFile;
	private Csa csa;
	private transient MovieService movieService;
	
	public AddMovieBean() {
		movie= new Movie();
		csa= new Csa();
		this.movieService= new MovieServiceImpl();
	}
	
	public String addMovie() throws IOException {
		ServletContext servletContext= (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		Path folder = Paths.get(servletContext.getRealPath("/")+"/resources/default/videos");
		String filename = FilenameUtils.getBaseName(videoFile.getName()); 
		String extension = FilenameUtils.getExtension(videoFile.getName());
		Path file = Files.createTempFile(folder, filename, extension);
		
		try (InputStream input = videoFile.getInputStream()) {
		    Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
		}
		
		movie.setCsa(csa);
		movieService.addMovie(movie);
		return "movies";
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Csa getCsa() {
		return csa;
	}
	public void setCsa(Csa csa) {
		this.csa = csa;
	}

	public UploadedFile getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(UploadedFile videoFile) {
		this.videoFile = videoFile;
	}
	
	
	
}
