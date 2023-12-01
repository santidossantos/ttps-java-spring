package ttps.java.grupo1.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.repository.GroupRepository;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Optional<Group> findById(Long groupId) { return groupRepository.findById(groupId); }

    @Transactional
    public Group save(Group group) {
        return this.groupRepository.save(group);
    }

    @Transactional
    public void update(Group currentGroup) {
        this.groupRepository.save(currentGroup);
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

    @Transactional
    public List<Group> findAll() {
        return this.groupRepository.findAll();
    }

}