package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.DAO.UserDAO;
import ttps.java.grupo1.model.User;

public class UserService {

    @Autowired
    UserDAO userDAO;

    @Transactional(readOnly = true)
    public String findByEmail() {
        return this.userDAO.findByEmail("jorge11@gmail.com").getEmail();
    }

    @Transactional
    public User saveUser(User user){ return this.userDAO.save(user); }
}
