package br.com.cod3r.adapter.hexagonal.application;

import br.com.cod3r.adapter.hexagonal.core.model.User;
import br.com.cod3r.adapter.hexagonal.core.ports.UserRepository;
import br.com.cod3r.adapter.hexagonal.core.usecases.UserService;
import br.com.cod3r.adapter.hexagonal.infrastructure.UserMemoryDatabaseAdapter;

import java.util.List;
import java.util.Map;

public class UserRest {

	private UserService userFacade;

	public UserRest() {
		UserRepository userRepository = new UserMemoryDatabaseAdapter();
		userFacade = new UserService(userRepository);
	}

	public Integer post(Map<String, String> values) {
		try{
			User user = new User(values.get("name"),values.get("email"),values.get("password"));
		}catch (Exception e){
			System.out.println(e.getMessage());
			return 400;
		}
		return 201;
	}
	
	public Integer get() {
		List<User> users = userFacade.getUsers();
		users.stream().forEach(System.out::println);
		return 200;
	}
}
