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
import com.dalvittpeeter.workshopmongo.repository.PostRepository;
import com.dalvittpeeter.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Tô viajando, é os guri", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Opa, bão?!", "Cszin de noite vai brilhar", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
	}

}
