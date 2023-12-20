package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ttps.java.grupo1.exception.DuplicateConstraintException;
import ttps.java.grupo1.exception.UserNotFoundException;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.repository.UserRepository;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.security.JwtService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

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

    @Transactional
    public User register(User user) throws DuplicateConstraintException {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateConstraintException("El nombre de usuario ingresado ya está en uso");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateConstraintException("El email ingresado ya está en uso");
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
   }

   @Transactional(readOnly = true)
    public String authenticate(String username, String password) {
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isPresent() && BCrypt.checkpw(password, user.get().getPassword())) {
                return jwtService.generateToken(username);
            }
            return null;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Transactional(readOnly = true)
    public List<Expense> getExpensesOfUserWithUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        List<Expense> filteredExpense = new ArrayList<>();
        if (user.isPresent()) {
            List<Group> groupsOfUser = user.get().getGroups();
            filteredExpense = groupsOfUser
                    .stream().map(Group::getExpenses)
                    .flatMap(List::stream)
                    .filter(expense -> expense.getPayingUser() == user.get())
                    .toList();
        }
        return filteredExpense;
    }

    @Transactional(readOnly = true)
    public List<User> getUsersWithFilter(String filter){
        return userRepository.findUserWithFilter(filter);
    }
    
    public String getAvatar(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return user.get().getAvatar();
        }
        throw new NotFoundException("User not found");
    }

}
