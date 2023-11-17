package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.repository.UserRepository;
import ttps.java.grupo1.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        return this.userRepository.save(user);
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
}
