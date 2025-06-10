package com.dalvittpeeter.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dalvittpeeter.workshopmongo.domain.Post;
import com.dalvittpeeter.workshopmongo.domain.User;
import com.dalvittpeeter.workshopmongo.dto.AuthorDTO;
import com.dalvittpeeter.workshopmongo.dto.CommentDTO;
import com.dalvittpeeter.workshopmongo.repository.PostRepository;
import com.dalvittpeeter.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userReposiroty;

	@Autowired
	private PostRepository postReposiroty;

	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "CS de noite", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Tanto lugar pra ir e o loko foi pra SP", sdf.parse("22/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Não seja assaltado pls", sdf.parse("21/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("É os guri do CS, não adianta, vamo que vamo gremiooo fiufiu", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postReposiroty.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userReposiroty.save(maria);
	}

}