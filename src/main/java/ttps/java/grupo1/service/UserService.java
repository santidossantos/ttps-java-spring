package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.repository.UserRepository;
import ttps.java.grupo1.model.User;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public String findByEmail() {
        return this.userRepository.findByEmail("jorge11@gmail.com").getEmail();
    }

    @Transactional
    public User saveUser(User user){ return this.userRepository.save(user); }
}
