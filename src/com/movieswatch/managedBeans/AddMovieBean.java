package com.movieswatch.managedBeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.movieswatch.entities.Csa;
import com.movieswatch.entities.Format;
import com.movieswatch.entities.Movie;
import com.movieswatch.entities.MoviesFormat;
import com.movieswatch.services.MovieService;
import com.movieswatch.services.MovieServiceImpl;

@Named
@ViewScoped
public class AddMovieBean implements Serializable {

	private Movie movie;
	private MoviesFormat hd= new MoviesFormat();
	private MoviesFormat sd= new MoviesFormat();
	private MoviesFormat fork= new MoviesFormat();
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
		
		sd.setFormat(new Format());
		hd.setFormat(new Format());
		fork.setFormat(new Format());
	
		sd.getFormat().setId(2);
		hd.getFormat().setId(1);
		fork.getFormat().setId(4);
		
		List<MoviesFormat> mfs= new ArrayList<MoviesFormat>();
		
		mfs.add(hd);
		mfs.add(sd);
		mfs.add(fork);
		
		movie.setCsa(csa);
		movieService.addMovie(movie, mfs);
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

	public MoviesFormat getHd() {
		return hd;
	}

	public void setHd(MoviesFormat hd) {
		this.hd = hd;
	}

	public MoviesFormat getSd() {
		return sd;
	}

	public void setSd(MoviesFormat sd) {
		this.sd = sd;
	}

	public MoviesFormat getFork() {
		return fork;
	}

	public void setFork(MoviesFormat fork) {
		this.fork = fork;
	}
	
	
		
}
