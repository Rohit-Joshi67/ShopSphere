package com.SophSphere.app.service;

import com.SophSphere.app.dto.AddressDTO;
import com.SophSphere.app.dto.UserRequest;
import com.SophSphere.app.dto.UserResponse;
import com.SophSphere.app.model.Address;
import com.SophSphere.app.model.User;
import com.SophSphere.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    // Create user
    public UserResponse createUser(UserRequest userRequest) {

        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        List<Address> addresses = userRequest.getAddresses() == null
                ? Collections.emptyList()
                : userRequest.getAddresses()
                .stream()
                .map(this::toAddress)
                .collect(Collectors.toList());

        user.setAddresses(addresses);

        User savedUser = userRepository.save(user);

        return toUserResponse(savedUser);
    }

    // Get user by ID
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        return user != null ? toUserResponse(user) : null;
    }

    // Update user
    public UserResponse updateUser(Long id, UserRequest userRequest) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        List<Address> addresses = userRequest.getAddresses() == null
                ? Collections.emptyList()
                : userRequest.getAddresses()
                .stream()
                .map(this::toAddress)
                .collect(Collectors.toList());

        user.setAddresses(addresses);

        User savedUser = userRepository.save(user);

        return toUserResponse(savedUser);
    }

    // Delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Convert User -> UserResponse
    private UserResponse toUserResponse(User user) {

        List<AddressDTO> addressDTOs = user.getAddresses() == null
                ? Collections.emptyList()
                : user.getAddresses()
                .stream()
                .map(this::toAddressDTO)
                .collect(Collectors.toList());

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                addressDTOs
        );
    }

    // Convert AddressDTO -> Address
    private Address toAddress(AddressDTO addressDTO) {

        Address address = new Address();

        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());

        return address;
    }

    // Convert Address -> AddressDTO
    private AddressDTO toAddressDTO(Address address) {

        return new AddressDTO(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode()
        );
    }
}
