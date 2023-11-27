package ttps.java.grupo1.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.repository.GroupRepository;
import ttps.java.grupo1.model.Group;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

//    @Transactional
//    public User addMember(Long groupId, User user) {
//        Group group = this.groupRepository.findById(groupId)
//                .orElseThrow(EntityNotFoundException::new);
//        List<User> users = group.getUsers();
//        users.add(user);
//        groupRepository.save(group);
//        return user;
//    }
//
//    @Transactional
//    public User removeMember(Long groupId, User user) {
//        Group group = this.groupRepository.findById(groupId)
//                .orElseThrow(EntityNotFoundException::new);
//        List<User> users = group.getUsers();
//        users.remove(user);
//        groupRepository.save(group);
//        return user;
//    }

    @Transactional
    public Group findById(Long groupId) {
        Group group = this.groupRepository.findById(groupId)
                .orElseThrow(EntityNotFoundException::new);
        return group;
    }

    public Group save(Group group) {
        return this.groupRepository.save(group);
    }
}
