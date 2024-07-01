package br.com.cod3r.adapter.hexagonal.core.usecases;

import br.com.cod3r.adapter.hexagonal.core.model.User;
import br.com.cod3r.adapter.hexagonal.core.ports.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        boolean alreadyUsedEmail = userRepository.getAll().stream().anyMatch(userDb -> userDb.getEmail().equalsIgnoreCase(user.getEmail()));
        if(alreadyUsedEmail) throw  new RuntimeException("Email address already exists!");
        userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.getAll();
    }
	
}
