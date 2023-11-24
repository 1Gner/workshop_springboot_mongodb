package com.joao.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joao.workshopmongo.domain.Post;
import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.repository.PostRepository;
import com.joao.workshopmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String...  arg0) throws Exception{
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu viagem","Para sao paulo",maria);
		Post post2 = new Post(null,sdf.parse("21/03/2012"),"Pdsadsa","sadsadsao",maria);
		
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		postRepository.saveAll(Arrays.asList(post1,post2));
	}
}
