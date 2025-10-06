package ru.naumov.bloghub.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.naumov.bloghub.model.User;
import ru.naumov.bloghub.repository.UserRepository;

import java.util.Optional;

public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent())
            return new UserDetailsImp(userOptional.get());
        else
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    }
}
