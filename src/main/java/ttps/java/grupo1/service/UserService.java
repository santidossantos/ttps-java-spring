package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.exception.UserNotFoundException;
import ttps.java.grupo1.repository.RoleRepository;
import ttps.java.grupo1.repository.UserRepository;
import ttps.java.grupo1.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Transactional
    public boolean updateById(Long id, User userToUpdate) throws UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setEmail(userToUpdate.getEmail());
        user.setName(userToUpdate.getName());
        user.setPassword(userToUpdate.getPassword());
        user.setUsername(userToUpdate.getUsername());
        return true;
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if(this.userRepository.findById(id).isPresent()) {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean addFriend(Long id, Long friendId) {
    	if(this.userRepository.findById(id).isPresent() && this.userRepository.findById(friendId).isPresent()) {
    		User user = this.userRepository.findById(id).get();
    		User friend = this.userRepository.findById(friendId).get();
    		user.addFriend(friend);
    		friend.addFriend(user);
    		this.userRepository.save(user);
    		this.userRepository.save(friend);
    		return true;
    	}
    	return false;
    }

//    @Transactional
//    public User register(User user) throws DuplicateConstraintException {
//        if(userRepository.existsByUsername(user.getUsername())) {
//            throw new DuplicateConstraintException("Username already exists");
//        }
//        if(userRepository.existsByEmail(user.getEmail())) {
//            throw new DuplicateConstraintException("Email already exists");
//        }
//
//        Optional<UserRole> roles = roleRepository.findByName("USER");
//
//        if(roles.isPresent()) {
//        	user.setRoles(Collections.singletonList(roles.get()));
//        }
//        else {
//            UserRole role = new UserRole();
//            role.setName("USER");
//            roleRepository.save(role);
//            user.setRoles(Collections.singletonList(role));
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public String authenticate(String username, String password) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        username,
//                        password
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return jwtTokenProvider.generateToken(authentication);
//    }

}
