package com.joao.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joao.workshopmongo.domain.Post;
import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.dto.AuthorDTO;
import com.joao.workshopmongo.dto.CommentDTO;
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
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu viagem","Para sao paulo",new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("21/03/2012"),"Pdsadsa","sadsadsao",new AuthorDTO(maria));
		
		
		CommentDTO cm = new CommentDTO("Boa viagem",sdf.parse("21/03/2018"),new AuthorDTO(alex));
		CommentDTO cm2 = new CommentDTO("Vai dormi",sdf.parse("21/03/2019"),new AuthorDTO(alex));
		CommentDTO cm3 = new CommentDTO("Ja ganhou",sdf.parse("21/03/2019"),new AuthorDTO(bob));
		
		post1.getComments().addAll(Arrays.asList(cm,cm2));
		post2.getComments().addAll(Arrays.asList(cm3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
		
	}
}
