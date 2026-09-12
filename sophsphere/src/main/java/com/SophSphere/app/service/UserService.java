package com.SophSphere.app.service;

import com.SophSphere.app.model.User;
import com.SophSphere.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
  //  public List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }
    public boolean updateuser(Long id,User updateduser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateduser.getFirstName());
                    existingUser.setLastName(updateduser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
