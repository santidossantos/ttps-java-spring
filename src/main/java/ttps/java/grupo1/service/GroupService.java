package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.repository.GroupRepository;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.repository.UserRepository;
import ttps.java.grupo1.security.JwtService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    JwtService JwtService = new JwtService();

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<Group> findById(Long groupId) { return groupRepository.findById(groupId); }

    @Transactional
    public Group save(Group group) {
        return this.groupRepository.save(group);
    }

    @Transactional
    public Group update(Group currentGroup) {
        return this.groupRepository.save(currentGroup);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (this.groupRepository.existsById(id)) {
            this.groupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Group> addMember(Long groupId, Long userId) {
        if (this.groupRepository.findById(groupId).isPresent() && this.userRepository.findById(userId).isPresent()) {
            Group group = this.groupRepository.findById(groupId).get();
            User user = this.userRepository.findById(userId).get();
            group.addMember(user);
            this.groupRepository.save(group);
            return Optional.of(group);
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return this.groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Group> findMyGroups(String token) {
        String userName = JwtService.getUsernameFromToken(token);
        if (userName != null) {
            Optional<User> user = userRepository.findByUsername(userName);
            if (user.isPresent()) {
                return user.get().getGroups();
            }
        }
        return Collections.emptyList();
    }

}